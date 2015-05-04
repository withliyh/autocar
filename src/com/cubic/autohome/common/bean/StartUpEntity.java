package com.cubic.autohome.common.bean;

public class StartUpEntity extends CommonResultEntity {
	private static final long serialVersionUID = 1L;
	private boolean hasNewTuan = false;
	private boolean isHasNewFunction = false;
	private String tuandatetime = "";

	public String getTuandatetime() {
		return this.tuandatetime;
	}

	public boolean hasNewTuan() {
		return this.hasNewTuan;
	}

	public boolean isHasNewFunction() {
		return this.isHasNewFunction;
	}

	public void setHasNewFunction(boolean paramBoolean) {
		this.isHasNewFunction = paramBoolean;
	}

	public void setNewTuan(boolean paramBoolean) {
		this.hasNewTuan = paramBoolean;
	}

	public void setTuandatetime(String paramString) {
		this.tuandatetime = paramString;
	}
}