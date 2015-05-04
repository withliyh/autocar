package com.cubic.autohome.common.bean;

public class QuickIndexBaseEntity {
	private int Id;
	private String baseIcon;
	private String baseId;
	private String baseName;
	private boolean isChecked = false;

	public String getBaseIcon() {
		return this.baseIcon;
	}

	public String getBaseId() {
		return this.baseId;
	}

	public String getBaseName() {
		return this.baseName;
	}

	public int getId() {
		return this.Id;
	}

	public boolean isChecked() {
		return this.isChecked;
	}

	public void setBaseIcon(String paramString) {
		this.baseIcon = paramString;
	}

	public void setBaseId(String paramString) {
		this.baseId = paramString;
	}

	public void setBaseName(String paramString) {
		this.baseName = paramString;
	}

	public void setChecked(boolean paramBoolean) {
		this.isChecked = paramBoolean;
	}

	public void setId(int paramInt) {
		this.Id = paramInt;
	}
}