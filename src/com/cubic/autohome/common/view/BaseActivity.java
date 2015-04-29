package com.cubic.autohome.common.view;

import com.cubic.autohome.business.ActivityStackMgr;
import com.cubic.autohome.business.MyApplication;
import com.cubic.autohome.common.Exception.ApiException;
import com.cubic.autohome.common.skin.ISkinUIObserver;
import com.cubic.autohome.common.ums.UmsParams;
import com.cubic.autohome.common.util.SkinsUtil;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Toast;

public class BaseActivity extends Activity implements ISkinUIObserver {

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				BaseActivity.this.LoadDataBegin();
				break;
			case 1:
				BaseActivity.this.LoadDataFinish();
				break;
			case 2:
				BaseActivity.this.LoadDataErro();
			}
			String errDesc = msg.getData().getString("err");
			Toast.makeText(getApplication(), errDesc, Toast.LENGTH_LONG).show();
		}
	};

	protected boolean isAutoRefreshPv = false;
	protected boolean isPvEnable = true;
	protected boolean isStartPv = false;
	protected boolean isViewCreated = true;
	protected boolean mIsFirst = true;
	private Thread mLoadThread;
	protected UmsParams mPvParams = null;
	protected boolean openThreadInActivity = false;
	protected String pvLabel = null;

	private void beginPv(UmsParams umsParams) {
		if (umsParams != null) {
			this.mPvParams = umsParams;
			this.isStartPv = true;
			return;
		}
	}

	public void endPv() {
		if (isStartPv && pvLabel != null) {
			isStartPv = false;
			return;
		}
	}

	private void startLoadThread() {
		if (handler == null) {
			return;
		}
		if (handler.hasMessages(0)) {
			handler.removeMessages(0);
		}
		if (handler.hasMessages(1)) {
			handler.removeMessages(1);
		}
		this.mLoadThread = new LoadThread(null);
		this.mLoadThread.start();
	}

	public void LoadDataBegin() {

	}

	public void LoadDataErro() {

	}

	public void LoadDataFinish() {
		fillUI();

	}

	public void fillUI() {

	}

	protected void onCreateAnim() {
		overridePendingTransition(0, 0);
	}

	protected void finishAnim() {
		overridePendingTransition(0, 0);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		onCreateAnim();
		SkinsUtil.initSkin();
		MyApplication.getInstance().getAtSkinObserable().registered(this);
		ActivityStackMgr.getActivityStackMgr().pushActivity(this);
	}

	@Override
	protected void onDestroy() {
		MyApplication.getInstance().getAtSkinObserable().unregistered(this);
		super.onDestroy();
	}

	@Override
	protected void onStart() {
		super.onStart();
		isViewCreated = true;
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (isPvEnable) {
			endPv();
		}
		isViewCreated = false;
	}
	
	@Override
	public void setContentView(int layoutResID) {
		try {
			super.setContentView(layoutResID);
			initContentView();
		} catch (Exception e) {
			
		}
	}
	
	@Override
	public void setContentView(View view) {
		super.setContentView(view);
		initContentView();
	}
	
	@Override
	public void setContentView(View view, LayoutParams params) {
		super.setContentView(view, params);
		initContentView();
	}
	
	public void initContentView() {
		getWindow().getDecorView().findViewById(0).setBackgroundColor(0);
		fillStaticUIData();
		if (openThreadInActivity) {
			startLoadThread();
		}
	}
	
	public void fillStaticUIData() {
		
	}

	@Override
	public void finish() {
		ActivityStackMgr.getActivityStackMgr().popNofinishActivity(this);
		super.finish();
		finishAnim();
		if (isPvEnable) {
			endPv();
		}
	}

	public void showException(Exception e) {

	}

	public void showException(String e) {

	}

	public void loadData() throws ApiException {

	}

	private class LoadThread extends Thread {
		BaseActivity activity;

		private LoadThread(BaseActivity activity) {
			super();
			this.activity = activity;
		}

		// public LoadThread(BaseActivity activity, LoadThread thread) {
		// this(activity);
		// }

		@Override
		public void run() {
			handler.sendEmptyMessage(0);
			try {
				activity.loadData();
			} catch (ApiException e) {
				handler.sendEmptyMessage(2);
				this.activity.showException(e);
				return;
			}

			handler.sendEmptyMessage(1);

			return;
		}
	}

	@Override
	public void onSkinChanged() {
		// TODO Auto-generated method stub

	}
}
