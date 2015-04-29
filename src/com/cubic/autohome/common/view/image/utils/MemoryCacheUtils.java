package com.cubic.autohome.common.view.image.utils;

import com.cubic.autohome.common.view.image.core.assist.ImageSize;

import java.util.Comparator;

public final class MemoryCacheUtils {
	public static Comparator<String> createFuzzyKeyComparator() {
		return new Comparator() {

			@Override
			public int compare(Object paramAnonymousString1,
					Object paramAnonymousString2) {
				return 0;
			}
		};
	}

	public static String generateKey(String paramString,
			ImageSize paramImageSize) {
		return paramString + "_" + paramImageSize.getWidth() + "x"
				+ paramImageSize.getHeight();
	}
}