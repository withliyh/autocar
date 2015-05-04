package com.cubic.autohome.common.bean;

import android.text.TextUtils;

public class ProxyItem {
	private int dataoverdue = 1;
	private int errorstatus = 1;
	private int hashdismatch = 1;
	private int httpErrHandle = 1;
	private int networkErroHandle = 1;
	private int parsingerror = 1;
	private String urlmapping;

	public int getDataoverdue() {
		return this.dataoverdue;
	}

	public int getErrorstatus() {
		return this.errorstatus;
	}

	public int getHashdismatch() {
		return this.hashdismatch;
	}

	public int getHttpErrHandle() {
		return this.httpErrHandle;
	}

	public int getNetworkErroHandle() {
		return this.networkErroHandle;
	}

	public int getParsingerror() {
		return this.parsingerror;
	}

	public String getUrlmapping() {
		return this.urlmapping;
	}

	public void setDataoverdue(int paramInt) {
		this.dataoverdue = paramInt;
	}

	public void setErrorstatus(int paramInt) {
		this.errorstatus = paramInt;
	}

	public void setHashdismatch(int paramInt) {
		this.hashdismatch = paramInt;
	}

	public void setHttpErrHandle(int paramInt) {
		this.httpErrHandle = paramInt;
	}

	public void setNetworkErroHandle(int paramInt) {
		this.networkErroHandle = paramInt;
	}

	public void setParsingerror(int paramInt) {
		this.parsingerror = paramInt;
	}

	public void setUrlmapping(String paramString) {
		urlmapping = paramString; // 反编译错误
//		String str = paramString;
//		int j;
//		int i;
//		if (!TextUtils.isEmpty(paramString)) {
//			j = paramString.length() % 4;
//			str = paramString;
//			if (j != 0)
//				i = 0;
//		}
//		while (true) {
//			if (i >= j)
//				str = paramString;
//			do {
//				this.urlmapping = str;
//				return;
//				paramString = paramString + "=";
//				str = paramString;
//			} while (paramString.length() % 4 == 0);
//			i += 1;
//		}
	}

	public static class ProxyBean {
		private String host;
		private boolean isSuspend;

		public String getHost() {
			return this.host;
		}

		public boolean isSuspend() {
			return this.isSuspend;
		}

		public void setHost(String paramString) {
			this.host = paramString;
		}

		public void setSuspend(boolean paramBoolean) {
			this.isSuspend = paramBoolean;
		}
	}
}