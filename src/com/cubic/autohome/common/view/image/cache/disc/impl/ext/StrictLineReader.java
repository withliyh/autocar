package com.cubic.autohome.common.view.image.cache.disc.impl.ext;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class StrictLineReader implements Closeable {

	private byte[] buf;
	private final Charset charset;
	private int end;
	private final InputStream in;
	private int pos;
	
	public StrictLineReader(InputStream inputStream, int capacity, Charset charset) {
		if (inputStream == null || charset == null) {
			throw new NullPointerException();
		}
		if (capacity < 0) {
			throw new IllegalArgumentException("capacity <= 0");
		}
		if (!charset.equals(Util.US_ASCII)) {
			throw new IllegalArgumentException("Unsupported encoding");
		}
		this.in = inputStream;
		this.charset = charset;
		this.buf = new byte[capacity];
	}
	
	public StrictLineReader(InputStream inputStream, Charset charset) {
		this(inputStream, 8192, charset);
	}
	
	
	@Override
	public void close() throws IOException {
		synchronized (in) {
			if (buf != null) {
				buf = null;
				in.close();
			}
			return;
		}
	}
	

	
	private void fillBuf() throws IOException{
		int i = in.read(buf, 0, buf.length);
		if (i == -1) {
			throw new EOFException();
		}
		this.pos = 0;
		this.end = i;
	}

}
