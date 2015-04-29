package com.cubic.autohome.common.ums;

import java.util.HashMap;

public class UmsParams {
	private HashMap<String, String> mHashMap = null;
	
	  public HashMap<String, String> getHashMap()
	  {
	    return this.mHashMap;
	  }

	  public void put(String paramString1, String paramString2, int paramInt)
	  {
	    paramString1 = paramString1 + "#" + paramInt;
	    this.mHashMap.put(paramString1, paramString2);
	  }
}
