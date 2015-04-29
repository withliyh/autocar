package com.cubic.autohome.common.view.image.core.decode;

import com.cubic.autohome.common.view.image.core.DisplayImageOptions;
import com.cubic.autohome.common.view.image.core.assist.ImageScaleType;
import com.cubic.autohome.common.view.image.core.assist.ImageSize;
import com.cubic.autohome.common.view.image.core.assist.ViewScaleType;
import com.cubic.autohome.common.view.image.core.download.ImageDownloader;

import android.graphics.BitmapFactory;
import android.os.Build;

public class ImageDecodingInfo {
	private final boolean considerExifParams;
	private final BitmapFactory.Options decodingOptions;
	private final ImageDownloader downloader;
	private final Object extraForDownloader;
	private String imageKey;
	private ImageScaleType imageScaleType;
	private final String imageUri;
	private final String originalImageUri;
	private final ImageSize targetSize;
	private final ViewScaleType viewScaleType;

	public ImageDecodingInfo(String imagekey, String imageUri,
			String originalImageUri, ImageSize targetSize,
			ViewScaleType viewScaleType, ImageDownloader imageDownloader,
			DisplayImageOptions displayImageOptions) {
		this.imageKey = imagekey;
		this.imageUri = imageUri;
		this.originalImageUri = originalImageUri;
		this.targetSize = targetSize;
		this.imageScaleType = displayImageOptions.getImageScaleType();
		this.viewScaleType = viewScaleType;
		this.downloader = imageDownloader;
		this.extraForDownloader = displayImageOptions.getExtraForDownloader();
		this.considerExifParams = displayImageOptions.isConsiderExifParams();
		this.decodingOptions = new BitmapFactory.Options();
		
		copyOptions(displayImageOptions.getDecodingOptions(), this.decodingOptions);
	}

	private void copyOptions(BitmapFactory.Options src,
			BitmapFactory.Options dst) {
		dst.inDensity = src.inDensity;
		dst.inDither = src.inDither;
		dst.inInputShareable = src.inInputShareable;
		dst.inJustDecodeBounds = src.inJustDecodeBounds;
		dst.inPreferredConfig = src.inPreferredConfig;
		dst.inPurgeable = src.inPurgeable;
		dst.inSampleSize = src.inSampleSize;
		dst.inScaled = src.inScaled;
		dst.inScreenDensity = src.inScreenDensity;
		dst.inTargetDensity = src.inTargetDensity;
		dst.inTempStorage = src.inTempStorage;
		if (Build.VERSION.SDK_INT >= 10)
			copyOptions10(src, dst);
		if (Build.VERSION.SDK_INT >= 11)
			copyOptions11(src, dst);
	}

	private void copyOptions10(BitmapFactory.Options src,
			BitmapFactory.Options dst) {
		dst.inPreferQualityOverSpeed = src.inPreferQualityOverSpeed;
	}

	private void copyOptions11(BitmapFactory.Options src,
			BitmapFactory.Options dst) {
		dst.inBitmap = src.inBitmap;
		dst.inMutable = src.inMutable;
	}

	public BitmapFactory.Options getDecodingOptions() {
		return this.decodingOptions;
	}

	public ImageDownloader getDownloader() {
		return this.downloader;
	}

	public Object getExtraForDownloader() {
		return this.extraForDownloader;
	}

	public String getImageKey() {
		return this.imageKey;
	}

	public ImageScaleType getImageScaleType() {
		return this.imageScaleType;
	}

	public String getImageUri() {
		return this.imageUri;
	}

	public ImageSize getTargetSize() {
		return this.targetSize;
	}

	public ViewScaleType getViewScaleType() {
		return this.viewScaleType;
	}

	public boolean shouldConsiderExifParams() {
		return this.considerExifParams;
	}
}
