package com.cubic.autohome.common.view.image.cache.disc.impl;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import com.cubic.autohome.common.view.image.cache.disc.DiskCache;
import com.cubic.autohome.common.view.image.cache.disc.naming.FileNameGenerator;
import com.cubic.autohome.common.view.image.utils.IoUtils;
import com.cubic.autohome.common.view.image.utils.IoUtils.CopyListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class BaseDiskCache implements DiskCache {
	public static final Bitmap.CompressFormat DEFAULT_COMPRESS_FORMAT = Bitmap.CompressFormat.PNG;
	protected int bufferSize = 32768;
	protected final File cacheDir;
	protected Bitmap.CompressFormat compressFormat = DEFAULT_COMPRESS_FORMAT;
	protected int compressQuality = 100;
	protected final FileNameGenerator fileNameGenerator;
	protected final File reserveCacheDir;

	public BaseDiskCache(File paramFile1, File paramFile2,
			FileNameGenerator paramFileNameGenerator) {
		if (paramFile1 == null)
			throw new IllegalArgumentException(
					"cacheDir argument must be not null");
		if (paramFileNameGenerator == null)
			throw new IllegalArgumentException(
					"fileNameGenerator argument must be not null");
		this.cacheDir = paramFile1;
		this.reserveCacheDir = paramFile2;
		this.fileNameGenerator = paramFileNameGenerator;
	}

	public File get(String paramString) {
		return getFile(paramString);
	}

	protected File getFile(String paramString) {
		String str = this.fileNameGenerator.generate(paramString);
		File localFile = this.cacheDir;
		if (!this.cacheDir.exists()) {
			if (!this.cacheDir.mkdirs()) {
				if (this.reserveCacheDir != null)
					if (!this.reserveCacheDir.exists()) {
						if (!this.reserveCacheDir.mkdirs());
					} else {
						localFile = this.reserveCacheDir;
					}
			}
		}
		return new File(localFile, str);
	}

	public boolean save(String paramString, Bitmap paramBitmap)
			throws IOException {
		File tmpfile = getFile(paramString);
		File localFile = new File(tmpfile.getAbsolutePath() + ".tmp");
		BufferedOutputStream localBufferedOutputStream = new BufferedOutputStream(
				new FileOutputStream(localFile), this.bufferSize);
		try {
			boolean bool1 = paramBitmap.compress(this.compressFormat,
					this.compressQuality, localBufferedOutputStream);
			IoUtils.closeSilently(localBufferedOutputStream);
			boolean bool2 = true;
			if (bool1) {
				if (!localFile.renameTo(tmpfile))
					bool2 = false;
			}
			if (!bool2)
				localFile.delete();
			paramBitmap.recycle();
			return bool2;
		} finally {
			IoUtils.closeSilently(localBufferedOutputStream);
			
		}
	}

	public boolean save(String paramString, InputStream paramInputStream,
			IoUtils.CopyListener paramCopyListener) throws IOException {
		File tmpfile = getFile(paramString);
		File localFile = new File(tmpfile.getAbsolutePath() + ".tmp");
		boolean bool2 = false;
		boolean bool1 = bool2;
		try {
			BufferedOutputStream localBufferedOutputStream = new BufferedOutputStream(
					new FileOutputStream(localFile), this.bufferSize);
			try {
				bool1 = IoUtils.copyStream(paramInputStream,
						localBufferedOutputStream, paramCopyListener,
						this.bufferSize);
				bool2 = bool1;
				bool1 = bool2;
				IoUtils.closeSilently(localBufferedOutputStream);
				return bool1;
			} finally {
				bool1 = bool2;
				IoUtils.closeSilently(localBufferedOutputStream);
				bool1 = bool2;
			}
		} finally {
			
		}
	}
}