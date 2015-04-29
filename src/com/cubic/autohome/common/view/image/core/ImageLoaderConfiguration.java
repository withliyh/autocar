package com.cubic.autohome.common.view.image.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executor;

import com.cubic.autohome.common.view.image.cache.disc.DiskCache;
import com.cubic.autohome.common.view.image.cache.disc.naming.FileNameGenerator;
import com.cubic.autohome.common.view.image.cache.memory.MemoryCache;
import com.cubic.autohome.common.view.image.cache.memory.impl.FuzzyKeyMemoryCache;
import com.cubic.autohome.common.view.image.core.assist.ImageSize;
import com.cubic.autohome.common.view.image.core.assist.QueueProcessingType;
import com.cubic.autohome.common.view.image.core.decode.ImageDecoder;
import com.cubic.autohome.common.view.image.core.download.ImageDownloader;
import com.cubic.autohome.common.view.image.core.process.BitmapProcessor;
import com.cubic.autohome.common.view.image.utils.L;
import com.cubic.autohome.common.view.image.utils.MemoryCacheUtils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public final class ImageLoaderConfiguration {

	  final boolean customExecutor;
	  final boolean customExecutorForCachedImages;
	  final ImageDecoder decoder;
	  final DisplayImageOptions defaultDisplayImageOptions;
	  final DiskCache diskCache;
	  final ImageDownloader downloader;
	  final int maxImageHeightForDiskCache;
	  final int maxImageHeightForMemoryCache;
	  final int maxImageWidthForDiskCache;
	  final int maxImageWidthForMemoryCache;
	  final MemoryCache memoryCache;
	  final ImageDownloader networkDeniedDownloader;
	  final BitmapProcessor processorForDiskCache;
	  final Resources resources;
	  final ImageDownloader slowNetworkDownloader;
	  final Executor taskExecutor;
	  final Executor taskExecutorForCachedImages;
	  final QueueProcessingType tasksProcessingType;
	  final int threadPoolSize;
	  final int threadPriority;

	private ImageLoaderConfiguration(Builder paramBuilder) {
		this.resources = paramBuilder.context.getResources();
		this.maxImageWidthForMemoryCache = paramBuilder.maxImageWidthForMemoryCache;
		this.maxImageHeightForMemoryCache = paramBuilder.maxImageHeightForMemoryCache;
		this.maxImageWidthForDiskCache = paramBuilder.maxImageWidthForDiskCache;
		this.maxImageHeightForDiskCache = paramBuilder.maxImageHeightForDiskCache;
		this.processorForDiskCache = paramBuilder.processorForDiskCache;
		this.taskExecutor = paramBuilder.taskExecutor;
		this.taskExecutorForCachedImages = paramBuilder.taskExecutorForCachedImages;
		this.threadPoolSize = paramBuilder.threadPoolSize;
		this.threadPriority = paramBuilder.threadPriority;
		this.tasksProcessingType = paramBuilder.tasksProcessingType;
		this.diskCache = paramBuilder.diskCache;
		this.memoryCache = paramBuilder.memoryCache;
		this.defaultDisplayImageOptions = paramBuilder.defaultDisplayImageOptions;
		this.downloader = paramBuilder.downloader;
		this.decoder = paramBuilder.decoder;
		this.customExecutor = paramBuilder.customExecutor;
		this.customExecutorForCachedImages = paramBuilder.customExecutorForCachedImages;
		this.networkDeniedDownloader = new NetworkDeniedImageDownloader(
				this.downloader);
		this.slowNetworkDownloader = new SlowNetworkImageDownloader(
				this.downloader);
		L.writeDebugLogs(paramBuilder.writeLogs);
	}

	ImageSize getMaxImageSize() {
		DisplayMetrics localDisplayMetrics = this.resources.getDisplayMetrics();
		int j = this.maxImageWidthForMemoryCache;
		int i = j;
		if (j <= 0)
			i = localDisplayMetrics.widthPixels;
		int k = this.maxImageHeightForMemoryCache;
		j = k;
		if (k <= 0)
			j = localDisplayMetrics.heightPixels;
		return new ImageSize(i, j);
	}

	public static class Builder {
		public static final QueueProcessingType DEFAULT_TASK_PROCESSING_TYPE = QueueProcessingType.FIFO;
		private Context context;
		private boolean customExecutor = false;
		private boolean customExecutorForCachedImages = false;
		private ImageDecoder decoder;
		private DisplayImageOptions defaultDisplayImageOptions = null;
		private boolean denyCacheImageMultipleSizesInMemory = false;
		private DiskCache diskCache = null;
		private int diskCacheFileCount = 0;
		private FileNameGenerator diskCacheFileNameGenerator = null;
		private long diskCacheSize = 0L;
		private ImageDownloader downloader = null;
		private int maxImageHeightForDiskCache = 0;
		private int maxImageHeightForMemoryCache = 0;
		private int maxImageWidthForDiskCache = 0;
		private int maxImageWidthForMemoryCache = 0;
		private MemoryCache memoryCache = null;
		private int memoryCacheSize = 0;
		private BitmapProcessor processorForDiskCache = null;
		private Executor taskExecutor = null;
		private Executor taskExecutorForCachedImages = null;
		private QueueProcessingType tasksProcessingType = DEFAULT_TASK_PROCESSING_TYPE;
		private int threadPoolSize = 3;
		private int threadPriority = 3;
		private boolean writeLogs = false;

		public Builder(Context paramContext) {
			this.context = paramContext.getApplicationContext();
		}

		private void initEmptyFieldsWithDefaultValues() {
			if (this.taskExecutor == null) {
				this.taskExecutor = DefaultConfigurationFactory.createExecutor(
						this.threadPoolSize, this.threadPriority,
						this.tasksProcessingType);
				if (this.taskExecutorForCachedImages != null)
					;
				this.taskExecutorForCachedImages = DefaultConfigurationFactory
						.createExecutor(this.threadPoolSize,
								this.threadPriority, this.tasksProcessingType);
			}
			while (true) {
				if (this.diskCache == null) {
					if (this.diskCacheFileNameGenerator == null)
						this.diskCacheFileNameGenerator = DefaultConfigurationFactory
								.createFileNameGenerator();
					this.diskCache = DefaultConfigurationFactory
							.createDiskCache(this.context,
									this.diskCacheFileNameGenerator,
									this.diskCacheSize, this.diskCacheFileCount);
				}
				if (this.memoryCache == null)
					this.memoryCache = DefaultConfigurationFactory
							.createMemoryCache(this.context,
									this.memoryCacheSize);
				if (this.denyCacheImageMultipleSizesInMemory)
					this.memoryCache = new FuzzyKeyMemoryCache(
							this.memoryCache,
							MemoryCacheUtils.createFuzzyKeyComparator());
				if (this.downloader == null)
					this.downloader = DefaultConfigurationFactory
							.createImageDownloader(this.context);
				if (this.decoder == null)
					this.decoder = DefaultConfigurationFactory
							.createImageDecoder(this.writeLogs);
				if (this.defaultDisplayImageOptions == null)
					this.defaultDisplayImageOptions = DisplayImageOptions
							.createSimple();
				this.customExecutor = true;
				break;
			}
		}

		public ImageLoaderConfiguration build() {
			initEmptyFieldsWithDefaultValues();
			return new ImageLoaderConfiguration(this);
		}

		public Builder defaultDisplayImageOptions(
				DisplayImageOptions paramDisplayImageOptions) {
			this.defaultDisplayImageOptions = paramDisplayImageOptions;
			return this;
		}

		public Builder denyCacheImageMultipleSizesInMemory() {
			this.denyCacheImageMultipleSizesInMemory = true;
			return this;
		}

		public Builder diskCache(DiskCache paramDiskCache) {
			if ((this.diskCacheSize > 0L) || (this.diskCacheFileCount > 0))
				L.w("diskCache(), diskCacheSize() and diskCacheFileCount calls overlap each other",
						new Object[0]);
			if (this.diskCacheFileNameGenerator != null)
				L.w("diskCache() and diskCacheFileNameGenerator() calls overlap each other",
						new Object[0]);
			this.diskCache = paramDiskCache;
			return this;
		}

		public Builder memoryCache(MemoryCache paramMemoryCache) {
			if (this.memoryCacheSize != 0)
				L.w("memoryCache() and memoryCacheSize() calls overlap each other",
						new Object[0]);
			this.memoryCache = paramMemoryCache;
			return this;
		}

		public Builder taskExecutor(Executor paramExecutor) {
			if ((this.threadPoolSize != 3)
					|| (this.threadPriority != 3)
					|| (this.tasksProcessingType != DEFAULT_TASK_PROCESSING_TYPE))
				L.w("threadPoolSize(), threadPriority() and tasksProcessingOrder() calls can overlap taskExecutor() and taskExecutorForCachedImages() calls.",
						new Object[0]);
			this.taskExecutor = paramExecutor;
			return this;
		}

		public Builder taskExecutorForCachedImages(Executor paramExecutor) {
			if ((this.threadPoolSize != 3)
					|| (this.threadPriority != 3)
					|| (this.tasksProcessingType != DEFAULT_TASK_PROCESSING_TYPE))
				L.w("threadPoolSize(), threadPriority() and tasksProcessingOrder() calls can overlap taskExecutor() and taskExecutorForCachedImages() calls.",
						new Object[0]);
			this.taskExecutorForCachedImages = paramExecutor;
			return this;
		}

		public Builder tasksProcessingOrder(
				QueueProcessingType paramQueueProcessingType) {
			if ((this.taskExecutor != null)
					|| (this.taskExecutorForCachedImages != null))
				L.w("threadPoolSize(), threadPriority() and tasksProcessingOrder() calls can overlap taskExecutor() and taskExecutorForCachedImages() calls.",
						new Object[0]);
			this.tasksProcessingType = paramQueueProcessingType;
			return this;
		}

		public Builder threadPoolSize(int paramInt) {
			if ((this.taskExecutor != null)
					|| (this.taskExecutorForCachedImages != null))
				L.w("threadPoolSize(), threadPriority() and tasksProcessingOrder() calls can overlap taskExecutor() and taskExecutorForCachedImages() calls.",
						new Object[0]);
			this.threadPoolSize = paramInt;
			return this;
		}

		public Builder writeDebugLogs() {
			this.writeLogs = true;
			return this;
		}
	}

	private static class NetworkDeniedImageDownloader implements
			ImageDownloader {
		private final ImageDownloader wrappedDownloader;

		public NetworkDeniedImageDownloader(ImageDownloader paramImageDownloader) {
			this.wrappedDownloader = paramImageDownloader;
		}

		public InputStream getStream(String paramString, Object paramObject)
				throws IOException {
			switch (com.cubic.autohome.common.view.image.core.download.ImageDownloader.Scheme
					.ofUri(paramString).ordinal()) {
			default:
				return this.wrappedDownloader.getStream(paramString,
						paramObject);
			case 1:
			case 2:
			}
			throw new IllegalStateException();
		}
	}

	private static class SlowNetworkImageDownloader implements ImageDownloader {
		private final ImageDownloader wrappedDownloader;

		public SlowNetworkImageDownloader(ImageDownloader paramImageDownloader) {
			this.wrappedDownloader = paramImageDownloader;
		}

		public InputStream getStream(String paramString, Object paramObject)
				throws IOException {
			InputStream in  = this.wrappedDownloader.getStream(paramString,
					paramObject);
			switch (com.cubic.autohome.common.view.image.core.download.ImageDownloader.Scheme
					.ofUri(paramString).ordinal()) {
			default:
				return in;
			case 1:
			case 2:
			}
			//return new FlushedInputStream(paramObject);
			return this.wrappedDownloader.getStream(paramString,
					paramObject);
		}
	}
}