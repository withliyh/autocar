package com.cubic.autohome.common.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class ListDataResult<T> implements Serializable {
	public int Total;
	private boolean isLoadCache = false;
	public boolean isLoadMore;
	public String lastid;
	public String message;
	public int pageCount;
	public ArrayList<T> resourceList = new ArrayList();
	public int success;

	public String getLastid() {
		return this.lastid;
	}

	public String getMessage() {
		return this.message;
	}

	public int getPageCount() {
		return this.pageCount;
	}

	public ArrayList<T> getResourceList() {
		return this.resourceList;
	}

	public int getSuccess() {
		return this.success;
	}

	public int getTotal() {
		return this.Total;
	}

	public boolean isLoadCache() {
		return this.isLoadCache;
	}

	public void setLastid(String paramString) {
		this.lastid = paramString;
	}

	public void setLoadCache(boolean paramBoolean) {
		this.isLoadCache = paramBoolean;
	}

	public void setMessage(String paramString) {
		this.message = paramString;
	}

	public void setPageCount(int paramInt) {
		this.pageCount = paramInt;
	}

	public void setSuccess(int paramInt) {
		this.success = paramInt;
	}

	public void setTotal(int paramInt) {
		this.Total = paramInt;
	}
}