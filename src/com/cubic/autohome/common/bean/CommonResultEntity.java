package com.cubic.autohome.common.bean;

import java.io.Serializable;

public class CommonResultEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String message = "";
	private int returnCode = -1;

	public String getMessage() {
		return this.message;
	}

	public int getReturnCode() {
		return this.returnCode;
	}

	public void setMessage(String paramString) {
		this.message = paramString;
	}

	public void setReturnCode(int paramInt) {
		this.returnCode = paramInt;
	}
}