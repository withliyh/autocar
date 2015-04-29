package com.cubic.autohome.common.view.image.core;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;

import com.cubic.autohome.common.view.image.cache.disc.DiskCache;
import com.cubic.autohome.common.view.image.cache.disc.impl.UnlimitedDiskCache;
import com.cubic.autohome.common.view.image.cache.disc.impl.ext.LruDiskCache;
import com.cubic.autohome.common.view.image.cache.disc.naming.FileNameGenerator;
import com.cubic.autohome.common.view.image.cache.disc.naming.HashCodeFileNameGenerator;
import com.cubic.autohome.common.view.image.cache.memory.MemoryCache;
import com.cubic.autohome.common.view.image.cache.memory.impl.LruMemoryCache;
import com.cubic.autohome.common.view.image.core.assist.QueueProcessingType;
import com.cubic.autohome.common.view.image.core.assist.deque.LIFOLinkedBlockingDeque;
import com.cubic.autohome.common.view.image.core.decode.BaseImageDecoder;
import com.cubic.autohome.common.view.image.core.decode.ImageDecoder;
import com.cubic.autohome.common.view.image.core.display.BitmapDisplayer;
import com.cubic.autohome.common.view.image.core.display.SimpleBitmapDisplayer;
import com.cubic.autohome.common.view.image.core.download.BaseImageDownloader;
import com.cubic.autohome.common.view.image.core.download.ImageDownloader;
import com.cubic.autohome.common.view.image.utils.L;
import com.cubic.autohome.common.view.image.utils.StorageUtils;

public class DefaultConfigurationFactory {
	public static BitmapDisplayer createBitmapDisplayer() {
		return new SimpleBitmapDisplayer();
	}

	public static DiskCache createDiskCache(Context paramContext,
			FileNameGenerator paramFileNameGenerator, long paramLong,
			int paramInt) {
		File localFile = createReserveDiskCacheDir(paramContext);
		if ((paramLong > 0L) || (paramInt > 0)) {
			Object localObject = StorageUtils
					.getIndividualCacheDirectory(paramContext);
			try {
				DiskCache lruDiskCache = new LruDiskCache((File) localObject, localFile,
						paramFileNameGenerator, paramLong, paramInt);
				return lruDiskCache;
			} catch (IOException localIOException) {
				L.e(localIOException);
			}
		}
		return new UnlimitedDiskCache(
				StorageUtils.getCacheDirectory(paramContext), localFile,
				paramFileNameGenerator);
	}

	public static Executor createExecutor(int paramInt1, int paramInt2,
			QueueProcessingType paramQueueProcessingType) {
		return createTaskDistributor();
	}

	public static FileNameGenerator createFileNameGenerator() {
		return new HashCodeFileNameGenerator();
	}

	public static ImageDecoder createImageDecoder(boolean paramBoolean) {
		return new BaseImageDecoder(paramBoolean);
	}

	public static ImageDownloader createImageDownloader(Context paramContext) {
		return new BaseImageDownloader(paramContext);
	}

	public static MemoryCache createMemoryCache(Context paramContext,
			int paramInt) {
		int i = paramInt;
		if (paramInt == 0) {
			ActivityManager localActivityManager = (ActivityManager) paramContext
					.getSystemService("activity");
			i = localActivityManager.getMemoryClass();
			paramInt = i;
			if (hasHoneycomb()) {
				paramInt = i;
				if (isLargeHeap(paramContext))
					paramInt = getLargeMemoryClass(localActivityManager);
			}
			i = 1048576 * paramInt / 8;
		}
		return new LruMemoryCache();
	}

	private static File createReserveDiskCacheDir(Context paramContext) {
		File parent = StorageUtils.getCacheDirectory(paramContext, false);
		File localFile = new File(parent, "uil-images");
		if ((localFile.exists()) || (localFile.mkdir()));
		return localFile;
	}

	public static Executor createTaskDistributor() {
		return Executors.newCachedThreadPool(createThreadFactory(5,
				"uil-pool-d-"));
	}

	private static ThreadFactory createThreadFactory(int paramInt,
			String paramString) {
		return new DefaultThreadFactory(paramInt, paramString);
	}

	private static int getLargeMemoryClass(ActivityManager paramActivityManager) {
		return paramActivityManager.getLargeMemoryClass();
	}

	private static boolean hasHoneycomb() {
		return Build.VERSION.SDK_INT >= 11;
	}

	private static boolean isLargeHeap(Context paramContext) {
		return (paramContext.getApplicationInfo().flags & 0x100000) != 0;
	}

	private static class DefaultThreadFactory implements ThreadFactory {
		private static final AtomicInteger poolNumber = new AtomicInteger(1);
		private final ThreadGroup group;
		private final String namePrefix;
		private final AtomicInteger threadNumber = new AtomicInteger(1);
		private final int threadPriority;

		DefaultThreadFactory(int paramInt, String paramString) {
			this.threadPriority = paramInt;
			this.group = Thread.currentThread().getThreadGroup();
			this.namePrefix = (paramString + poolNumber.getAndIncrement() + "-thread-");
		}

		public Thread newThread(Runnable paramRunnable) {
			Thread thread = new Thread(this.group, paramRunnable,
					this.namePrefix + this.threadNumber.getAndIncrement(), 0L);
			if (thread.isDaemon())
				thread.setDaemon(false);
			thread.setPriority(this.threadPriority);
			return thread;
		}
	}
}
