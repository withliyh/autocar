package com.cubic.autohome.common.view.image.core.display;

import android.graphics.Bitmap;

import com.cubic.autohome.common.view.image.core.assist.LoadedFrom;
import com.cubic.autohome.common.view.image.core.imageaware.ImageAware;

public class SimpleBitmapDisplayer implements BitmapDisplayer {

	@Override
	public void display(Bitmap bitmap, ImageAware imageAware,
			LoadedFrom loadedFrom) {
		imageAware.setImageBitmap(bitmap);
	}

}
