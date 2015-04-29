package com.cubic.autohome.common.view.image.core.imageaware;

import com.cubic.autohome.common.view.image.core.assist.ViewScaleType;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;

public interface ImageAware {
	public abstract int getHeight();

	public abstract int getId();

	public abstract ViewScaleType getScaleType();

	public abstract int getWidth();

	public abstract View getWrappedView();

	public abstract boolean isCollected();

	public abstract boolean setImageBitmap(Bitmap paramBitmap);

	public abstract boolean setImageDrawable(Drawable paramDrawable);
}
