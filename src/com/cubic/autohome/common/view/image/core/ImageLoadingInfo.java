package com.cubic.autohome.common.view.image.core;

import com.cubic.autohome.common.view.image.core.assist.ImageSize;
import com.cubic.autohome.common.view.image.core.imageaware.ImageAware;
import com.cubic.autohome.common.view.image.core.listener.ImageLoadingListener;
import com.cubic.autohome.common.view.image.core.listener.ImageLoadingProgressListener;
import java.util.concurrent.locks.ReentrantLock;

final class ImageLoadingInfo {
	final ImageAware imageAware;
	final ImageLoadingListener listener;
	final ReentrantLock loadFromUriLock;
	final String memoryCacheKey;
	final DisplayImageOptions options;
	final ImageLoadingProgressListener progressListener;
	final ImageSize targetSize;
	final String uri;

	public ImageLoadingInfo(String paramString1, ImageAware paramImageAware,
			ImageSize paramImageSize, String paramString2,
			DisplayImageOptions paramDisplayImageOptions,
			ImageLoadingListener paramImageLoadingListener,
			ImageLoadingProgressListener paramImageLoadingProgressListener,
			ReentrantLock paramReentrantLock) {
		this.uri = paramString1;
		this.imageAware = paramImageAware;
		this.targetSize = paramImageSize;
		this.options = paramDisplayImageOptions;
		this.listener = paramImageLoadingListener;
		this.progressListener = paramImageLoadingProgressListener;
		this.loadFromUriLock = paramReentrantLock;
		this.memoryCacheKey = paramString2;
	}
}