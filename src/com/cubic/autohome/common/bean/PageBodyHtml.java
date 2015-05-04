package com.cubic.autohome.common.bean;

public class PageBodyHtml implements Comparable<PageBodyHtml> {
	private int pageindex;

	public PageBodyHtml(int paramInt) {
		this.pageindex = paramInt;
	}

	public int compareTo(PageBodyHtml paramPageBodyHtml) {
		if (this.pageindex > paramPageBodyHtml.getPageindex())
			return 1;
		if (this.pageindex == paramPageBodyHtml.getPageindex())
			return 0;
		return -1;
	}

	public int getPageindex() {
		return this.pageindex;
	}

	public void setPageindex(int paramInt) {
		this.pageindex = paramInt;
	}

	public String toString() {
		return String.valueOf(this.pageindex);
	}
}