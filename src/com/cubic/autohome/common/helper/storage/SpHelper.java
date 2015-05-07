package com.cubic.autohome.common.helper.storage;

import java.util.ArrayList;

import com.cubic.autohome.business.MyApplication;
import com.cubic.autohome.business.user.owner.bean.AppRecommandEntity;
import com.cubic.autohome.common.util.ArrayListAndStringUtil;

import android.content.Context;
import android.content.SharedPreferences;
import android.webkit.WebView;

public class SpHelper {
	private static final String APPRECOMMAND = "apprecommand";
	public static final String APPS_UPLOAD_STATE = "apps_upload_state";
	private static final String ARTICLECONFIG = "articleConfig";
	private static final String ARGICLE_CSS_TAG = "articleCssTag";
	public static final String AUTOHOME = "autohome";
	private static final String AUTOHOME_VERSION_CODE = "versionCode";
	private static final String CAR_SPEC_LEVEL_ID = "car_spec_level_id";
	private static final String CAR_SPEC_LEVEL_NAME = "car_spec_level_name";
	private static final String CAR_SPEC_ORDER_ID = "car_spec_order_id";
	private static final String CAR_SPEC_ORDER_NAME = "car_spec_order_name";
	private static final String CAR_SPEC_PRICE_ID = "car_spec_price_id";
	private static final String CAR_SPEC_PRICE_NAME = "car_spec_price_name";
	private static final String CLUBUSERSHOW = "clubUserShow";
	private static final String CLUE_RECAPP_CLOSET = "clubrecommentappcloset";
	private static final String CLUB_REPLAY_REFRESH = "club_replay_refresh";
	private static final String CLUE_SUB_TIMESTAMP = "club_sub_time_stamp";
	private static final String COMMENT_REPLY_REFRESH = "comment_reply_refresh";
	private static final String COMMON_JS_TAG = "commonJsTag";
	private static final String COMPARE_CARS = "compare_cars";
	private static final String COUNTRYCONFIG = "countryConfig";
	public static final String CURRENT_VERSION = "current_version";
	private static final String DEALER_TELIMG_TIME = "dealer_telimg_time";
	public static final String DRAFT_COUNT = "draft_count";
	public static final String FEED_TIME = "feedtime";
	public static final String FONT_BIG_JS = "big";
	public static final String FONT_MIDDLE_JS = "normal";
	public static final String FONT_SMALL_JS = "small";
	public static final String GUIDE_PAGE = "guide_page";
	public static final String GUIDE_PAGE_FOR_FM = "guide_page_for_fm";
	public static final String GUIDE_PAGE_FOR_MAIN = "guide_page_for_main_2";
	public static final String GUIDE_PAGE_FOR_SALE = "guide_page_for_sale";
	public static final String GUIDE_PAGE_FOR_SERIES_DEALER = "guide_page_for_series_dealer";
	public static final int GUIDE_UNUSE = 0x0;
	public static final int GUIDE_USED = 0x1;
	public static final String HAS_NEW_BULIETINS = "has_new_bulletins";
	public static final String INIT_BAIDU = "init_baidu";
	public static final String INSTALLED_SKIN_NIGHT_PACKAGE_VERSION = "skinNightVersion";
	private static final String ISSHOWAPP = "isshowapp";

	public static final String IS_ALREADY_CREATE_SHORTCAT = "IS_ALREADY_CREATE_SHORTCAT";
	private static final String IS_FILTERBAR_OPEN = "is_filterbar_open";
	private static final String JQUERYNEW_JS_TAG = "jquerynewJsTag";
	private static final String JQUERY_JS_TAG = "jqueryJsTag";
	private static String KEY_DEBUG_MODE = null;
	private static final String LEFT_COMPARE_CARID = "left_compare_carID";
	private static final String LEVELCONFIG = "levelConfig";
	private static final String LOADVIDEO_JS_TAG = "loadvideoJsTag";
	public static final String LOCAL_CHANNEL = "local_channel";
	public static final String LOCAL_DEVICE_ID = "local_device_id";
	private static final String LOCAL_LA = "local_la";
	private static final String LOCAL_LO = "local_lo";
	private static final String LOCATION_CITY_ID = "location_city_id";
	private static final String LOCATION_CITY_NAME = "location_city_name";
	private static final String LOCATION_PROVINCE_ID = "location_province_id";
	private static final String LOCATION_PROVINCE_NAME = "location_province_name";
	private static final String MORE_RED_DOT = "more_red_dot";
	private static final String MYTOPIC_REFRESH = "mytopic_refresh";
	private static final String MY_CITY_ID = "my_city_id";
	private static final String MY_CITY_NAME = "my_city_name";
	private static final String MY_PROVINCE_ID = "my_province_id";
	private static final String MY_REAL_CITY_ID = "my_real_city_id";
	private static String NAME_DEBUG_MODE = null;
	private static final String NEWSTYPECONFIG = "newstypeConfig";
	public static final String NEW_BULLETINS_LASTTIME = "new_bulletins_lasttime";
	public static final String NEW_FUNCTION = "new_func";
	public static final String NEW_FUNCTION_KEY = "new_func_key";
	public static final String ONLYLOOKMODE = "onlylook_mode";
	public static final int ONLYLOOK_ALL = 0x0;
	public static final int ONLYLOOK_MAIN = 0x1;
	public static final int PAGE_FONT_BIG = 0x1;
	public static final int PAGE_FONT_MIDDLE = 0x0;
	public static final String PAGE_FONT_SIZE = "font_size";
	public static final int PAGE_FONT_SMALL = 0x2;
	private static final String PERSUADER_CSS_TAG = "persuaderCssTag";
	public static final String PHONE_AUTH = "phone_auth";
	private static final String PHONE_DENSITY = "phone_density";
	private static final String PHONE_INFO = "phone_info";
	private static final String PICCONFIG = "picConfig";
	private static final String PRICEBETWEENCONFIG = "pricebetweenConfig";
	private static final String PRICE_CSS_TAG = "priceCssTag";
	private static final String RIGHT_COMPARE_CARID = "right_compare_carID";
	private static final String SALE_ACTIVITY_RED_DOT = "sale_activity_red_dot";
	private static final String SALE_CITY_ID = "sale_city_id";
	private static final String SALE_PROVINCE_ID = "sale_province_id";
	private static final String SALE_RED_DOT = "sale_red_dot";
	private static final String SCREEN_HEIGHT = "screen_height";
	private static final String SCREEN_WIDTH = "screen_width";
	private static final String SCREEN_WIDTH_FOR_PAGE = "screen_width_page";
	public static final String SEARCH_HISTORY = "search_history";
	public static final String SEARCH_LOG_KEY = "search_log_key";
	public static final String SERIES_ARTICLE_POSITION = "seriesarticleposition";
	public static final String SHOW_TUANGOU_NEW_PROMPT_ISCLOSE = "show_tuangou_new_prompt_isclose";
	public static final String SKIN_CONFIG = "skinconfig";
	private static final String SLIP_SWITCH = "slip_switch";
	public static final String SPMODE = "spmode";
	public static final int SPMODE_BIG = 0x0;
	public static final int SPMODE_SMALL = 0x1;
	public static final String SWITCH_CITY_FLAG = "switchcityflag";
	public static final String SWITCH_LOCATION_CITY_FLAG = "switch_location_city_flag";
	private static final String SYSTEM_TIMESTAMP = "time_stamp";
	public static final String THEME_PACKAGE_PARAMS = "themePackage";
	public static final String THIRD_QQ_NAME = "third_qq_name";
	public static final String THIRD_QQ_OPENID = "third_qq_openid";
	public static final String THIRD_QQ_TOKEN = "third_qq_token";
	public static final String THIRD_SINA_NAME = "third_sina_name";
	public static final String THIRD_SINA_OPENID = "third_sina_openid";
	public static final String THIRD_SINA_TOKEN = "third_sina_token";
	public static final String TUANGOU_LAST_PUBLISH_TIME = "tuangou_last_publish_time";
	public static final String UNINSTALLED_SKIN_NIGHT_PACKAGE_VERSION = "unInstall_skinNightVersion";
	private static final String USER_INFO = "user_info";
	private static final String VIDEO_CSS_TAG = "videoCssTag";
	private static final String VIDEO_DISABLED_TIPS = "video_disabled_tips";
	private static final String VIDEO_ENABLED = "video_enabled";
	private static final String VIDEO_HEIGHT = "video_height";
	private static final String VIDEO_MAX_TIME = "video_max_time";
	private static final String VIDEO_RATE = "video_rate";
	private static final String VIDEO_WIDTH = "video_width";
	
	
	private static SharedPreferences mSearchHistory;
	private static SharedPreferences mSkinConfig;
	private static SpHelper mSpHelper;
	private static SharedPreferences mySharedPreferences;
	
	static {
		mySharedPreferences = MyApplication.getContext().getSharedPreferences("autohome", 0x6);
		mSkinConfig = MyApplication.getContext().getSharedPreferences("skinconfig", 0x6);
		mSearchHistory = MyApplication.getContext().getSharedPreferences("search_history", 0x6);
		NAME_DEBUG_MODE = "autohome_debug_mode_name";
		KEY_DEBUG_MODE = "autohome_debug_mode_key";
	}
	
	public SpHelper() {
	}
	
	public static void clearDraftCount() {
		commitInt("draft_count", 0);
	}
	
	public static boolean commitBoolean(String key, boolean value) {
		return mySharedPreferences.edit().putBoolean(key, value).commit();
	}
	
	public static boolean commitClubSubTimeStamp(long time) {
		return commitLong("time_stamp", time);
	}
	
	public static boolean commitDouble(String key, double value) {
		return mySharedPreferences.edit().putString(key, String.valueOf(value)).commit();
	}
	
	public static boolean commitFloat(String key, float value) {
		return mySharedPreferences.edit().putFloat(key, value).commit();
	}
	
	public static boolean commitInt(String key, int value) {
		return mySharedPreferences.edit().putInt(key, value).commit();
	}
	
	public static boolean commitIsShowApp(int value) {
		return mySharedPreferences.edit().putInt("isshowapp", value).commit();
	}
	
	public static boolean commitLong(String key, long value) {
		return mySharedPreferences.edit().putLong(key, value).commit();
	}
	
	public static boolean commitString(String key, String value) {
		return mySharedPreferences.edit().putString(key, value).commit();
	}
	
	public static boolean contains(String key) {
		return mySharedPreferences.contains(key);
	}
	
	public static boolean containsClubSubTimeStamp() {
		return contains("time_stamp");
	}
	
	public static ArrayList<AppRecommandEntity> getAppRecommand() {
		String recommand = getString("apprecommand", null);
		ArrayListAndStringUtil util =  new ArrayListAndStringUtil();
		return util.StringToArrayList(recommand);
	}
	
	public static boolean getAppsUploadState() {
		return mySharedPreferences.getBoolean("apps_upload_state", false);
	}
	
	public static long getArticleCssTag() {
		return getLong("articeCssTag", 0);
	}
	
	public static ArrayList<String> getAskPriceUserNameAndPhone() {
		String config = getString("user_info", null);
		ArrayListAndStringUtil util =  new ArrayListAndStringUtil();
		return util.StringToArrayList(config);
	}
	
	public static int getAutohomeVersionCode() {
		return getInt("versionCode", 0);
	}
	
	public static boolean getBoolean(String key, boolean defaultValue) {
		return mySharedPreferences.getBoolean(key, defaultValue);
	}
	
	public static String getChannel() {
		return mySharedPreferences.getString("local_channel", "");
	}
	
	public static long getClubRecappCloset() {
		return mySharedPreferences.getLong("clubrecommentappcloset", 0);
	}

	public static long getClubSubTimeStamp(long defaultValue) {
		return getLong("time_stamp", defaultValue);
	}
	
	public static String getClubUserShow() {
		return getString("clubUserShow", "");
	}
	
	public static long getCommonJsTag() {
		return getLong("commonJsTag", 0);
	}
	
	public static ArrayList<SpecEntity> getCompareCars() {
		String config = getString("compare_cars", null);
		ArrayListAndStringUtil util =  new ArrayListAndStringUtil();
		return util.StringToArrayList(config);
	}
	
	public static boolean getDevDebugMode(Context context) {
		SharedPreferences sp = context.getSharedPreferences(NAME_DEBUG_MODE, 0);
		return sp.getBoolean(KEY_DEBUG_MODE, false);
	}
	
	public static String getDouble(String key) {
		return mySharedPreferences.getString(key, "0.00");
	}
	
	public static int getDraftCount() {
		return getInt("draft_count", 0);
	}
	
	public static String getFeedTime() {
		return mySharedPreferences.getString("feedtime", "0");
	}
	
	
	public static float getFloat(String key, float defval) {
		return mySharedPreferences.getFloat(key, defval);
	}
	
	public static int getFontSize() {
		return getInt("font_size", 0);
	}
	
	public static int getGuidePage() {
		return getInt("guide_page", 0);
	}
	
	public static int getInstalledSkinVersion() {
		return mySharedPreferences.getInt("skinNightVersion", 0);
	}
	
	public static int getInt(String key) {
		return mySharedPreferences.getInt(key, 1);
	}
	
	public static int getInt(String key, int defval) {
		return mySharedPreferences.getInt(key, defval);
	}
	
	public static int getIsShowApp() {
		return mySharedPreferences.getInt("isshowapp", 0);
	}
	
	public static boolean getIsSwitchFlag() {
		return mySharedPreferences.getBoolean("switchcityflag", false);
	}
	
	
	
	public static boolean getIsSwitchLocationFlag() {
		return mySharedPreferences.getBoolean("switch_location_city_flag", false);
	}
   

	public static long getJqueryJsTag() {
		return getLong("jqueryJsTag", 0);
	}


	public static long getJquerynewJsTag() {
		return getLong("jquerynewJsTag", 0);
	}


	public static String getLeftCompareCarID() {
		return getString("left_compare_carID", "0");
	}


	public static long getLoadvideoJsTag() {
		return getLong("loadvideoJsTag", 0);
	}
    

	public static String getLocalDeviceID() {
		return mySharedPreferences.getString("local_device_id", null);
	}


	public static String getLocalLa() {
		return getDouble("local_la");
	}


	public static String getLocalLo() {
		return getDouble("local_lo");
	}

	public static int getLocationCityId() {
		return getInt("location_city_id", 0x1AE14);
	}


	public static String getLocationCityName() {
		return getString("location_city_name", "北京");
	}


	public static int getLocationProvinceId() {
		return getInt("location_province_id", 0);
	}


	public static String getLocationProvinceName() {
		return getString("location_province_name", "");
	}


	public static long getLong(String key, long defaultValue) {
		return mySharedPreferences.getLong(key, defaultValue);
	}

	public static int getMyCityId() {
		return getInt("my_city_id", 0x1AE14);
	}


	public static String getMyCityName() {
		return getString("my_city_name", "北京");
	}

	public static String getMyProvinceId() {
		return getString("my_province_id", "");
	}


	public static int getMyRealCityId() {
		return getInt("my_real_city_id", 0x1AE14);
	}


	public static String getNewBulletinsLasttime() {
		return getString("new_bulletins_lasttime", "0");
	}

	public static ArrayList<SpecEntity> getOldCompareCars() {
		String config = getString("compare_cars", null);
		ArrayListAndStringUtil util = new ArrayListAndStringUtil();
		return util.StringToArrayList(config);
	}


	public static long getPersuaderCssTag() {
		return getLong("persuaderCssTag", 0);
	}


	public static boolean getPhoneAuth() {
		return getBoolean("phone_auth", false);
	}


	public static float getPhoneDensity() {
		return getFloat("phone_density", 0x3F800000);
	}


	public static long getPriceCssTag() {
		return getLong("priceCssTag", 0);
	}


	public static String getRightCompareCarID() {
		return getString("right_compare_carID", "0");
	}


	public static int getSaveOverViewClickNew(int typeid) {
		return getInt(String.valueOf(typeid), -1);
	}


	public static int getSaveSaleClickNew(int seriesId) {
		return getInt(String.valueOf(seriesId), -1);
	}


	public static int getScreenHeight() {
		return getInt("screen_height");
	}


	public static int getScreenWidth() {
		return getInt("screen_width");
	}
	
	
	public static int getScreenWidthForPage() {
		return getInt("screen_width_page");
	}


	public static String getSearchHistory() {
		return mSearchHistory.getString("search_log_key", "");
	}


	public static int getSeriesArticlePosition() {
		return mySharedPreferences.getInt("seriesarticleposition", 0);
	}


	public static boolean getSlipSwitch() {
		return getBoolean("slip_switch", false);
	}


	public static int getSpMode() {
		return getInt("spmode", 0);
	}


	public static String getString(String key) {
		return mySharedPreferences.getString(key, "");
	}


	public static String getString(String key , String defaultValue) {
		return mySharedPreferences.getString(key, defaultValue);
	}


	public static String getThemePackageName() {
		return mSkinConfig.getString("themePackage", "");
	}


	public static long getVideoCssTag() {
		return getLong("videoCssTag", 0);
	}


	public static String getVideoDisabledTips() {
		return getString("video_disabled_tips", "");
	}


	public static int getVideoEnabled() {
		return getInt("video_enabled", 1);
	}


	public static int getVideoHeight() {
		return getInt("video_height", 0x168);
	}


	public static int getVideoMaxTime() {
		return getInt("video_max_time", 0x1e);
	}
	
	
	public static int getVideoRate() {
		return getInt("video_rate", 0x2);
	}
	
	
	public static int getVideoWidth() {
		return getInt("video_width", 0x1E0);
	}
	
	public static boolean hasNewBulletins() {
		return getBoolean("has_new_bulletins", true);
	}

	public static void increaseDraftCount() {
		int total = getInt("draft_count", 0);
		if (total < 0) {
			total = 0;
		}
		commitInt("draft_count", total+1);
	}


	public static boolean isShowGuideForFM() {
		int is = getInt("guide_page_for_fm", 0);
		if (is != 0) {
			return false;
		}
		return true;
	}
	
	
	public static boolean isShowGuideForMain2() {
		int is = getInt("guide_page_for_main_2", 0);
		if (is != 0) {
			return false;
		}
		return true;
	}


	public static boolean isShowGuideForSale() {
		return getBoolean("guide_page_for_sale", false);
	}
	
	
	public static boolean isShowGuideForSeriesDealer() {
		int is = getInt("guide_page_for_series_dealer", 0);
		if (is != 0) {
			return false;
		}
		return true;
	}


	public static boolean isShowMoreRedDot() {
		return getBoolean("more_red_dot", false);
	}
	
	
	public static void reduceDraftCount() {
		int total =  getInt("draft_count", 0);
		if (total > 0 ) {
			total --;
		}
		commitInt("draft_count", total);
	}
	
	
	public static void registerPrefChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
		mSkinConfig.registerOnSharedPreferenceChangeListener(listener);
	}


	public static boolean remove(String key) {
		return mySharedPreferences.edit().remove(key).commit();
	}


	public static boolean saveAppRecommand(ArrayList<AppRecommandEntity> apprecommand) {
		ArrayListAndStringUtil util = new ArrayListAndStringUtil();
		String recommand = util.ArrayListToString(apprecommand);
		return commitString("apprecommand", recommand);
	}
	
	
	public static boolean saveAskPriceUserNameAndPhone(ArrayList<String> userInfo) {
		ArrayListAndStringUtil util = new ArrayListAndStringUtil();
		String config = util.ArrayListToString(userInfo);
		return commitString("user_info", config);
	}
	
	
	public static boolean saveClubReplyRefresh(boolean isRefresh) {
		return commitBoolean("club_reply_refresh", isRefresh);
	}
	
	
	public static boolean saveCommentReplyRefresh(boolean isRefresh) {
		return commitBoolean("comment_reply_refresh", isRefresh);
	}


	public static boolean saveCompareCars(ArrayList<SpecEntity> compareCars) {
		ArrayListAndStringUtil util = new ArrayListAndStringUtil();
		String config = util.ArrayListToString(compareCars);
		return commitString("compare_cars", config);
	}
	
	
	public static boolean saveLeftCompareCarId(String leftCarId) {
		return commitString("left_compare_carID", leftCarId);
	}
	
	
	public static boolean  saveLocalLa(double local_la) {
		return commitDouble("local_la", local_la);
	}
	
	public static boolean  saveLocalLo(double local_lo) {
		return commitDouble("local_lo", local_lo);
	}


	public static boolean saveLocationCityId(int locationCityId) {
		return commitInt("location_city_id", locationCityId);
	}
	
	
	public static boolean saveLocationCityName(String locationCityName) {
		return commitString("location_city_name", locationCityName);
	}

	public static boolean saveLocationProvinceId(int locationProvinceId) {
		return commitInt("location_province_id", locationProvinceId);
	}


	public static boolean saveLocationProvinceName(String locationProvinceName) {
		return commitString("location_province_name", locationProvinceName);
	}
	
	
	public static boolean saveMyCityId(int myCityId) {
		return commitInt("my_city_id", myCityId);
	}
	
	
	public static boolean saveMyCityName(String myCityName) {
		return commitString("my_city_name", myCityName);
	}


	public static boolean saveMyRealCityId(int myCityId) {
		return commitInt("my_real_city_id", myCityId);
	}
	
	
	public static boolean saveMyTopicRefresh(boolean isRefresh) {
		return commitBoolean("mytopic_refresh", isRefresh);
	}


	public static boolean saveOverViewClickNew(int typeid) {
		return mySharedPreferences.edit().putInt(String.valueOf(typeid), 0x1).commit();
	}
	
	
	public static boolean savePhoneDensity(float phoneDensity) {
		return commitFloat("phone_density" , phoneDensity);
	}


	public static boolean saveRightCompareCarId(String rightCarId) {
		return commitString("right_compare_carID", rightCarId);
	}
	
	
	public static boolean saveScreenHeight(int screenHeight) {
		return commitInt("screen_height", screenHeight);
	}
	
	
	public static boolean saveScreenWidth(int screenWidth) {
		return commitInt("screen_width", screenWidth);
	}
	
	
	public static boolean saveScreenWidthForPage(int screenWidthForPage) {
		return commitInt("screen_width_page", screenWidthForPage);
	}
	
	
	public static boolean setAppsUploadState(boolean state) {
		return mySharedPreferences.edit().putBoolean("apps_upload_state", state).commit();
	}


	public static boolean setArticleCssTag(long changeTag) {
		return commitLong("articleCssTag", changeTag);
	}
	
	
	public static boolean setAutohomeVersionCode(int versionCode) {
		return commitInt("versionCode", versionCode);
	}


	public static boolean setChannel(String value) {
		return mySharedPreferences.edit().putString("local_channel", value).commit();
	}
	
	
	public static boolean setClubRecappCloset(long value) {
		return mySharedPreferences.edit().putLong("clubrecommentappcloset", value).commit();
	}
	
	
	public static boolean setClubUserShow(String clubUserShow) {
		return commitString("clubUserShow", clubUserShow);
	}


	public static boolean setCommonJsTag(long changeTag) {
		return commitLong("commonJsTag", changeTag);
	}
	
	
	public static void setDevDebugMode(boolean isDebug, Context context) {
		SharedPreferences sp = context.getSharedPreferences(NAME_DEBUG_MODE, 0);
		sp.edit().putBoolean(KEY_DEBUG_MODE, isDebug).commit();
	}


	private static void setFontSize(WebView webview, int fs) {
		String strFs = "";
		switch (fs) {
		case 0:strFs = "normal";break;
		case 1:strFs = "big";break;
		case 2:strFs = "small";break;
		}
		String url = "javascript:ChageModel(\'" + fs + "\'" + SkinsUtil.getNightMode() + ")";
		webview.loadUrl(url);
	}
	
	
	public static boolean setFontSize(int fontSize) {
		return commitInt("font_size", fontSize);
	}


	public static boolean setGuidePage(int mode) {
		return commitInt("guide_page", mode);
	}
	
	
	public static boolean setInstalledSkinVersion(int version) {
		return mySharedPreferences.edit().putInt("skinNightVersion", version).commit();
	}
	
	
	public static boolean setIsSwitchedFlag(boolean value) {
		return mySharedPreferences.edit().putBoolean("switchcityflag", value).commit();
	}


public static boolean setIsSwitchedLocationFlag(boolean value) {
	return mySharedPreferences.edit().putBoolean("switch_location_city_flag", value).commit();
}


public static boolean setJqueryJsTag(long changeTag) {
	return commitLong("jqueryJsTag", changeTag);
}


public static boolean setJquerynewJsTag(long changeTag) {
	return commitLong("jquerynewJsTag", changeTag);
}


public static boolean setLoadvideoJsTag(long changeTag) {
	return commitLong("loadvideoJsTag", changeTag);
}


public static boolean setLocalDeviceID(String deviceID) {
	return mySharedPreferences.edit().putString("local_device_id", deviceID).commit();
}


public static boolean setMyProvinceId(String myProvinceId) {
	return commitString("my_province_id", myProvinceId);
}


public static void setNewBulletinsLasttime(String lasttime) {
	
	String last = getNewBulletinsLasttime();
	try {
	int beforeLasttime = Integer.parseInt(last);
	int afterLasttime = Integer.parseInt(lasttime);
	if (beforeLasttime > afterLasttime) {
		commitString("new_bulletins_lasttime", lasttime);
	}
	}catch (NumberFormatException e) {
		
	}
}
    

public static boolean setPersuaderCssTag(long changeTag) {
	return commitLong("persuaderCssTag", changeTag);
}


public static boolean setPhoneAuth(boolean isPhoneAuth) {
	return commitBoolean("phone_auth", isPhoneAuth);
}


public static boolean setPriceCssTag(long changeTag) {
	return commitLong("priceCssTag", changeTag);
}

public static boolean setSearchHistory(String value) {
	return mSearchHistory.edit().putString("search_log_key", value).commit();
}


public static boolean setSeriesArticlePosition(int seriesArticlePosition) {
	return mySharedPreferences.edit().putInt("seriesarticleposition", seriesArticlePosition).commit();
}


public static void setShowGuideForFM(int versionCode) {
	commitInt("guide_page_for_fm", versionCode);
}


public static void setShowGuideForMain2(int versionCode) {
	commitInt("guide_page_for_main_2", versionCode);
}


public static void setShowGuideForSale(int versionCode) {
	commitInt("guide_page_for_sale", versionCode);
}


public static void setShowGuideForSeriesDealer(int versionCode) {
	commitInt("guide_page_for_series_dealer", versionCode);
}


public static boolean setSpMode(int mode) {
	return commitInt("spmode", mode);
}


public static void setThemePackageName(String packageName) {
	mSkinConfig.edit().putString("themePackage", packageName).commit();
}


public static boolean setVideoCssTag(long changeTag) {
	return commitLong("videoCssTag", changeTag);
}


public static boolean setVideoDisabledTips(String disabledTips) {
	return commitString("video_disabled_tips", disabledTips);
}


public static boolean setVideoEnabled(int enabledFlag) {
	return commitInt("video_enabled", enabledFlag);
}


public static boolean setVideoHeight(int height) {
	return commitInt("video_height", height);
}


public static boolean setVideoMaxTime(int time) {
	return commitInt("video_max_time", time);
}


public static boolean setVideoRate(int rate) {
	return commitInt("video_rate", rate);
}
 

public static boolean setVideoWidth(int width) {
	return commitInt("video_width", width);
}


public static boolean sethasNewBulletins(boolean hasNewBulletins) {
	return commitBoolean("has_new_bulletins", hasNewBulletins);
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
