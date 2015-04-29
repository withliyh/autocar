package com.cubic.autohome.common.view.image.cache.memory.impl;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import android.graphics.Bitmap;

import com.cubic.autohome.common.view.image.cache.memory.BaseMemoryCache;

public class WeakMemoryCache extends BaseMemoryCache {

	@Override
	protected Reference<Bitmap> createReference(Bitmap bitmap) {
		return new WeakReference<Bitmap>(bitmap);
	}

}
