package com.cubic.autohome.common.bean;

import java.util.List;

public class BlackWhitleListEntity extends CommonResultEntity {
	private List<String> blackList;
	private List<String> whitleList;

	public List<String> getBlackList() {
		return this.blackList;
	}

	public List<String> getWhitleList() {
		return this.whitleList;
	}

	public void setBlackList(List<String> paramList) {
		this.blackList = paramList;
	}

	public void setWhitleList(List<String> paramList) {
		this.whitleList = paramList;
	}
}