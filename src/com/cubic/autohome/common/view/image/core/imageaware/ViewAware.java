package com.cubic.autohome.common.view.image.core.imageaware;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.cubic.autohome.common.view.image.core.assist.ViewScaleType;

public class ViewAware implements ImageAware{

	public ViewAware(View view) {
		
	}
	
	public ViewAware(View view, boolean bool) {
		if (view == null) {
			throw new IllegalArgumentException("view must not be null");
		}

	}
	
	
	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ViewScaleType getScaleType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getWrappedView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCollected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setImageBitmap(Bitmap paramBitmap) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setImageDrawable(Drawable paramDrawable) {
		// TODO Auto-generated method stub
		return false;
	}

}
