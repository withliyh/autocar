package com.cubic.autohome.common.view.image.core.download;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

public interface ImageDownloader {
	public Scheme scheme = Scheme.UNKNOWN;
	public InputStream getStream(String url, Object obj) throws IOException;
	
	public enum Scheme {
		HTTP("http"), HTTPS("https"), FILE("file"), CONTENT("content"), ASSETS("assets"), DRAWABLE("drawable"), UNKNOWN("unknown");
		
		private String scheme;
		private String uriPrefix;
		
		private Scheme(String scheme) {
			this.scheme = scheme;
			this.uriPrefix = scheme + "://";
		}
	
		private boolean belongsTo(String uri) {
			return uri.toLowerCase(Locale.US).startsWith(uriPrefix);
		}
		
		public static Scheme ofUri(String uri) {
			if (uri == null) {
				return Scheme.UNKNOWN;
			}
			int len = Scheme.values().length;
			boolean b;
			for (int i = 0; i < len; i++) {
				b = Scheme.values()[i].belongsTo(uri);
				if (b) {
					return Scheme.values()[i];
				}
			}
			return Scheme.UNKNOWN;
		}
		
		public String crop(String uri) {
			boolean b = this.belongsTo(uri);
			if (!b) {
				String format = String.format("URI [%1$s] doesn\'t have expected scheme [%2$s]", uri, scheme);
				throw new IllegalArgumentException(format);
			}
			return uri.substring(this.uriPrefix.length());
		}
		
		public String wrap(String path) {
			return this.uriPrefix + path;
		}
	}
}
