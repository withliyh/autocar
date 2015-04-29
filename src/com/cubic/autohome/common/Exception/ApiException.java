package com.cubic.autohome.common.Exception;

public class ApiException extends BaseException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8534881109566053515L;

	public ApiException(int paramInt) {
		super(paramInt);
	}

	public ApiException(int paramInt, String paramString) {
		super(paramInt, paramString);
	}

	public ApiException(int paramInt, String paramString1, String paramString2,
			Throwable paramThrowable) {
		super(paramInt, paramString1, paramThrowable);
	}

	public ApiException(int paramInt, String paramString,
			Throwable paramThrowable) {
		super(paramInt, paramString, paramThrowable);
	}
}
