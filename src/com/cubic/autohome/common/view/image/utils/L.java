package com.cubic.autohome.common.view.image.utils;

import android.util.Log;

public final class L {
	private static volatile boolean writeDebugLogs = false;
	private static volatile boolean writeLogs = true;

	public static void d(String msg, Object[] arg) {
		if (writeDebugLogs)
			log(3, null, msg, arg);
	}

	public static void e(String msg, Object[] arg) {
		log(6, null, msg, arg);
	}

	public static void e(Throwable throwable) {
		log(6, throwable, null, null);
	}

	public static void i(String msg, Object[] arg) {
		log(4, null, msg, arg);
	}
	
	public static void i(String msg, Throwable throwable) {
		log(4, throwable, msg, null);
	}

	private static void log(int level, Throwable throwable,
			String msg, Object[] args) {
		if (!writeLogs)
			return;
		String str = msg;
		if (args.length > 0)
			str = String.format(msg, args);
		if (throwable != null) {
			str = String.format("%1$s\n%2$s", new Object[] { msg,
					Log.getStackTraceString(throwable)});
		}
		Log.println(level, "ImageLoader", str);
	}

	public static void w(String paramString, Object[] paramArrayOfObject) {
		log(5, null, paramString, paramArrayOfObject);
	}

	public static void writeDebugLogs(boolean paramBoolean) {
		writeDebugLogs = paramBoolean;
	}

	public static void writeLogs(boolean paramBoolean) {
		writeLogs = paramBoolean;
	}
}