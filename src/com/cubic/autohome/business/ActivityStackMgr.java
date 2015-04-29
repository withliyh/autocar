package com.cubic.autohome.business;

import java.util.Stack;

import android.app.Activity;

public class ActivityStackMgr {
	private static Stack<Activity> activityStack;
	private static ActivityStackMgr instance;

	public static ActivityStackMgr getActivityStackMgr() {
		if (instance == null)
			instance = new ActivityStackMgr();
		return instance;
	}

	public Activity currentActivity() {
		return activityStack.lastElement();
	}

	public void popActivity() {
		Activity localActivity = activityStack.lastElement();
		if (localActivity != null)
			localActivity.finish();
	}

	public void popActivity(Activity paramActivity) {
		if (paramActivity != null) {
			paramActivity.finish();
			activityStack.remove(paramActivity);
		}
	}

	public void popAllActivity() {
		while (activityStack.size() > 0) {
			Activity activ = currentActivity();
			popActivity(activ);
		}
	}

	public void popNofinishActivity(Activity paramActivity) {
		if (paramActivity != null)
			activityStack.remove(paramActivity);
	}

	public void pushActivity(Activity paramActivity) {
		if (activityStack == null)
			activityStack = new Stack<Activity>();
		activityStack.add(paramActivity);
	}
}
