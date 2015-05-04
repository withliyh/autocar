package com.cubic.autohome.common.bean;

import java.io.Serializable;

public class ChooseEntity extends BaseEntity implements Serializable {
	private String colorName;
	private boolean isChecked = false;
	private boolean isClickable = true;
	private String name;
	private String param1;
	private String param2;
	private String param3;
	private int phonelength;
	private String sid;
	private String type;

	public ChooseEntity() {
	}

	public ChooseEntity(String paramString1, String paramString2) {
		this.sid = paramString1;
		this.name = paramString2;
	}

	public ChooseEntity(String paramString1, String paramString2, int paramInt) {
		this.sid = paramString1;
		this.name = paramString2;
		this.phonelength = paramInt;
	}

	public ChooseEntity(String paramString1, String paramString2,
			String paramString3) {
		this.sid = paramString1;
		this.name = paramString2;
		this.colorName = paramString3;
	}

	public ChooseEntity(String paramString1, String paramString2,
			boolean paramBoolean) {
		this.sid = paramString1;
		this.name = paramString2;
		this.isClickable = paramBoolean;
	}

	public String getColorName() {
		return this.colorName;
	}

	public boolean getIsClickable() {
		return this.isClickable;
	}

	public String getName() {
		return this.name;
	}

	public String getParam1() {
		return this.param1;
	}

	public String getParam2() {
		return this.param2;
	}

	public String getParam3() {
		return this.param3;
	}

	public int getPhonelength() {
		return this.phonelength;
	}

	public String getSid() {
		return this.sid;
	}

	public String getType() {
		return this.type;
	}

	public boolean isChecked() {
		return this.isChecked;
	}

	public void setChecked(boolean paramBoolean) {
		this.isChecked = paramBoolean;
	}

	public void setClickable(boolean paramBoolean) {
		this.isClickable = paramBoolean;
	}

	public void setColorName(String paramString) {
		this.colorName = paramString;
	}

	public void setName(String paramString) {
		this.name = paramString;
	}

	public void setParam1(String paramString) {
		this.param1 = paramString;
	}

	public void setParam2(String paramString) {
		this.param2 = paramString;
	}

	public void setParam3(String paramString) {
		this.param3 = paramString;
	}

	public void setPhonelength(int paramInt) {
		this.phonelength = paramInt;
	}

	public void setSid(String paramString) {
		this.sid = paramString;
	}

	public void setType(String paramString) {
		this.type = paramString;
	}
}