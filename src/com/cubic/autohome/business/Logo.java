package com.cubic.autohome.business;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cubic.autohome.common.view.BaseActivity;
import com.cubic.autohome.common.view.RemoteImageView;
import com.cubic.autohome.common.view.image.core.ImageLoaderConfiguration;

public class Logo extends BaseActivity {

	private final int AD_FAIL = -1;
	private final int AD_IF_SUCCESS = 1;
	private final int AD_IMG_SUCCESS = 3;
	private final int AD_INITLIZE = 0;
	private final int AD_NONE = 2;
	private int articleType = 1;
	private String exnewsTitle;
	private int exnewsid;
	private String exnewsmessageid;
	private int getAdType = 0;
	private boolean gotoNextPage = false;
	private RemoteImageView img_startup;
	private boolean isExternalJump = false;
	private RelativeLayout ll_bottomLayout;

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				// Logo.this.img_startup
				break;
			case 1:

			}
			Logo.this.timer.cancel();
			Logo.this.go2NextPage();
		};
	};

	private String messagetypename;
	private int newsid = -1;
	private boolean second3Already = false;
	private MyCountDownTimer timer;
	private TopicEntity topicEntity;
	private TextView tvPass;
	private TextView tvVersion;
	private TextView versionName;

	private void go2NextPage() {
//		initConfig();
//		if (!Environment.getExternalStorageState().equals("mounted")) {
//			DataCache.isHaveSDCARD = 0;
//		}
		if (SysUtil.isFirstUseApp(this)) {
			if (this.isExternalJump) {
				externalJump();
				return;
			}
			startActivity(new Intent(this, IntroductionActivity.class));
			finish();
		}
		
		Intent intent = new Intent(this, MainActivity.class);
		Intent paramIntent = getIntent();
		Bundle paramBundle = paramIntent.getExtras();
		if (paramBundle != null) {
			int i = paramBundle.getInt("club", 0);
			int j = paramBundle.getInt("comment", 0);
			int k = paramBundle.getInt("letter", 0);
			boolean bool = paramBundle.getBoolean("fromnotification", false);
			String msgOID = paramBundle.getString("msgOID");
			String pushcount = paramBundle.getString("pushcount");
			intent.putExtras(paramBundle);
			pushCountTask(i, j, k, bool, msgOID, pushcount);
		}
		startActivity(intent);
		finish();
	}
	
	private void pushCountTask(int club, int comment, int letter, boolean notification, String msgOID, String pushcount) {
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intent = getIntent();
		if ("android.intent.action.VIEW".equals(intent.getAction())) {
			this.isExternalJump = true;
		}
	}

	private class MyCountDownTimer extends CountDownTimer {

		public MyCountDownTimer(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onTick(long millisUntilFinished) {
			if ((200L + millisUntilFinished) / 1000L == 2L) {
				if (Logo.this.getAdType == -1 || Logo.this.getAdType == 2) {
					Logo.this.mHandler.sendEmptyMessage(1);
				}
			}
			Logo.this.second3Already = true;
		}

		@Override
		public void onFinish() {
			if (Logo.this.getAdType != 3) {
				Logo.this.go2NextPage();
			}
		}

	}
}
