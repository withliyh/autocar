package com.cubic.autohome.common.bean;

import java.io.Serializable;

public class BaseEntity extends QuickIndexBaseEntity implements Serializable {
	static final long serialVersionUID = -6795987482202293249L;
	private int blockposition;
	private int id;
	private int type;
	private int viewType;

	public int getBlockposition() {
		return this.blockposition;
	}

	public int getId() {
		return this.id;
	}

	public int getViewType() {
		return this.viewType;
	}

	public void setBlockposition(int paramInt) {
		this.blockposition = paramInt;
	}

	public void setId(int paramInt) {
		this.id = paramInt;
	}

	public void setType(int paramInt) {
		this.type = paramInt;
	}

	public void setViewType(int paramInt) {
		this.viewType = paramInt;
	}
}