package com.cubic.autohome.common.view.image.cache.memory;

import java.util.Collection;

import android.graphics.Bitmap;

public interface MemoryCache {
	public abstract void clear();

	public abstract Bitmap get(String key);

	public abstract Collection<String> keys();

	public abstract boolean put(String key, Bitmap paramBitmap);

	public abstract Bitmap remove(String key);
}
