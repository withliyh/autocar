package com.cubic.autohome.common.view.image.cache.memory;

import java.lang.ref.Reference;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import android.graphics.Bitmap;

public abstract class BaseMemoryCache implements MemoryCache {

	private final Map<String, Reference<Bitmap>> softMap = Collections.synchronizedMap(new HashMap());
	
	@Override
	public void clear() {
		softMap.clear();
	}

	@Override
	public Bitmap get(String key) {
		Bitmap bitmap = null;
		Reference<Bitmap> reference = softMap.get(key);
		if (reference != null) {
			bitmap = reference.get();
		}
		return bitmap;
	}

	@Override
	public Collection<String> keys() {
		synchronized (softMap) {
			HashSet<String> hashSet = new HashSet(softMap.keySet());
			return hashSet;
		}
	}

	@Override
	public boolean put(String key, Bitmap paramBitmap) {
		softMap.put(key, createReference(paramBitmap));
		return true;
	}

	@Override
	public Bitmap remove(String key) {
		Reference<Bitmap> reference = softMap.remove(key);
		if (reference == null) {
			return null;
		}
		return reference.get();
	}

	protected abstract Reference<Bitmap> createReference(Bitmap bitmap);
}
