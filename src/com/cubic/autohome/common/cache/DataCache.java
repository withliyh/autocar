package com.cubic.autohome.common.cache;

import java.util.ArrayList;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.cubic.autohome.R;
import com.cubic.autohome.business.MyApplication;

public class DataCache {

	private static String LocationCityName = "";
	private static int LocationCityId = -1;
	private static String LocationProvinceName = "";
	private static int LocationProvinceId = -1;
	private static int ScreenHeight = 0;
	private static int ScreenWidth = 0;
	private static int ScreenWidthForPage;
	private static int appId = 1;
	private static String appKey = null;
	private static int clientType = 2;

	public static boolean firstViewLoad_Begin = false;
	public static ArrayList<String> imageInThread = new ArrayList<>();
	public static boolean install = false;
	public static int isAction = -1;
	public static boolean isCompressing = false;
	public static int isHaveSDCARD = -1;

	private static String myProvinceId;
	private static int mycityid = 0;
	private static String mycityname = "福州";
	private static String phoneInfo = null;
	private static int realCityId = 0;

	public static String registCodeCache = "";
	public static String registPhoneCache = "";
	public static String replyCount = "0";
	public static ArrayList<String> selectImages = new ArrayList<>();

	private static int versionCode = 0;

	private static void CreateAppKey() {
		try {
			Context context = MyApplication.getContext();
			View view1 = View.inflate(context, R.layout.not_delete, null);
			TextView tt1 = (TextView) view1.findViewById(R.id.tvtv3);// 2
			TextView tt2 = (TextView) view1.findViewById(R.id.tvtv4);// 3

			View view2 = View.inflate(context, R.layout.not_delete2, null);
			TextView tt3 = (TextView) view2.findViewById(R.id.tvtv2);// 4
			TextView tt4 = (TextView) view2.findViewById(R.id.tvtv5);// 5

			View view3 = View.inflate(context, R.layout.car_series_noborder,
					null);
			TextView tt5 = (TextView) view3.findViewById(R.id.tvtv1);// 6
			TextView tt6 = (TextView) view3.findViewById(R.id.tvtv4);// 7

			View view4 = View.inflate(context, R.layout.category_no_border,
					null);
			TextView tt7 = (TextView) view4.findViewById(R.id.tvtv3); // 8

			StringBuilder sb = new StringBuilder("@");
			sb.append(tt7.getText());
			sb.append(tt6.getText());
			sb.append(tt4.getText());
			sb.append(tt5.getText());
			sb.append(tt1.getText());
			sb.append(tt2.getText());
			sb.append(tt3.getText());
			sb.append("@");
			sb.append(tt4.getText());

			appKey = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			appKey = null;
		}
	}
	
	public static synchronized String getAppKey() {
		if (TextUtils.isEmpty(appKey)) {
			CreateAppKey();
		}
		return appKey;
	}
	
	public static int getIsHaveSDCARD() {
		if (isHaveSDCARD == -1) {
			boolean b = Environment.getExternalStorageState().equals("mounted");
			if (!b) {
				isHaveSDCARD = 0;
			} else {
				isHaveSDCARD = 1;
				String[] freeSize = DiskUtil.getSDCardStorageFreeSize();
				int size = Integer.parseInt(freeSize[2]);
				if (size < 3) {
					isHaveSDCARD = 0;
				} else {
					if (freeSize[1].equals("MB")) {
						isHaveSDCARD = 0;
					} 
				}
			}
		}
		return isHaveSDCARD;
	}

	public static String getLocationCityName() {
		String cityName = SpHelper.getLocationCityName();
		LocationCityName = cityName;
		return cityName;
	}
	
	public static int getLocationCityid() {
		if (LocationCityId == -1) {
			int cityId = SpHelper.getLocationCityId();
			LocationCityId = cityId;
		}
		return LocationCityId;
	}
	
	public static String getLocationProvinceName() {
		if (TextUtils.isEmpty(LocationProvinceName)) {
			LocationProvinceName = SpHelper.getLocationProvinceName();
		}
		return LocationProvinceName;
	}
	
	public static int getLocationProvinceid() {
		if (LocationProvinceId == -1) {
			LocationProvinceId = SpHelper.getLocationProvinceId();
		}
		return LocationProvinceId;
	}
	
	public static int getMycityid() {
		mycityid = SpHelper.getMyCityId();
		return mycityid;
	}
	
	public static String getMyprovinceId() {
		if (TextUtils.isEmpty(myProvinceId)) {
			myProvinceId = SpHelper.getMyProvinceId();
		}
		return myProvinceId;
	}
	
	public static float getPhoneDensity() {
		return SpHelper.getPhoneDensity();
	}
	
	public static int getRealLocationCityID() {
		if (realCityId == 0) {
			realCityId = SpHelper.getMyRealCityId();
		}
		return realCityId;
	}
	
	public static int getScreenWidth() {
		if (ScreenWidth == 0) {
			ScreenWidth = SpHelper.getScreenWidth();
		}
		return ScreenWidth;
	}
	
	public static int getScreenHeight() {
		if (ScreenHeight == 0) {
			ScreenHeight = SpHelper.getScreenHeight();
		}
		return ScreenHeight;
	}
	
	public static int getScreenWidthForPage() {
		if (ScreenWidthForPage == 0) {
			ScreenWidthForPage = SpHelper.getScreenWidthForPage();
		}
		return ScreenWidthForPage;
	}
	
	public static int getVersionCode() {
		return SpHelper.getAutohomeVersionCode();
	}
	
	public static void setLocationCityName(String locationCityName) {
		LocationCityName = locationCityName;
		SpHelper.saveLocationCityName(locationCityName);
	}
	
	public static void setLocationCityid(int locationCityid) {
		LocationCityId = locationCityid;
		SpHelper.saveLocationCityId(locationCityid);
	}
	
	public static void setLocationProvinceName(String locationProvinceName) {
		LocationProvinceName = locationProvinceName;
		SpHelper.saveLocationProvinceName(locationProvinceName);
	}
	
	public static void setLocationProvinceid(int locationProcinveid) {
		LocationProvinceId = locationProcinveid;
		SpHelper.saveLocationProvinceId(locationProcinveid);
	}
	
	public static void setMyProvinceId(String provinceId) {
		myProvinceId = provinceId;
		SpHelper.setMyProvinceId(provinceId);
	}
	
	public static void setMycityid(int mycityid) {
		DataCache.mycityid = mycityid;
		SpHelper.saveMyCityId(mycityid);
	}
	
	public static void setMycityname(String mycityname) {
		DataCache.mycityname = mycityname;
		SpHelper.saveMyCityName(mycityname);
	}
	
	public static void setPhoneDensity(float paraDensity) {
		SpHelper.savePhoneDensity(paraDensity);
	}
	
	
	public static void setRealLocaitonCityId(int cityid) {
		realCityId = cityid;
		SpHelper.saveRealCityId(cityid);
	}
	
	public static void setScreenWidth(int width) {
		ScreenWidth = width;
		SpHelper.saveScreenWidth(width);
	}
	
	public static void setScreenHeight(int height) {
		ScreenHeight = height;
		SpHelper.saveScreenHeight(height);
	}
	
	public static void setScreenWidthForPage() {
		int width = getScreenWidth();
		int density = (int) getPhoneDensity();
		ScreenWidthForPage = width / density;
		SpHelper.saveScreenWidthForPage(ScreenWidthForPage);
	}
	
	public static void setVersionCode(int versionCode) {
		DataCache.versionCode = versionCode;
		SpHelper.setAutohomeVersionCode(versionCode);
	}
	
	
}
