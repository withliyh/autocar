package com.cubic.autohome.common.view.image.core;

import com.cubic.autohome.common.view.image.core.assist.LoadedFrom;

import android.graphics.Bitmap;
import android.os.Handler;

public class ProcessAndDisplayImageTask implements Runnable {

	private final Bitmap bitmap;
	private final ImageLoaderEngine engine;
	private final Handler handler;
	private final ImageLoadingInfo imageLoadingInfo;

	public ProcessAndDisplayImageTask(ImageLoaderEngine paramImageLoaderEngine,
			Bitmap paramBitmap, ImageLoadingInfo paramImageLoadingInfo,
			Handler paramHandler) {
		this.engine = paramImageLoaderEngine;
		this.bitmap = paramBitmap;
		this.imageLoadingInfo = paramImageLoadingInfo;
		this.handler = paramHandler;
	}

	@Override
	public void run() {
		 LoadAndDisplayImageTask.runTask(new DisplayBitmapTask(this.imageLoadingInfo.options.getPostProcessor().process(this.bitmap), this.imageLoadingInfo, this.engine, LoadedFrom.MEMORY_CACHE), this.imageLoadingInfo.options.isSyncLoading(), this.handler, this.engine);
	}

}
