package com.cubic.autohome.common.view.image.cache.disc.impl.ext;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import android.graphics.Bitmap;

import com.cubic.autohome.common.view.image.cache.disc.DiskCache;
import com.cubic.autohome.common.view.image.cache.disc.impl.ext.DiskLruCache.Editor;
import com.cubic.autohome.common.view.image.cache.disc.impl.ext.DiskLruCache.Snapshot;
import com.cubic.autohome.common.view.image.cache.disc.naming.FileNameGenerator;
import com.cubic.autohome.common.view.image.utils.IoUtils;
import com.cubic.autohome.common.view.image.utils.IoUtils.CopyListener;
import com.cubic.autohome.common.view.image.utils.L;

public class LruDiskCache implements DiskCache {

	public static final Bitmap.CompressFormat DEFAULT_COMPRESS_FORMAT = Bitmap.CompressFormat.PNG;
	protected int bufferSize = 32768;
	protected DiskLruCache cache;
	protected Bitmap.CompressFormat compressFormat = DEFAULT_COMPRESS_FORMAT;
	protected int compressQuality = 100;
	protected final FileNameGenerator fileNameGenerator;
	private File reserveCacheDir;

	public LruDiskCache(File paramFile,
			FileNameGenerator paramFileNameGenerator, long paramLong)
			throws IOException {
		this(paramFile, null, paramFileNameGenerator, paramLong, 0);
	}

	public LruDiskCache(File paramFile1, File paramFile2,
			FileNameGenerator paramFileNameGenerator, long paramLong,
			int paramInt) throws IOException {
		if (paramFile1 == null)
			throw new IllegalArgumentException(
					"cacheDir argument must be not null");
		if (paramLong < 0L)
			throw new IllegalArgumentException(
					"cacheMaxSize argument must be positive number");
		if (paramInt < 0)
			throw new IllegalArgumentException(
					"cacheMaxFileCount argument must be positive number");
		if (paramFileNameGenerator == null)
			throw new IllegalArgumentException(
					"fileNameGenerator argument must be not null");
		long l = paramLong;
		if (paramLong == 0L)
			l = 9223372036854775807L;
		int i = paramInt;
		if (paramInt == 0)
			i = 2147483647;
		this.reserveCacheDir = paramFile2;
		this.fileNameGenerator = paramFileNameGenerator;
		initCache(paramFile1, paramFile2, l, i);
	}

	private String getKey(String paramString) {
		return this.fileNameGenerator.generate(paramString);
	}

	private void initCache(File paramFile1, File paramFile2, long paramLong,
			int paramInt) throws IOException {
		try {
			this.cache = DiskLruCache.open(paramFile1, 1, 1, paramLong,
					paramInt);
			return;
		} catch (IOException e) {
			do {
				L.e(e);
				if (e != null)
					initCache(paramFile2, null, paramLong, paramInt);
			} while (this.cache != null);
			throw e;
		}
		
	}

	@Override
	public File get(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(String key, Bitmap paramBitmap) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(String key, InputStream paramInputStream,
			CopyListener paramCopyListener) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}



}
