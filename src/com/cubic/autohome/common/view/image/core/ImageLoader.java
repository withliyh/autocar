package com.cubic.autohome.common.view.image.core;

import java.util.concurrent.locks.ReentrantLock;

import android.database.DefaultDatabaseErrorHandler;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.cubic.autohome.business.MyApplication;
import com.cubic.autohome.common.view.image.cache.disc.DiskCache;
import com.cubic.autohome.common.view.image.cache.memory.MemoryCache;
import com.cubic.autohome.common.view.image.core.assist.ImageSize;
import com.cubic.autohome.common.view.image.core.assist.ViewScaleType;
import com.cubic.autohome.common.view.image.core.imageaware.ImageAware;
import com.cubic.autohome.common.view.image.core.imageaware.ImageViewAware;
import com.cubic.autohome.common.view.image.core.imageaware.NonViewAware;
import com.cubic.autohome.common.view.image.core.listener.ImageLoadingListener;
import com.cubic.autohome.common.view.image.core.listener.ImageLoadingProgressListener;
import com.cubic.autohome.common.view.image.core.listener.SimpleImageLoadingListener;
import com.cubic.autohome.common.view.image.utils.ImageSizeUtils;
import com.cubic.autohome.common.view.image.utils.L;
import com.cubic.autohome.common.view.image.utils.MemoryCacheUtils;

public class ImageLoader {
	public static final String TAG = ImageLoader.class.getSimpleName();
	private static volatile ImageLoader instance;
	private ImageLoaderConfiguration configuration;
	private final ImageLoadingListener emptyListener = new SimpleImageLoadingListener();
	private ImageLoaderEngine engine;

	private void checkConfiguration() {
		if (this.configuration == null)
			this.configuration = MyApplication.getInstance()
					.getImageLoaderConfig();
	}

	private static Handler defineHandler(DisplayImageOptions displayImageOptions) {
		Handler handler = displayImageOptions.getHandler();
		if (displayImageOptions.isSyncLoading()) {
			displayImageOptions = null;
		}
		if (handler != null) {
			return handler;
		} else {
			return new Handler();
		}
	}

	public static ImageLoader getInstance() {
		if (instance == null) {
			instance = new ImageLoader();
		}
		return instance;
	}

	public void clearMemoryCache() {
		checkConfiguration();
		this.configuration.memoryCache.clear();
	}

	public void displayImage(String paramString, ImageView paramImageView,
			DisplayImageOptions paramDisplayImageOptions,
			ImageLoadingListener paramImageLoadingListener,
			ImageLoadingProgressListener paramImageLoadingProgressListener) {
		displayImage(paramString, new ImageViewAware(paramImageView),
				paramDisplayImageOptions, paramImageLoadingListener,
				paramImageLoadingProgressListener);
	}

	public void displayImage(String paramString, ImageView paramImageView,
			ImageLoadingListener paramImageLoadingListener) {
		displayImage(paramString, new ImageViewAware(paramImageView), null,
				paramImageLoadingListener, null);
	}

	public DiskCache getDiskCache() {
		checkConfiguration();
		return this.configuration.diskCache;
	}

	public MemoryCache getMemoryCache() {
		checkConfiguration();
		return this.configuration.memoryCache;
	}

	public void init(ImageLoaderConfiguration paramImageLoaderConfiguration) {
		if (paramImageLoaderConfiguration == null)
			try {
				throw new IllegalArgumentException(
						"ImageLoader configuration can not be initialized with null");
			} finally {
			}
		if (this.configuration == null) {
			L.d("Initialize ImageLoader with configuration", new Object[0]);
			this.engine = new ImageLoaderEngine(paramImageLoaderConfiguration);
			this.configuration = paramImageLoaderConfiguration;
		}

	}

	public boolean isInited() {
		return this.configuration != null;
	}

	public void loadImage(String paramString, ImageSize paramImageSize,
			DisplayImageOptions paramDisplayImageOptions,
			ImageLoadingListener paramImageLoadingListener) {
		loadImage(paramString, paramImageSize, paramDisplayImageOptions,
				paramImageLoadingListener, null);
	}

	public void loadImage(String paramString, ImageSize paramImageSize,
			DisplayImageOptions paramDisplayImageOptions,
			ImageLoadingListener paramImageLoadingListener,
			ImageLoadingProgressListener paramImageLoadingProgressListener) {
		checkConfiguration();
		ImageSize localImageSize = paramImageSize;
		if (paramImageSize == null)
			localImageSize = this.configuration.getMaxImageSize();
		if (paramDisplayImageOptions == null) {
			paramDisplayImageOptions = DisplayImageOptions.createSimple();
		}
		NonViewAware aware = new NonViewAware(paramString,localImageSize, ViewScaleType.CROP);
		displayImage(paramString, aware, paramDisplayImageOptions,
					paramImageLoadingListener,
					paramImageLoadingProgressListener);
	}
	
	public void displayImage(String uri, ImageAware imageAware, DisplayImageOptions options,
			ImageLoadingListener listener, ImageLoadingProgressListener progressListener) {
		
		checkConfiguration();
		if (imageAware == null) {
			throw new IllegalArgumentException("Wrong arguments were passed to displayImage() method (ImageView reference must not be null");
		}
		if (listener == null) {
			listener = emptyListener;
		}
		
		if (options == null) {
			ImageLoaderConfiguration configureation = this.configuration;
			options = configureation.defaultDisplayImageOptions;
		}
		if (TextUtils.isEmpty(uri)) {//cond_4
			
			this.engine.cancelDisplayTaskFor(imageAware);
			View view = imageAware.getWrappedView();
			listener.onLoadingStarted(uri, view);
			boolean shouldShowImageForEmptyUri = options.shouldShowImageForEmptyUri();
			Drawable drawable = null;
			if (!shouldShowImageForEmptyUri) {//cond_3
				drawable = options.getImageForEmptyUri(this.configuration.resources);
				imageAware.setImageDrawable(drawable);
				view = imageAware.getWrappedView();
				listener.onLoadingComplete(uri, view, null);
				return;
			}
			imageAware.setImageDrawable(drawable);
		}
		ImageSize imageSize = this.configuration.getMaxImageSize();
		imageSize = ImageSizeUtils.defineTargetSizeForView(imageAware, imageSize);
		String key = MemoryCacheUtils.generateKey(uri, imageSize);
		this.engine.prepareDisplayTaskFor(imageAware, key);
		View view = imageAware.getWrappedView();
		listener.onLoadingStarted(uri, view);
		Bitmap bitmap = this.configuration.memoryCache.get(key);
		if (bitmap != null) {
			if(!bitmap.isRecycled()) {
				boolean postProcess = options.shouldPostProcess();
				if (postProcess) {
					ReentrantLock  lock = this.engine.getLockForUri(uri);
					ImageLoadingInfo imageLoadingInfo = new ImageLoadingInfo(uri,imageAware,imageSize, key,options,listener,progressListener,lock);
					ProcessAndDisplayImageTask task = new ProcessAndDisplayImageTask(engine, bitmap, imageLoadingInfo, defineHandler(options));
					if (options.isSyncLoading()) { //:cond_5
						task.run();
					} else {
						this.engine.submit(task);
					}
					
				}
				
			}
			
		}
	}
	

//	public void displayImage(String paramString, ImageAware paramImageAware,
//			DisplayImageOptions paramDisplayImageOptions,
//			ImageLoadingListener paramImageLoadingListener,
//			ImageLoadingProgressListener paramImageLoadingProgressListener) {
//		checkConfiguration();
//		if (paramImageAware == null)
//			throw new IllegalArgumentException(
//					"Wrong arguments were passed to displayImage() method (ImageView reference must not be null)");
//		ImageLoadingListener localImageLoadingListener = paramImageLoadingListener;
//		if (paramImageLoadingListener == null)
//			localImageLoadingListener = this.emptyListener;
//		
//		if (paramDisplayImageOptions == null)
//			paramDisplayImageOptions = this.configuration.defaultDisplayImageOptions;
//		if (TextUtils.isEmpty(paramString)) {
//			this.engine.cancelDisplayTaskFor(paramImageAware);
//			localImageLoadingListener.onLoadingStarted(paramString,
//					paramImageAware.getWrappedView());
//			if (paramDisplayImageOptions.shouldShowImageForEmptyUri())
//				paramImageAware.setImageDrawable(paramDisplayImageOptions
//						.getImageForEmptyUri(this.configuration.resources));
//			while (true) {
//				localImageLoadingListener.onLoadingComplete(paramString,
//						paramImageAware.getWrappedView(), null);
//				return;
//				paramImageAware.setImageDrawable(null);
//			}
//		}
//		ImageSize localImageSize = ImageSizeUtils.defineTargetSizeForView(
//				paramImageAware, this.configuration.getMaxImageSize());
//		String str = MemoryCacheUtils.generateKey(paramString, localImageSize);
//		this.engine.prepareDisplayTaskFor(paramImageAware, str);
//		localImageLoadingListener.onLoadingStarted(paramString,
//				paramImageAware.getWrappedView());
//		Bitmap bitmap = this.configuration.memoryCache.get(str);
//		if ((bitmap != null)
//				&& (!bitmap.isRecycled())) {
//			L.d("Load image from memory cache [%s]", new Object[] { str });
//			if (paramImageLoadingListener.shouldPostProcess()) {
//				paramString = new ImageLoadingInfo(paramString,
//						paramImageAware, localImageSize, str,
//						paramImageLoadingListener, localImageLoadingListener,
//						paramImageLoadingProgressListener,
//						this.engine.getLockForUri(paramString));
//				paramString = new ProcessAndDisplayImageTask(this.engine,
//						paramDisplayImageOptions, paramString,
//						defineHandler(paramImageLoadingListener));
//				if (paramImageLoadingListener.isSyncLoading()) {
//					paramString.run();
//					return;
//				}
//				this.engine.submit(paramString);
//				return;
//			}
//			paramImageLoadingListener.getDisplayer().display(
//					paramDisplayImageOptions, paramImageAware,
//					LoadedFrom.MEMORY_CACHE);
//			localImageLoadingListener.onLoadingComplete(paramString,
//					paramImageAware.getWrappedView(), paramDisplayImageOptions);
//			return;
//		}
//		if (paramImageLoadingListener.shouldShowImageOnLoading())
//			paramImageAware.setImageDrawable(paramImageLoadingListener
//					.getImageOnLoading(this.configuration.resources));
//		while (true) {
//			paramString = new ImageLoadingInfo(paramString, paramImageAware,
//					localImageSize, str, paramImageLoadingListener,
//					localImageLoadingListener,
//					paramImageLoadingProgressListener,
//					this.engine.getLockForUri(paramString));
//			paramString = new LoadAndDisplayImageTask(this.engine, paramString,
//					defineHandler(paramImageLoadingListener));
//			if (!paramImageLoadingListener.isSyncLoading())
//				break;
//			paramString.run();
//			return;
//			if (paramImageLoadingListener.isResetViewBeforeLoading())
//				paramImageAware.setImageDrawable(null);
//		}
//		this.engine.submit(paramString);
//	}
}
