package com.cubic.autohome.common.view.image.core;

import com.cubic.autohome.common.view.image.core.assist.ImageScaleType;
import com.cubic.autohome.common.view.image.core.display.BitmapDisplayer;
import com.cubic.autohome.common.view.image.core.process.BitmapProcessor;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Handler;

public final class DisplayImageOptions {

	private final boolean cacheInMemory;
	private final boolean cacheOnDisk;
	private final boolean considerExifParams;
	private final BitmapFactory.Options decodingOptions;
	private final int delayBeforeLoading;
	private final BitmapDisplayer displayer;
	private final Object extraForDownloader;
	private final Handler handler;
	private final Drawable imageForEmptyUri;
	private final Drawable imageOnFail;
	private final Drawable imageOnLoading;
	private final int imageResForEmptyUri;
	private final int imageResOnFail;
	private final int imageResOnLoading;
	private final ImageScaleType imageScaleType;
	private final boolean isSyncLoading;
	private final BitmapProcessor postProcessor;
	private final BitmapProcessor preProcessor;
	private final boolean resetViewBeforeLoading;

	private DisplayImageOptions(Builder paramBuilder) {
		this.imageResOnLoading = paramBuilder.imageResOnLoading;
		this.imageResForEmptyUri = paramBuilder.imageResForEmptyUri;
		this.imageResOnFail = paramBuilder.imageResOnFail;
		this.imageOnLoading = paramBuilder.imageOnLoading;
		this.imageForEmptyUri = paramBuilder.imageForEmptyUri;
		this.imageOnFail = paramBuilder.imageOnFail;
		this.resetViewBeforeLoading = paramBuilder.resetViewBeforeLoading;
		this.cacheInMemory = paramBuilder.cacheInMemory;
		this.cacheOnDisk = paramBuilder.cacheOnDisk;
		this.imageScaleType = paramBuilder.imageScaleType;
		this.decodingOptions = paramBuilder.decodingOptions;
		this.delayBeforeLoading = paramBuilder.delayBeforeLoading;
		this.considerExifParams = paramBuilder.considerExifParams;
		this.extraForDownloader = paramBuilder.extraForDownloader;
		this.preProcessor = paramBuilder.preProcessor;
		this.postProcessor = paramBuilder.postProcessor;
		this.displayer = paramBuilder.displayer;
		this.handler = paramBuilder.handler;
		this.isSyncLoading = paramBuilder.isSyncLoading;
	}

	public static DisplayImageOptions createSimple() {
		return new Builder().build();
	}

	public BitmapFactory.Options getDecodingOptions() {
		return this.decodingOptions;
	}

	public int getDelayBeforeLoading() {
		return this.delayBeforeLoading;
	}

	public BitmapDisplayer getDisplayer() {
		return this.displayer;
	}

	public Object getExtraForDownloader() {
		return this.extraForDownloader;
	}

	public Handler getHandler() {
		return this.handler;
	}

	public Drawable getImageForEmptyUri(Resources paramResources) {
		if (this.imageResForEmptyUri != 0)
			return paramResources.getDrawable(this.imageResForEmptyUri);
		return this.imageForEmptyUri;
	}

	public Drawable getImageOnFail(Resources paramResources) {
		if (this.imageResOnFail != 0)
			return paramResources.getDrawable(this.imageResOnFail);
		return this.imageOnFail;
	}

	public Drawable getImageOnLoading(Resources paramResources) {
		if (this.imageResOnLoading != 0)
			return paramResources.getDrawable(this.imageResOnLoading);
		return this.imageOnLoading;
	}

	public ImageScaleType getImageScaleType() {
		return this.imageScaleType;
	}

	public BitmapProcessor getPostProcessor() {
		return this.postProcessor;
	}

	public BitmapProcessor getPreProcessor() {
		return this.preProcessor;
	}

	public boolean isCacheInMemory() {
		return this.cacheInMemory;
	}

	public boolean isCacheOnDisk() {
		return this.cacheOnDisk;
	}

	public boolean isConsiderExifParams() {
		return this.considerExifParams;
	}

	public boolean isResetViewBeforeLoading() {
		return this.resetViewBeforeLoading;
	}

	boolean isSyncLoading() {
		return this.isSyncLoading;
	}

	public boolean shouldDelayBeforeLoading() {
		return this.delayBeforeLoading > 0;
	}

	public boolean shouldPostProcess() {
		return this.postProcessor != null;
	}

	public boolean shouldPreProcess() {
		return this.preProcessor != null;
	}

	public boolean shouldShowImageForEmptyUri() {
		return (this.imageForEmptyUri != null)
				|| (this.imageResForEmptyUri != 0);
	}

	public boolean shouldShowImageOnFail() {
		return (this.imageOnFail != null) || (this.imageResOnFail != 0);
	}

	public boolean shouldShowImageOnLoading() {
		return (this.imageOnLoading != null) || (this.imageResOnLoading != 0);
	}

	public static class Builder {
		private boolean cacheInMemory = false;
		private boolean cacheOnDisk = false;
		private boolean considerExifParams = false;
		private BitmapFactory.Options decodingOptions = new BitmapFactory.Options();
		private int delayBeforeLoading = 0;
		private BitmapDisplayer displayer = DefaultConfigurationFactory
				.createBitmapDisplayer();
		private Object extraForDownloader = null;
		private Handler handler = null;
		private Drawable imageForEmptyUri = null;
		private Drawable imageOnFail = null;
		private Drawable imageOnLoading = null;
		private int imageResForEmptyUri = 0;
		private int imageResOnFail = 0;
		private int imageResOnLoading = 0;
		private ImageScaleType imageScaleType = ImageScaleType.IN_SAMPLE_POWER_OF_2;
		private boolean isSyncLoading = false;
		private BitmapProcessor postProcessor = null;
		private BitmapProcessor preProcessor = null;
		private boolean resetViewBeforeLoading = false;

		public Builder() {
			this.decodingOptions.inPurgeable = true;
			this.decodingOptions.inInputShareable = true;
		}

		public DisplayImageOptions build() {
			return new DisplayImageOptions(this);
		}

		public Builder cacheInMemory(boolean paramBoolean) {
			this.cacheInMemory = paramBoolean;
			return this;
		}

		public Builder cacheOnDisk(boolean paramBoolean) {
			this.cacheOnDisk = paramBoolean;
			return this;
		}

		public Builder cloneFrom(DisplayImageOptions paramDisplayImageOptions) {
			this.imageResOnLoading = paramDisplayImageOptions.imageResOnLoading;
			this.imageResForEmptyUri = paramDisplayImageOptions.imageResForEmptyUri;
			this.imageResOnFail = paramDisplayImageOptions.imageResOnFail;
			this.imageOnLoading = paramDisplayImageOptions.imageOnLoading;
			this.imageForEmptyUri = paramDisplayImageOptions.imageForEmptyUri;
			this.imageOnFail = paramDisplayImageOptions.imageOnFail;
			this.resetViewBeforeLoading = paramDisplayImageOptions.resetViewBeforeLoading;
			this.cacheInMemory = paramDisplayImageOptions.cacheInMemory;
			this.cacheOnDisk = paramDisplayImageOptions.cacheOnDisk;
			this.imageScaleType = paramDisplayImageOptions.imageScaleType;
			this.decodingOptions = paramDisplayImageOptions.decodingOptions;
			this.delayBeforeLoading = paramDisplayImageOptions.delayBeforeLoading;
			this.considerExifParams = paramDisplayImageOptions.considerExifParams;
			this.extraForDownloader = paramDisplayImageOptions.extraForDownloader;
			this.preProcessor = paramDisplayImageOptions.preProcessor;
			this.postProcessor = paramDisplayImageOptions.postProcessor;
			this.displayer = paramDisplayImageOptions.displayer;
			this.handler = paramDisplayImageOptions.handler;
			this.isSyncLoading = paramDisplayImageOptions.isSyncLoading;
			return this;
		}

		public Builder imageScaleType(ImageScaleType paramImageScaleType) {
			this.imageScaleType = paramImageScaleType;
			return this;
		}
	}
}
