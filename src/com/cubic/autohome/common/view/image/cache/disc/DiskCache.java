package com.cubic.autohome.common.view.image.cache.disc;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.cubic.autohome.common.view.image.utils.IoUtils;

import android.graphics.Bitmap;

public interface DiskCache {
	public abstract File get(String key);

	public abstract boolean save(String key, Bitmap paramBitmap)
			throws IOException;

	public abstract boolean save(String key,
			InputStream paramInputStream, IoUtils.CopyListener paramCopyListener)
			throws IOException;
}
