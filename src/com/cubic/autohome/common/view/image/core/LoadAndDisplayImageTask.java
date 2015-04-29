package com.cubic.autohome.common.view.image.core;

import android.graphics.Bitmap;
import android.os.Handler;
import com.cubic.autohome.common.view.image.cache.disc.DiskCache;
import com.cubic.autohome.common.view.image.cache.memory.MemoryCache;
import com.cubic.autohome.common.view.image.core.assist.FailReason;
import com.cubic.autohome.common.view.image.core.assist.FailReason.FailType;
import com.cubic.autohome.common.view.image.core.assist.ImageScaleType;
import com.cubic.autohome.common.view.image.core.assist.ImageSize;
import com.cubic.autohome.common.view.image.core.assist.LoadedFrom;
import com.cubic.autohome.common.view.image.core.assist.ViewScaleType;
import com.cubic.autohome.common.view.image.core.decode.ImageDecoder;
import com.cubic.autohome.common.view.image.core.decode.ImageDecodingInfo;
import com.cubic.autohome.common.view.image.core.download.ImageDownloader;
import com.cubic.autohome.common.view.image.core.download.ImageDownloader.Scheme;
import com.cubic.autohome.common.view.image.core.imageaware.ImageAware;
import com.cubic.autohome.common.view.image.core.listener.ImageLoadingListener;
import com.cubic.autohome.common.view.image.core.listener.ImageLoadingProgressListener;
import com.cubic.autohome.common.view.image.core.process.BitmapProcessor;
import com.cubic.autohome.common.view.image.utils.IoUtils;
import com.cubic.autohome.common.view.image.utils.IoUtils.CopyListener;
import com.cubic.autohome.common.view.image.utils.L;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

final class LoadAndDisplayImageTask implements IoUtils.CopyListener, Runnable {
	private final ImageLoaderConfiguration configuration;
	private final ImageDecoder decoder;
	private final ImageDownloader downloader;
	private final ImageLoaderEngine engine;
	private final Handler handler;
	final ImageAware imageAware;
	private final ImageLoadingInfo imageLoadingInfo;
	final ImageLoadingListener listener;
	private LoadedFrom loadedFrom = LoadedFrom.NETWORK;
	private final String memoryCacheKey;
	private final ImageDownloader networkDeniedDownloader;
	final DisplayImageOptions options;
	final ImageLoadingProgressListener progressListener;
	private final ImageDownloader slowNetworkDownloader;
	private final boolean syncLoading;
	private final ImageSize targetSize;
	final String uri;

	public LoadAndDisplayImageTask(ImageLoaderEngine paramImageLoaderEngine,
			ImageLoadingInfo paramImageLoadingInfo, Handler paramHandler) {
		this.engine = paramImageLoaderEngine;
		this.imageLoadingInfo = paramImageLoadingInfo;
		this.handler = paramHandler;
		this.configuration = paramImageLoaderEngine.configuration;
		this.downloader = this.configuration.downloader;
		this.networkDeniedDownloader = this.configuration.networkDeniedDownloader;
		this.slowNetworkDownloader = this.configuration.slowNetworkDownloader;
		this.decoder = this.configuration.decoder;
		this.uri = paramImageLoadingInfo.uri;
		this.memoryCacheKey = paramImageLoadingInfo.memoryCacheKey;
		this.imageAware = paramImageLoadingInfo.imageAware;
		this.targetSize = paramImageLoadingInfo.targetSize;
		this.options = paramImageLoadingInfo.options;
		this.listener = paramImageLoadingInfo.listener;
		this.progressListener = paramImageLoadingInfo.progressListener;
		this.syncLoading = this.options.isSyncLoading();
	}

	private void checkTaskInterrupted()
			throws LoadAndDisplayImageTask.TaskCancelledException {
		if (isTaskInterrupted())
			throw new TaskCancelledException();
	}

	private void checkTaskNotActual()
			throws LoadAndDisplayImageTask.TaskCancelledException {
		checkViewCollected();
		checkViewReused();
	}

	private void checkViewCollected()
			throws LoadAndDisplayImageTask.TaskCancelledException {
		if (isViewCollected())
			throw new TaskCancelledException();
	}

	private void checkViewReused()
			throws LoadAndDisplayImageTask.TaskCancelledException {
		if (isViewReused())
			throw new TaskCancelledException();
	}

	private Bitmap decodeImage(String imageUri) throws IOException {
		ViewScaleType scaleType = this.imageAware.getScaleType();
		ImageDecodingInfo decodingInfo = new ImageDecodingInfo(
				this.memoryCacheKey, imageUri, this.uri, this.targetSize,
				scaleType, getDownloader(), this.options);
		return this.decoder.decode(decodingInfo);
	}

	private boolean delayIfNeed() {
		if (this.options.shouldDelayBeforeLoading()) {
			L.d("Delay %d ms before loading...  [%s]",
					new Object[] {
							Integer.valueOf(this.options
									.getDelayBeforeLoading()),
							this.memoryCacheKey });
			try {
				Thread.sleep(this.options.getDelayBeforeLoading());
				return isTaskNotActual();
			} catch (InterruptedException localInterruptedException) {
				L.e("Task was interrupted [%s]",
						new Object[] { this.memoryCacheKey });
				return false;
			}
		}
		return false;
	}

	private boolean downloadImage() throws IOException {
		InputStream localInputStream = getDownloader().getStream(this.uri,
				this.options.getExtraForDownloader());
		if (localInputStream == null) {
			L.e("No stream for image [%s]",
					new Object[] { this.memoryCacheKey });
			return false;
		}
		try {
			boolean bool = this.configuration.diskCache.save(this.uri,
					localInputStream, this);
			return bool;
		} finally {
			IoUtils.closeSilently(localInputStream);
		}
	}

	private void fireCancelEvent() {
		if ((this.syncLoading) || (isTaskInterrupted()))
			return;
		runTask(new Runnable() {
			public void run() {
				LoadAndDisplayImageTask.this.listener.onLoadingCancelled(
						LoadAndDisplayImageTask.this.uri,
						LoadAndDisplayImageTask.this.imageAware
								.getWrappedView());
			}
		}, false, this.handler, this.engine);
	}

	private void fireFailEvent(final FailReason.FailType paramFailType,
			final Throwable paramThrowable) {
		if ((this.syncLoading) || (isTaskInterrupted()) || (isTaskNotActual()))
			return;
		runTask(new Runnable() {
			public void run() {
				if (LoadAndDisplayImageTask.this.options.shouldShowImageOnFail())
					LoadAndDisplayImageTask.this.imageAware
							.setImageDrawable(LoadAndDisplayImageTask.this.options
									.getImageOnFail(LoadAndDisplayImageTask.this.configuration.resources));
				LoadAndDisplayImageTask.this.listener.onLoadingFailed(
						LoadAndDisplayImageTask.this.uri,
						LoadAndDisplayImageTask.this.imageAware
								.getWrappedView(), new FailReason(
								paramFailType, paramThrowable));
			}
		}, false, this.handler, this.engine);
	}

	private boolean fireProgressEvent(final int paramInt1, final int paramInt2) {
		if ((isTaskInterrupted()) || (isTaskNotActual()))
			return false;
		if (this.progressListener != null)
			runTask(new Runnable() {
				public void run() {
					LoadAndDisplayImageTask.this.progressListener.onProgressUpdate(
							LoadAndDisplayImageTask.this.uri,
							LoadAndDisplayImageTask.this.imageAware
									.getWrappedView(), paramInt1, paramInt2);
				}
			}, false, this.handler, this.engine);
		return true;
	}

	private ImageDownloader getDownloader() {
		if (this.engine.isNetworkDenied())
			return this.networkDeniedDownloader;
		if (this.engine.isSlowNetwork())
			return this.slowNetworkDownloader;
		return this.downloader;
	}

	private boolean isTaskInterrupted() {
		if (Thread.interrupted()) {
			L.d("Task was interrupted [%s]",
					new Object[] { this.memoryCacheKey });
			return true;
		}
		return false;
	}

	private boolean isTaskNotActual() {
		return (isViewCollected()) || (isViewReused());
	}

	private boolean isViewCollected() {
		if (this.imageAware.isCollected()) {
			L.d("ImageAware was collected by GC. Task is cancelled. [%s]",
					new Object[] { this.memoryCacheKey });
			return true;
		}
		return false;
	}

	/**
	 * 此方法可能错误
	 * 
	 * @return
	 */
	private boolean isViewReused() {
		String str = this.engine.getLoadingUriForView(this.imageAware);
		if (this.memoryCacheKey.equals(str)) {
			return false;
		}
		L.d("ImageAware is reused for another image. Task is cancelled. [%s]",
				new Object[] { this.memoryCacheKey });
		return true;
	}

	private boolean resizeAndSaveImage(int maxWidth, int maxHeight)
			throws IOException {
		File file = this.configuration.diskCache.get(this.uri);
		if (file != null) {
			if (file.exists()) {
				ImageSize imageSize = new ImageSize(maxWidth, maxHeight);
				DisplayImageOptions.Builder builder = new DisplayImageOptions.Builder();
				builder = builder.cloneFrom(options);
				builder.imageScaleType(ImageScaleType.IN_SAMPLE_INT);
				DisplayImageOptions newOptions = builder.build();
				Scheme scheme = Scheme.FILE;
				ImageDecodingInfo decodingInfo = new ImageDecodingInfo(
						memoryCacheKey, Scheme.FILE.name(), scheme.wrap(file
								.getAbsolutePath()), imageSize,
						ViewScaleType.FIT_INSIDE, getDownloader(), newOptions);
				Bitmap bitmap = decoder.decode(decodingInfo);
				if (bitmap != null) {
					BitmapProcessor bitmapProcessor = configuration.processorForDiskCache;
					if (bitmapProcessor != null) {
						L.d("Process image before cache on disk [%s]",
								new Object[] { memoryCacheKey });
						bitmap = bitmapProcessor.process(bitmap);
						if (bitmap != null) {
							configuration.diskCache
									.save(memoryCacheKey, bitmap);
							bitmap.recycle();
							return true;
						} else {
							L.d("Bitmap processor for disk cache returned null [%s]",
									new Object[] { memoryCacheKey });
							return false;
						}
					}
				}
				return false;
			}
		}
		return false;
	}

	static void runTask(Runnable r, boolean sync, Handler handler,
			ImageLoaderEngine engine) {
		if (sync) {
			r.run();
			return;
		}
		if (handler == null) {
			engine.fireCallback(r);
			return;
		}
		handler.post(r);
	}

	private boolean tryCacheImageOnDisk()
			throws LoadAndDisplayImageTask.TaskCancelledException {
		L.d("Cache image on disk [%s]", new Object[] { this.memoryCacheKey });
		try {
			boolean bool = downloadImage();
			if (bool) {
				int i = this.configuration.maxImageWidthForDiskCache;
				int j = this.configuration.maxImageHeightForDiskCache;
				if ((i > 0) || (j > 0)) {
					L.d("Resize image in disk cache [%s]",
							new Object[] { this.memoryCacheKey });
					resizeAndSaveImage(i, j);
				}
			}
			return bool;
		} catch (IOException localIOException) {
			L.e(localIOException);
		}
		return false;
	}

	private Bitmap tryLoadBitmap()
			throws LoadAndDisplayImageTask.TaskCancelledException {

		Bitmap bitmap = null;
		try {
			File imageFile = configuration.diskCache.get(uri);
			if (imageFile != null && imageFile.exists()) {
				L.d("Load image from disk cache [%s]",
						new Object[] { memoryCacheKey });
				loadedFrom = LoadedFrom.DISC_CACHE;
				checkTaskNotActual();
				Scheme scheme = Scheme.FILE;
				String path = scheme.wrap(imageFile.getAbsolutePath());
				bitmap = decodeImage(path);
				if (bitmap != null) {
					int width = bitmap.getWidth();
					int height = bitmap.getHeight();
					if (width < 0 || height < 0) {

					} else {
						return bitmap;
					}
				}
			}

			L.d("Load image from network [%s]", new Object[] { memoryCacheKey });
			loadedFrom = LoadedFrom.NETWORK;
			if (options.isCacheOnDisk() && tryCacheImageOnDisk()) {
				File file = configuration.diskCache.get(uri);
				checkTaskNotActual();
				Scheme scheme = Scheme.FILE;
				String path = scheme.wrap(imageFile.getAbsolutePath());
				bitmap = decodeImage(path);
				if (bitmap != null) {
					int width = bitmap.getWidth();
					int height = bitmap.getHeight();
					if (width < 0 || height < 0) {
						fireFailEvent(FailType.DECODING_ERROR, null);
					} else {
						return bitmap;
					}
				}
			}

		} catch (IllegalStateException localIllegalStateException) {
			fireFailEvent(FailReason.FailType.NETWORK_DENIED, null);
		} catch (TaskCancelledException localTaskCancelledException) {
			throw localTaskCancelledException;
		} catch (IOException localIOException) {
			L.e(localIOException);
			fireFailEvent(FailReason.FailType.IO_ERROR, localIOException);
		} catch (OutOfMemoryError localOutOfMemoryError) {
			L.e(localOutOfMemoryError);
			fireFailEvent(FailReason.FailType.OUT_OF_MEMORY,
					localOutOfMemoryError);
		} catch (Throwable localThrowable) {
			L.e(localThrowable);
			fireFailEvent(FailReason.FailType.UNKNOWN, localThrowable);
		}
		return bitmap;
	}

	private boolean waitIfPaused() {
		AtomicBoolean pause = this.engine.getPause();
		if (pause.get()) {
			synchronized (this.engine.getPauseLock()) {
				if (pause.get())
					L.d("ImageLoader is paused. Waiting...  [%s]",
							new Object[] { this.memoryCacheKey });
				try {
					this.engine.getPauseLock().wait();
					L.d(".. Resume loading [%s]",
							new Object[] { this.memoryCacheKey });
					return isTaskNotActual();
				} catch (InterruptedException localInterruptedException) {
					L.e("Task was interrupted [%s]",
							new Object[] { this.memoryCacheKey });
					return true;
				}
			}
		}
		return isTaskNotActual();
	}

	String getLoadingUri() {
		return this.uri;
	}

	public boolean onBytesCopied(int current, int total) {
		return (this.syncLoading) || (fireProgressEvent(current, total));
	}

	public void run() {
		if (waitIfPaused())
			;
		while (delayIfNeed())
			return;
		ReentrantLock localReentrantLock = this.imageLoadingInfo.loadFromUriLock;
		L.d("Start display image task [%s]",
				new Object[] { this.memoryCacheKey });
		if (localReentrantLock.isLocked())
			L.d("Image already is loading. Waiting... [%s]",
					new Object[] { this.memoryCacheKey });
		localReentrantLock.lock();
		try {
			checkTaskNotActual();
			Object localObject3 = this.configuration.memoryCache
					.get(this.memoryCacheKey);
			Object localObject1;
			if ((localObject3 == null)
					|| (((Bitmap) localObject3).isRecycled())) {
				localObject3 = tryLoadBitmap();
				if (localObject3 == null)
					return;
				checkTaskNotActual();
				checkTaskInterrupted();
				localObject1 = localObject3;
				if (this.options.shouldPreProcess()) {
					L.d("PreProcess image before caching in memory [%s]",
							new Object[] { this.memoryCacheKey });
					localObject3 = this.options.getPreProcessor().process(
							(Bitmap) localObject3);
					localObject1 = localObject3;
					if (localObject3 == null) {
						L.e("Pre-processor returned null [%s]",
								new Object[] { this.memoryCacheKey });
						localObject1 = localObject3;
					}
				}
				localObject3 = localObject1;
				if (localObject1 != null) {
					localObject3 = localObject1;
					if (this.options.isCacheInMemory()) {
						L.d("Cache image in memory [%s]",
								new Object[] { this.memoryCacheKey });
						this.configuration.memoryCache.put(this.memoryCacheKey,
								localObject1);
						localObject3 = localObject1;
					}
				}
			}
			while (true) {
				localObject1 = localObject3;
				if (localObject3 != null) {
					localObject1 = localObject3;
					if (this.options.shouldPostProcess()) {
						L.d("PostProcess image before displaying [%s]",
								new Object[] { this.memoryCacheKey });
						localObject3 = this.options.getPostProcessor().process(
								(Bitmap) localObject3);
						localObject1 = localObject3;
						if (localObject3 == null) {
							L.e("Post-processor returned null [%s]",
									new Object[] { this.memoryCacheKey });
							localObject1 = localObject3;
						}
					}
				}
				checkTaskNotActual();
				checkTaskInterrupted();
				localReentrantLock.unlock();
				runTask(new DisplayBitmapTask(localObject1,
						this.imageLoadingInfo, this.engine, this.loadedFrom),
						this.syncLoading, this.handler, this.engine);
				return;
				this.loadedFrom = LoadedFrom.MEMORY_CACHE;
				L.d("...Get cached bitmap from memory after waiting. [%s]",
						new Object[] { this.memoryCacheKey });
			}
		} catch (TaskCancelledException localTaskCancelledException) {
			fireCancelEvent();
			return;
		} finally {
			localReentrantLock.unlock();
		}
	}

	class TaskCancelledException extends Exception {
		TaskCancelledException() {
		}
	}
}