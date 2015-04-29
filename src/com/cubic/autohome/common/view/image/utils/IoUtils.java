package com.cubic.autohome.common.view.image.utils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IoUtils {
	public static abstract interface CopyListener {
		public abstract boolean onBytesCopied(int paramInt1, int paramInt2);
	}

	public static void closeSilently(Closeable paramCloseable) {
		try {
			paramCloseable.close();
			return;
		} catch (Exception e) {
		}
	}

	public static boolean copyStream(InputStream paramInputStream,
			OutputStream paramOutputStream, CopyListener paramCopyListener,
			int paramInt) throws IOException {
		int j = 0;
		int k = paramInputStream.available();
		int i = k;
		if (k <= 0)
			i = 512000;
		byte[] arrayOfByte = new byte[paramInt];
		if (shouldStopLoading(paramCopyListener, 0, i))
			return false;
		do {
			paramOutputStream.write(arrayOfByte, 0, k);
			j += k;
			if (shouldStopLoading(paramCopyListener, j, i))
				break;
			k = paramInputStream.read(arrayOfByte, 0, paramInt);
		} while (k != -1);
		paramOutputStream.flush();
		return true;
	}

	private static boolean shouldStopLoading(CopyListener paramCopyListener,
			int paramInt1, int paramInt2) {
		return (paramCopyListener != null)
				&& (!paramCopyListener.onBytesCopied(paramInt1, paramInt2))
				&& (paramInt1 * 100 / paramInt2 < 75);
	}
}
