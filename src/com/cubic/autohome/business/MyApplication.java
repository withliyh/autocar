package com.cubic.autohome.business;

import java.io.File;
import java.io.IOException;

import com.cubic.autohome.common.skin.AtSkinObserable;
import com.cubic.autohome.common.view.image.cache.disc.impl.ext.LruDiskCache;
import com.cubic.autohome.common.view.image.cache.disc.naming.Md5FileNameGenerator;
import com.cubic.autohome.common.view.image.cache.memory.impl.WeakMemoryCache;
import com.cubic.autohome.common.view.image.core.DisplayImageOptions;
import com.cubic.autohome.common.view.image.core.ImageLoader;
import com.cubic.autohome.common.view.image.core.ImageLoaderConfiguration;
import com.cubic.autohome.common.view.image.core.assist.ImageScaleType;
import com.cubic.autohome.common.view.image.core.assist.QueueProcessingType;
import com.cubic.autohome.common.view.image.utils.L;
import com.cubic.autohome.common.view.image.utils.StorageUtils;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;

public class MyApplication extends Application {

	private static MyApplication instance;
	private AtSkinObserable mAtSkinObserable;
	private ImageLoaderConfiguration mConfig;
	
	
	public static MyApplication getInstance() {

			MyApplication tis = instance;
			return tis;

	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		instance = this;
	}
	
	
	public AtSkinObserable getAtSkinObserable() {
		this.mAtSkinObserable = AtSkinObserable.getInstance();
		return this.mAtSkinObserable;
	}
	
	public ImageLoaderConfiguration getImageLoaderConfig() {
		File localFile = null;
		DisplayImageOptions localDisplayImageOptions = null;
		if (this.mConfig == null) {
			localFile = StorageUtils.getOwnCacheDirectory(this,
					"/autohomemain/img");
			localDisplayImageOptions = new DisplayImageOptions.Builder()
					.cacheOnDisk(true).cacheInMemory(true)
					.imageScaleType(ImageScaleType.NONE).build();
			StorageUtils.getOwnCacheDirectory(this, Environment
					.getDataDirectory().getAbsolutePath()
					+ File.separator
					+ "cacheDir");
		}
		try {
			this.mConfig = new ImageLoaderConfiguration.Builder(this)
					.threadPoolSize(5)
					.taskExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
					.taskExecutorForCachedImages(AsyncTask.THREAD_POOL_EXECUTOR)
					.denyCacheImageMultipleSizesInMemory()
					.memoryCache(new WeakMemoryCache())
					.diskCache(
							new LruDiskCache(localFile,
									new Md5FileNameGenerator(), 52428800L))
					.tasksProcessingOrder(QueueProcessingType.FIFO)
					.defaultDisplayImageOptions(localDisplayImageOptions)
					.writeDebugLogs().build();
			return this.mConfig;
		} catch (IOException localIOException) {
			while (true)
				localIOException.printStackTrace();
		}
	}
	
	public void initImageConfig(Context paramContext) {
		int i = (int) Runtime.getRuntime().maxMemory() / 8;
		ImageLoader.getInstance().init(getImageLoaderConfig());
		L.writeLogs(false);
	}
}
