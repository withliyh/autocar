package com.cubic.autohome.common.skin;

import java.util.Iterator;

public class AtSkinObserable extends SkinObserable {
	private static AtSkinObserable mInstance = new AtSkinObserable();

	public static AtSkinObserable getInstance() {
		try {
			if (mInstance == null)
				mInstance = new AtSkinObserable();
			AtSkinObserable localAtSkinObserable = mInstance;
			return localAtSkinObserable;
		} finally {
		}
	}

	public void notifySkinChange() {
		try {
			Iterator localIterator = this.list.iterator();
			while (true) {
				if (!localIterator.hasNext())
					return;
				((ISkinUIObserver) localIterator.next()).onSkinChanged();
			}
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}
}
