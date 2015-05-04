package com.cubic.autohome.common.util;

import java.io.File;
import java.text.DecimalFormat;

import android.os.Environment;
import android.os.StatFs;

public class DiskUtil {
	public static final long MIN_LEFT_SPACE = 0x400L;//1024
	
	public DiskUtil() {
	}
	
	public static final String[] FileSize(long size) {
		String str = "";
		long rb = size;
		if (size >= MIN_LEFT_SPACE) {
			str = "KB";
			rb = size / MIN_LEFT_SPACE;
			if (rb >= MIN_LEFT_SPACE) {
				str = "MB";
				rb = rb / MIN_LEFT_SPACE;
			}
		}
		
		DecimalFormat formatter = new DecimalFormat();
		formatter.setGroupingSize(0x3);
		String[] result = new String[3];
		
		result[0] = formatter.format(size);
		result[1] = str;
		result[2] = String.valueOf(rb);
		
		return result;
		
	}
	
	public static String getSDCARDLogPath() {
		boolean mounted = Environment.getExternalStorageState().equals("mounted");
		String result = null;
		if (mounted) {
			result = Environment.getExternalStorageDirectory().getPath() + "/autohomemain/log/";
		}
		return result;
	}
	
	public static long getSDCardAvailableSpace() {
		boolean mounted = Environment.getExternalStorageState().equals("mounted");
		long result = 0;
		if (mounted) {
			File sdcardDir = Environment.getExternalStorageDirectory();
			StatFs sf = new StatFs(sdcardDir.getPath());
			int blockSize = sf.getBlockSize();
			int availCount = sf.getAvailableBlocks();
			result = blockSize * availCount;
			result /= 1024;
		}
		return result;
	}
	
	public static String[] getSDCardStorageFreeSize() {
		String sdcardDir = Environment.getExternalStorageDirectory().getPath();
		StatFs statFs = new StatFs(sdcardDir);
		int blockSize = statFs.getBlockSize();
		int availCount = statFs.getAvailableBlocks();
		availCount -= 4;
		
		long bytes = blockSize * availCount;
		return FileSize(bytes);
	}
	
	public static boolean isSDCARDMounted() {
		boolean mounted = Environment.getExternalStorageState().equals("mounted");
		return mounted;
	}
	
	public static boolean sdCardHaveSpace() {
		long space = getSDCardAvailableSpace();
		if (space / 1024 > 0) {
			return true;
		}
		return false;
	}
}
