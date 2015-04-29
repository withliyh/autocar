package com.cubic.autohome.common.view.image.core;

import com.cubic.autohome.common.view.image.core.assist.LoadedFrom;
import com.cubic.autohome.common.view.image.core.display.BitmapDisplayer;
import com.cubic.autohome.common.view.image.core.imageaware.ImageAware;
import com.cubic.autohome.common.view.image.core.listener.ImageLoadingListener;
import com.cubic.autohome.common.view.image.utils.L;

import android.graphics.Bitmap;

public class DisplayBitmapTask implements Runnable {

	private final Bitmap bitmap;
	private final BitmapDisplayer displayer;
	private final ImageLoaderEngine engine;
	private final ImageAware imageAware;
	private final String imageUri;
	private final ImageLoadingListener listener;
	private final LoadedFrom loadedFrom;
	private final String memoryCacheKey;

	public DisplayBitmapTask(Bitmap paramBitmap,
			ImageLoadingInfo paramImageLoadingInfo,
			ImageLoaderEngine paramImageLoaderEngine, LoadedFrom paramLoadedFrom) {
		this.bitmap = paramBitmap;
		this.imageUri = paramImageLoadingInfo.uri;
		this.imageAware = paramImageLoadingInfo.imageAware;
		this.memoryCacheKey = paramImageLoadingInfo.memoryCacheKey;
		this.displayer = paramImageLoadingInfo.options.getDisplayer();
		this.listener = paramImageLoadingInfo.listener;
		this.engine = paramImageLoaderEngine;
		this.loadedFrom = paramLoadedFrom;
	}

	private boolean isViewWasReused() {
		String str = this.engine.getLoadingUriForView(this.imageAware);
		return !this.memoryCacheKey.equals(str);
	}

	@Override
	public void run() {
		if (this.imageAware.isCollected()) {
			L.d("ImageAware was collected by GC. Task is cancelled. [%s]",
					new Object[] { this.memoryCacheKey });
			this.listener.onLoadingCancelled(this.imageUri,
					this.imageAware.getWrappedView());
			return;
		}
		if (isViewWasReused()) {
			L.d("ImageAware is reused for another image. Task is cancelled. [%s]",
					new Object[] { this.memoryCacheKey });
			this.listener.onLoadingCancelled(this.imageUri,
					this.imageAware.getWrappedView());
			return;
		}
		L.d("Display image in ImageAware (loaded from %1$s) [%2$s]",
				new Object[] { this.loadedFrom, this.memoryCacheKey });
		this.displayer.display(this.bitmap, this.imageAware, this.loadedFrom);
		this.engine.cancelDisplayTaskFor(this.imageAware);
		this.listener.onLoadingComplete(this.imageUri,
				this.imageAware.getWrappedView(), this.bitmap);
	}

}
