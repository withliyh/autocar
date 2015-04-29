package com.cubic.autohome.common.Exception;

public class NetException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8961539707565128081L;

	public NetException(int paramInt, String paramString) {
		super(paramInt, paramString);
	}

	public NetException(int paramInt, String paramString,
			Throwable paramThrowable) {
		super(paramInt, paramString, paramThrowable);
	}

}
