package com.cubic.autohome.common.view.image.utils;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.io.IOException;

public final class StorageUtils {
	public static File getCacheDirectory(Context paramContext) {
		return getCacheDirectory(paramContext, true);
	}

	public static File getCacheDirectory(Context paramContext,
			boolean paramBoolean) {
		String externalState = null;
		File cacheDirectory = null;
		try {
			externalState = Environment.getExternalStorageState();
			if ("mounted".equals(externalState)) {
				if (hasExternalStoragePermission(paramContext))
					cacheDirectory = getExternalCacheDir(paramContext);
			}
			if (cacheDirectory == null) {
				cacheDirectory = paramContext.getCacheDir();
			}
		} catch (NullPointerException localNullPointerException) {
			localNullPointerException.printStackTrace();
		}
		return cacheDirectory;
	}

	private static File getExternalCacheDir(Context paramContext) {
		File localFile = new File(new File(new File(new File(
				Environment.getExternalStorageDirectory(), "Android"), "data"),
				paramContext.getPackageName()), "cache");

		if (!localFile.exists()) {
			if (!localFile.mkdirs()) {
				L.w("Unable to create external cache directory", new Object[0]);
				paramContext = null;
			}
		}
		try {
			new File(localFile, ".nomedia").createNewFile();
			return localFile;
		} catch (IOException e) {
			L.i("Can't create \".nomedia\" file in application external cache directory",
					e);
		}
		return localFile;
	}

	public static File getIndividualCacheDirectory(Context paramContext) {
		File localFile1 = getCacheDirectory(paramContext);
		File localFile2 = new File(localFile1, "uil-images");
		if (!localFile2.exists()) {
			if (!localFile2.mkdir())
				localFile2 = localFile1;
		}
		return localFile2;
	}

	public static File getOwnCacheDirectory(Context paramContext,
			String path) {
		File ownCache = null;
		if ("mounted".equals(Environment.getExternalStorageState())) {
			if (hasExternalStoragePermission(paramContext))
				ownCache = new File(
						Environment.getExternalStorageDirectory(), path);
		}
		if (ownCache != null) {
			if (!ownCache.exists()) {
				ownCache.mkdirs();
			}
		} else {
			ownCache = paramContext.getCacheDir();
		}
		return ownCache;
	}

	private static boolean hasExternalStoragePermission(Context paramContext) {
		return paramContext
				.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0;
	}
}