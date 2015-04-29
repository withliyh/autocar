package com.cubic.autohome.common.view.image.core.assist;

public class FailReason {
	public enum FailType {
		IO_ERROR, DECODING_ERROR, NETWORK_DENIED, OUT_OF_MEMORY, UNKNOWN;
	}
	
	private final Throwable cause;
	private final FailType type;
	
	public FailReason(FailType type, Throwable cause) {
		this.type = type;
		this.cause = cause;
	}
}
