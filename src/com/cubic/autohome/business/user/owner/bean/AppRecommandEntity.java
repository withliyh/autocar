package com.cubic.autohome.business.user.owner.bean;

import java.io.Serializable;

public class AppRecommandEntity implements Serializable {
	private String action;
	private String appDesc;
	private String appName;
	private String appUrl;
	private String appUrlScheme;
	private int clientType;
	private String developer;
	private String downUrl;
	private String iconUrl;
	private int id;
	private boolean launchEnabled;
	private String packagename;
	private String tuiimg;
	private String updateDate;
	private String versionName;
	private String versioncode;

	public AppRecommandEntity() {
	}

	public AppRecommandEntity(String paramString1, String paramString2,
			String paramString3) {
		this.appName = paramString1;
		this.iconUrl = null;
		this.appUrlScheme = "com.cublic.autohome";
		this.downUrl = paramString2;
		this.versionName = paramString3;
		this.launchEnabled = false;
	}

	public AppRecommandEntity(String paramString1, String paramString2,
			String paramString3, String paramString4, String paramString5) {
		this.appName = paramString1;
		this.iconUrl = paramString2;
		this.appUrlScheme = paramString3;
		this.downUrl = paramString4;
		this.versionName = paramString5;
		this.launchEnabled = true;
	}

	public String getAction() {
		return this.action;
	}

	public String getAppDesc() {
		return this.appDesc;
	}

	public String getAppName() {
		return this.appName;
	}

	public String getAppUrl() {
		return this.appUrl;
	}

	public String getAppUrlScheme() {
		return this.appUrlScheme;
	}

	public int getClientType() {
		return this.clientType;
	}

	public String getDeveloper() {
		return this.developer;
	}

	public String getDownUrl() {
		return this.downUrl;
	}

	public String getIconUrl() {
		return this.iconUrl;
	}

	public int getId() {
		return this.id;
	}

	public String getPackagename() {
		return this.packagename;
	}

	public String getTuiimg() {
		return this.tuiimg;
	}

	public String getUpdateDate() {
		return this.updateDate;
	}

	public String getVersionName() {
		return this.versionName;
	}

	public String getVersioncode() {
		return this.versioncode;
	}

	public boolean isLaunchEnabled() {
		return this.launchEnabled;
	}

	public void setAction(String paramString) {
		this.action = paramString;
	}

	public void setAppDesc(String paramString) {
		this.appDesc = paramString;
	}

	public void setAppName(String paramString) {
		this.appName = paramString;
	}

	public void setAppUrl(String paramString) {
		this.appUrl = paramString;
	}

	public void setAppUrlScheme(String paramString) {
		this.appUrlScheme = paramString;
	}

	public void setClientType(int paramInt) {
		this.clientType = paramInt;
	}

	public void setDeveloper(String paramString) {
		this.developer = paramString;
	}

	public void setDownUrl(String paramString) {
		this.downUrl = paramString;
	}

	public void setIconUrl(String paramString) {
		this.iconUrl = paramString;
	}

	public void setId(int paramInt) {
		this.id = paramInt;
	}

	public void setLaunchEnabled(boolean paramBoolean) {
		this.launchEnabled = paramBoolean;
	}

	public void setPackagename(String paramString) {
		this.packagename = paramString;
	}

	public void setTuiimg(String paramString) {
		this.tuiimg = paramString;
	}

	public void setUpdateDate(String paramString) {
		this.updateDate = paramString;
	}

	public void setVersionName(String paramString) {
		this.versionName = paramString;
	}

	public void setVersioncode(String paramString) {
		this.versioncode = paramString;
	}
}