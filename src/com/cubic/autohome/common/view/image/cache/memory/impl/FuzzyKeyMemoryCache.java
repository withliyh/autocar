package com.cubic.autohome.common.view.image.cache.memory.impl;

import android.graphics.Bitmap;
import com.cubic.autohome.common.view.image.cache.memory.MemoryCache;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

public class FuzzyKeyMemoryCache implements MemoryCache {
	private final MemoryCache cache;
	private final Comparator<String> keyComparator;

	public FuzzyKeyMemoryCache(MemoryCache paramMemoryCache,
			Comparator<String> paramComparator) {
		this.cache = paramMemoryCache;
		this.keyComparator = paramComparator;
	}

	public void clear() {
		this.cache.clear();
	}

	public Bitmap get(String paramString) {
		return this.cache.get(paramString);
	}

	public Collection<String> keys() {
		return this.cache.keys();
	}

	public boolean put(String key, Bitmap paramBitmap) {
		String str = null;

		Iterator<String> localIterator = this.cache.keys().iterator();

		while (localIterator.hasNext()) {
			str = localIterator.next();
			if (this.keyComparator.compare(key, str) == 0) {
				this.cache.remove(key);
				break;
			}
		}
		return this.cache.put(key, paramBitmap);

	}

	public Bitmap remove(String paramString) {
		return this.cache.remove(paramString);
	}
}