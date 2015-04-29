package com.cubic.autohome.common.view.image.core.display;

import com.cubic.autohome.common.view.image.core.assist.LoadedFrom;
import com.cubic.autohome.common.view.image.core.imageaware.ImageAware;

import android.graphics.Bitmap;

public interface BitmapDisplayer {
	public void display(Bitmap bitmap, ImageAware imageAware, LoadedFrom loadedFrom);
}
