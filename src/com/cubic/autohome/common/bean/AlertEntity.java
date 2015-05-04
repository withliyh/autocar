package com.cubic.autohome.common.bean;

import java.util.ArrayList;
import java.util.List;

public class AlertEntity {
	private List<BtnEntity> btnList = new ArrayList();
	private int delayMinite;
	private String message;
	private String sign;
	private String title;

	public List<BtnEntity> getBtnList() {
		return this.btnList;
	}

	public int getDelayMinite() {
		return this.delayMinite;
	}

	public String getMessage() {
		return this.message;
	}

	public String getSign() {
		return this.sign;
	}

	public String getTitle() {
		return this.title;
	}

	public void setBtnList(List<BtnEntity> paramList) {
		this.btnList = paramList;
	}

	public void setDelayMinite(int paramInt) {
		this.delayMinite = paramInt;
	}

	public void setMessage(String paramString) {
		this.message = paramString;
	}

	public void setSign(String paramString) {
		this.sign = paramString;
	}

	public void setTitle(String paramString) {
		this.title = paramString;
	}

	public class BtnEntity {
		private String action;
		private String backurl;
		private String btntitle;
		private String openurl;
		private String version;

		public BtnEntity() {
		}

		public String getAction() {
			return this.action;
		}

		public String getBackurl() {
			return this.backurl;
		}

		public String getBtntitle() {
			return this.btntitle;
		}

		public String getOpenurl() {
			return this.openurl;
		}

		public String getVersion() {
			return this.version;
		}

		public void setAction(String paramString) {
			this.action = paramString;
		}

		public void setBackurl(String paramString) {
			this.backurl = paramString;
		}

		public void setBtntitle(String paramString) {
			this.btntitle = paramString;
		}

		public void setOpenurl(String paramString) {
			this.openurl = paramString;
		}

		public void setVersion(String paramString) {
			this.version = paramString;
		}
	}
}