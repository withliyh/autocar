package com.cubic.autohome.common.bean;

import java.io.Serializable;

public class PageEntity implements Serializable {
	public static final int ARTICLE_TYPE = 1;
	public static final int PERSUADER_TYPE = 2;
	public static final int SALE_TYPE = 5;
	public static final int TOPIC_TYPE = 4;
	public static final int VIDEO_TYPE = 3;
	private int bbsId;
	private String bbsType;
	private int pageId;
	private int sale_dealerId;
	private int sale_newsId;
	private int sale_newsType;
	private int sale_seriesId;
	private int sale_specId;
	private String title;
	private int type;

	public int getBbsId() {
		return this.bbsId;
	}

	public String getBbsType() {
		return this.bbsType;
	}

	public int getPageId() {
		return this.pageId;
	}

	public int getSale_dealerId() {
		return this.sale_dealerId;
	}

	public int getSale_newsId() {
		return this.sale_newsId;
	}

	public int getSale_newsType() {
		return this.sale_newsType;
	}

	public int getSale_seriesId() {
		return this.sale_seriesId;
	}

	public int getSale_specId() {
		return this.sale_specId;
	}

	public String getTitle() {
		return this.title;
	}

	public int getType() {
		return this.type;
	}

	public void setBbsId(int paramInt) {
		this.bbsId = paramInt;
	}

	public void setBbsType(String paramString) {
		this.bbsType = paramString;
	}

	public void setPageId(int paramInt) {
		this.pageId = paramInt;
	}

	public void setSale_dealerId(int paramInt) {
		this.sale_dealerId = paramInt;
	}

	public void setSale_newsId(int paramInt) {
		this.sale_newsId = paramInt;
	}

	public void setSale_newsType(int paramInt) {
		this.sale_newsType = paramInt;
	}

	public void setSale_seriesId(int paramInt) {
		this.sale_seriesId = paramInt;
	}

	public void setSale_specId(int paramInt) {
		this.sale_specId = paramInt;
	}

	public void setTitle(String paramString) {
		this.title = paramString;
	}

	public void setType(int paramInt) {
		this.type = paramInt;
	}
}