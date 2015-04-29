package com.cubic.autohome.common.view.image.cache.disc.impl.ext;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

final class Util {
	static final Charset US_ASCII = Charset.forName("US-ASCII");
	static final Charset UTF_8 = Charset.forName("UTF-8");

	static void closeQuietly(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (RuntimeException e) {
				throw e;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return;
		}
	}

	static void deleteContents(File paramFile) throws IOException {
		File[] arrayOfFile = paramFile.listFiles();
		if (arrayOfFile == null)
			throw new IOException("not a readable directory: " + paramFile);
		int j = arrayOfFile.length;
		int i = 0;
		while (true) {
			if (i >= j)
				return;
			paramFile = arrayOfFile[i];
			if (paramFile.isDirectory())
				deleteContents(paramFile);
			if (!paramFile.delete())
				throw new IOException("failed to delete file: " + paramFile);
			i += 1;
		}
	}
}