package com.cubic.autohome.common.Exception;

public class BaseException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1029860873616287483L;
	private int code;

	public BaseException(int paramInt) {
		this.code = paramInt;
	}

	public BaseException(int paramInt, String paramString) {
		super(paramString);
		this.code = paramInt;
	}

	public BaseException(int paramInt, String paramString,
			Throwable paramThrowable) {
		super(paramString, paramThrowable);
		this.code = paramInt;
	}

	public int getCode() {
		return this.code;
	}
}
