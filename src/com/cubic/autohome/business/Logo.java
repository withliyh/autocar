package com.cubic.autohome.business;

import java.io.File;
import java.io.IOException;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cubic.autohome.common.view.BaseActivity;
import com.cubic.autohome.common.view.RemoteImageView;
import com.cubic.autohome.common.view.image.cache.disc.DiskCache;
import com.cubic.autohome.common.view.image.cache.disc.impl.ext.LruDiskCache;
import com.cubic.autohome.common.view.image.cache.disc.naming.Md5FileNameGenerator;
import com.cubic.autohome.common.view.image.cache.memory.impl.WeakMemoryCache;
import com.cubic.autohome.common.view.image.core.DisplayImageOptions;
import com.cubic.autohome.common.view.image.core.ImageLoader;
import com.cubic.autohome.common.view.image.core.ImageLoaderConfiguration;
import com.cubic.autohome.common.view.image.core.assist.ImageScaleType;
import com.cubic.autohome.common.view.image.utils.L;
import com.cubic.autohome.common.view.image.utils.StorageUtils;

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

		if (!ImageLoader.getInstance().isInited()) {
			initImageConfig(this);
		}
		
		Intent intent = getIntent();
		if ("android.intent.action.VIEW".equals(intent.getAction())) {
			this.isExternalJump = true;
			Uri uri = intent.getData();
			Log.d("JIMMY", "uri :" + uri);
			if (uri != null) {
				String typeStr = uri.getQueryParameter("type");
				int typeInt = Integer.parseInt(typeStr);
				this.articleType = typeInt;
			} else {
				this.isExternalJump = false;
			}
		}
		if (Build.VERSION.SDK_INT < 0xE) {
			
		}
	}
	
	private void initImageConfig(Context context) {
		int maxmen = (int) Runtime.getRuntime().maxMemory();
		int memoryCacheSize = maxmen / 0x8;
		File cacheDir = StorageUtils.getOwnCacheDirectory(context,
				"autohomemain/img");

		DisplayImageOptions.Builder builder = new DisplayImageOptions.Builder();
		builder.cacheOnDisk(true);
		builder.cacheInMemory(true);
		builder.imageScaleType(ImageScaleType.NONE);
		DisplayImageOptions options = builder.build();

		String cacheDirPath = Environment.getDataDirectory().getAbsolutePath();
		cacheDirPath = cacheDirPath + File.separator + "cacheDir";
		StorageUtils.getOwnCacheDirectory(context, cacheDirPath);
		try {
			ImageLoaderConfiguration.Builder configurationBuilder = new ImageLoaderConfiguration.Builder(
					context);
			configurationBuilder.threadPoolSize(0x5);
			configurationBuilder.taskExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
			configurationBuilder
					.taskExecutorForCachedImages(AsyncTask.THREAD_POOL_EXECUTOR);
			configurationBuilder.denyCacheImageMultipleSizesInMemory();
			configurationBuilder.memoryCache(new WeakMemoryCache());
			DiskCache diskCache = new LruDiskCache(cacheDir,
					new Md5FileNameGenerator(), 0x3200000);
			configurationBuilder.diskCache(diskCache);
			configurationBuilder.defaultDisplayImageOptions(options);
			configurationBuilder.writeDebugLogs();
			ImageLoaderConfiguration config = configurationBuilder.build();

			ImageLoader.getInstance().init(config);
		} catch (IOException e) {
			e.printStackTrace();
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
