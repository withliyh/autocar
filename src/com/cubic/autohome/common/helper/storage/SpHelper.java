package com.cubic.autohome.common.helper.storage;

import java.util.ArrayList;

import com.cubic.autohome.business.MyApplication;
import com.cubic.autohome.business.user.owner.bean.AppRecommandEntity;

import android.content.Context;
import android.content.SharedPreferences;

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
		String recommand = getString("apprecommand", 0);
		return new ArrayListAndStringUtil.StringToArrayList(recommand);
	}
	
	public static boolean getAppsUploadState() {
		return mySharedPreferences.getBoolean("apps_upload_state", false);
	}
	
	public static long getArticleCssTag() {
		return getLong("articeCssTag", 0);
	}
	
	public static ArrayList<String> getAskPriceUserNameAndPhone() {
		String config = getString("user_info", null);
		return new ArrayListAndStringUtil.StringToArrayList(config);
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
		return new ArrayListAndStringUtil.StringToArrayList(config);
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
		return getLong("persuaderCssTag" 0);
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
		return getInt("guide_page_for_sale", 0);
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


public static saveMyRealCityId(I)Z
    .registers 2
    .param p0, "myCityId"
    .prologue
00000000  const-string            v0, "my_real_city_id"
00000004  invoke-static           SpHelper->commitInt(String, I)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

public static saveMyTopicRefresh(Z)Z
    .registers 2
    .param p0, "isRefresh"
    .prologue
00000000  const-string            v0, "mytopic_refresh"
00000004  invoke-static           SpHelper->commitBoolean(String, Z)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

public static saveOverViewClickNew(I)Z
    .registers 4
    .param p0, "typeid"
    .prologue
00000000  sget-object             v1, SpHelper->mySharedPreferences:SharedPreferences
00000004  invoke-interface        SharedPreferences->edit()SharedPreferences$Editor, v1
0000000A  move-result-object      v0
    .local v0, editor:Landroid/content/SharedPreferences$Editor;
0000000C  new-instance            v1, StringBuilder
00000010  invoke-static           String->valueOf(I)String, p0
00000016  move-result-object      v2
00000018  invoke-direct           StringBuilder-><init>(String)V, v1, v2
0000001E  invoke-virtual          StringBuilder->toString()String, v1
00000024  move-result-object      v1
00000026  const/4                 v2, 0x1
00000028  invoke-interface        SharedPreferences$Editor->putInt(String, I)SharedPreferences$Editor, v0, v1, v2
0000002E  invoke-interface        SharedPreferences$Editor->commit()Z, v0
00000034  move-result             v1
00000036  return                  v1
.end method

public static savePhoneDensity(F)Z
    .registers 2
    .param p0, "phoneDensity"
    .prologue
00000000  const-string            v0, "phone_density"
00000004  invoke-static           SpHelper->commitFloat(String, F)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

public static saveRightCompareCarId(String)Z
    .registers 2
    .param p0, "rightCarId"
    .prologue
00000000  const-string            v0, "right_compare_carID"
00000004  invoke-static           SpHelper->commitString(String, String)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

public static saveScreenHeight(I)Z
    .registers 2
    .param p0, "screenHeight"
    .prologue
00000000  const-string            v0, "screen_height"
00000004  invoke-static           SpHelper->commitInt(String, I)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

public static saveScreenWidth(I)Z
    .registers 2
    .param p0, "screenWidth"
    .prologue
00000000  const-string            v0, "screen_width"
00000004  invoke-static           SpHelper->commitInt(String, I)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

public static saveScreenWidthForPage(I)Z
    .registers 2
    .param p0, "screenWidthForPage"
    .prologue
00000000  const-string            v0, "screen_width_page"
00000004  invoke-static           SpHelper->commitInt(String, I)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

public static setAppsUploadState(Z)Z
    .registers 3
    .param p0, "state"
    .prologue
00000000  sget-object             v1, SpHelper->mySharedPreferences:SharedPreferences
00000004  invoke-interface        SharedPreferences->edit()SharedPreferences$Editor, v1
0000000A  move-result-object      v0
    .local v0, editor:Landroid/content/SharedPreferences$Editor;
0000000C  const-string            v1, "apps_upload_state"
00000010  invoke-interface        SharedPreferences$Editor->putBoolean(String, Z)SharedPreferences$Editor, v0, v1, p0
00000016  invoke-interface        SharedPreferences$Editor->commit()Z, v0
0000001C  move-result             v1
0000001E  return                  v1
.end method

public static setArticleCssTag(J)Z
    .registers 4
    .param p0, "changeTag"
    .prologue
00000000  const-string            v0, "articleCssTag"
00000004  invoke-static           SpHelper->commitLong(String, J)Z, v0, p0, p1
0000000A  move-result             v0
0000000C  return                  v0
.end method

public static setAutohomeVersionCode(I)Z
    .registers 2
    .param p0, "versionCode"
    .prologue
00000000  const-string            v0, "versionCode"
00000004  invoke-static           SpHelper->commitInt(String, I)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

public static setChannel(String)Z
    .registers 3
    .param p0, "value"
    .prologue
00000000  sget-object             v1, SpHelper->mySharedPreferences:SharedPreferences
00000004  invoke-interface        SharedPreferences->edit()SharedPreferences$Editor, v1
0000000A  move-result-object      v0
    .local v0, editor:Landroid/content/SharedPreferences$Editor;
0000000C  const-string            v1, "local_channel"
00000010  invoke-interface        SharedPreferences$Editor->putString(String, String)SharedPreferences$Editor, v0, v1, p0
00000016  invoke-interface        SharedPreferences$Editor->commit()Z, v0
0000001C  move-result             v1
0000001E  return                  v1
.end method

public static setClubRecappCloset(J)Z
    .registers 4
    .param p0, "value"
    .prologue
00000000  sget-object             v1, SpHelper->mySharedPreferences:SharedPreferences
00000004  invoke-interface        SharedPreferences->edit()SharedPreferences$Editor, v1
0000000A  move-result-object      v0
    .local v0, editor:Landroid/content/SharedPreferences$Editor;
0000000C  const-string            v1, "clubrecommentappcloset"
00000010  invoke-interface        SharedPreferences$Editor->putLong(String, J)SharedPreferences$Editor, v0, v1, p0, p1
00000016  invoke-interface        SharedPreferences$Editor->commit()Z, v0
0000001C  move-result             v1
0000001E  return                  v1
.end method

public static setClubUserShow(String)Z
    .registers 2
    .param p0, "clubUserShow"
    .prologue
00000000  const-string            v0, "clubUserShow"
00000004  invoke-static           SpHelper->commitString(String, String)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

public static setCommonJsTag(J)Z
    .registers 4
    .param p0, "changeTag"
    .prologue
00000000  const-string            v0, "commonJsTag"
00000004  invoke-static           SpHelper->commitLong(String, J)Z, v0, p0, p1
0000000A  move-result             v0
0000000C  return                  v0
.end method

public static setDevDebugMode(Z, Context)V
    .registers 6
    .param p0, "isDebug"
    .param p1, "context"
    .prologue
00000000  sget-object             v2, SpHelper->NAME_DEBUG_MODE:String
00000004  const/4                 v3, 0x0
00000006  invoke-virtual          Context->getSharedPreferences(String, I)SharedPreferences, p1, v2, v3
0000000C  move-result-object      v1
    .local v1, sp:Landroid/content/SharedPreferences;
0000000E  invoke-interface        SharedPreferences->edit()SharedPreferences$Editor, v1
00000014  move-result-object      v0
    .local v0, editor:Landroid/content/SharedPreferences$Editor;
00000016  sget-object             v2, SpHelper->KEY_DEBUG_MODE:String
0000001A  invoke-interface        SharedPreferences$Editor->putBoolean(String, Z)SharedPreferences$Editor, v0, v2, p0
00000020  invoke-interface        SharedPreferences$Editor->commit()Z, v0
00000026  return-void
.end method

private static setFontSize(WebView, I)V
    .registers 5
    .param p0, "webview"
    .param p1, "fs"
    .prologue
00000000  const-string            v0, ""
    .local v0, strFs:Ljava/lang/String;
00000004  packed-switch           p1, :6C
:A
0000000A  new-instance            v1, StringBuilder
0000000E  const-string            v2, "javascript:ChageModel(\'"
00000012  invoke-direct           StringBuilder-><init>(String)V, v1, v2
00000018  invoke-virtual          StringBuilder->append(String)StringBuilder, v1, v0
0000001E  move-result-object      v1
00000020  const-string            v2, "\',"
00000024  invoke-virtual          StringBuilder->append(String)StringBuilder, v1, v2
0000002A  move-result-object      v1
0000002C  invoke-static           SkinsUtil->getNightMode()I
00000032  move-result             v2
00000034  invoke-virtual          StringBuilder->append(I)StringBuilder, v1, v2
0000003A  move-result-object      v1
0000003C  const-string            v2, ")"
00000040  invoke-virtual          StringBuilder->append(String)StringBuilder, v1, v2
00000046  move-result-object      v1
00000048  invoke-virtual          StringBuilder->toString()String, v1
0000004E  move-result-object      v1
00000050  invoke-virtual          WebView->loadUrl(String)V, p0, v1
00000056  return-void
:58
00000058  const-string            v0, "normal"
0000005C  goto                    :A
:5E
0000005E  const-string            v0, "big"
00000062  goto                    :A
:64
00000064  const-string            v0, "small"
00000068  goto                    :A
0000006C  .packed-switch 0x0
        :58
        :5E
        :64
    .end packed-switch
.end method

public static setFontSize(I)Z
    .registers 2
    .param p0, "fontSize"
    .prologue
00000000  const-string            v0, "font_size"
00000004  invoke-static           SpHelper->commitInt(String, I)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

public static setGuidePage(I)Z
    .registers 2
    .param p0, "mode"
    .prologue
00000000  const-string            v0, "guide_page"
00000004  invoke-static           SpHelper->commitInt(String, I)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

public static setInstalledSkinVersion(I)Z
    .registers 3
    .param p0, "version"
    .prologue
00000000  sget-object             v1, SpHelper->mySharedPreferences:SharedPreferences
00000004  invoke-interface        SharedPreferences->edit()SharedPreferences$Editor, v1
0000000A  move-result-object      v0
    .local v0, editor:Landroid/content/SharedPreferences$Editor;
0000000C  const-string            v1, "skinNightVersion"
00000010  invoke-interface        SharedPreferences$Editor->putInt(String, I)SharedPreferences$Editor, v0, v1, p0
00000016  invoke-interface        SharedPreferences$Editor->commit()Z, v0
0000001C  move-result             v1
0000001E  return                  v1
.end method

public static setIsSwitchedFlag(Z)Z
    .registers 3
    .param p0, "value"
    .prologue
00000000  sget-object             v1, SpHelper->mySharedPreferences:SharedPreferences
00000004  invoke-interface        SharedPreferences->edit()SharedPreferences$Editor, v1
0000000A  move-result-object      v0
    .local v0, editor:Landroid/content/SharedPreferences$Editor;
0000000C  const-string            v1, "switchcityflag"
00000010  invoke-interface        SharedPreferences$Editor->putBoolean(String, Z)SharedPreferences$Editor, v0, v1, p0
00000016  invoke-interface        SharedPreferences$Editor->commit()Z, v0
0000001C  move-result             v1
0000001E  return                  v1
.end method

public static setIsSwitchedLocationFlag(Z)Z
    .registers 3
    .param p0, "value"
    .prologue
00000000  sget-object             v1, SpHelper->mySharedPreferences:SharedPreferences
00000004  invoke-interface        SharedPreferences->edit()SharedPreferences$Editor, v1
0000000A  move-result-object      v0
    .local v0, editor:Landroid/content/SharedPreferences$Editor;
0000000C  const-string            v1, "switch_location_city_flag"
00000010  invoke-interface        SharedPreferences$Editor->putBoolean(String, Z)SharedPreferences$Editor, v0, v1, p0
00000016  invoke-interface        SharedPreferences$Editor->commit()Z, v0
0000001C  move-result             v1
0000001E  return                  v1
.end method

public static setJqueryJsTag(J)Z
    .registers 4
    .param p0, "changeTag"
    .prologue
00000000  const-string            v0, "jqueryJsTag"
00000004  invoke-static           SpHelper->commitLong(String, J)Z, v0, p0, p1
0000000A  move-result             v0
0000000C  return                  v0
.end method

public static setJquerynewJsTag(J)Z
    .registers 4
    .param p0, "changeTag"
    .prologue
00000000  const-string            v0, "jquerynewJsTag"
00000004  invoke-static           SpHelper->commitLong(String, J)Z, v0, p0, p1
0000000A  move-result             v0
0000000C  return                  v0
.end method

public static setLoadvideoJsTag(J)Z
    .registers 4
    .param p0, "changeTag"
    .prologue
00000000  const-string            v0, "loadvideoJsTag"
00000004  invoke-static           SpHelper->commitLong(String, J)Z, v0, p0, p1
0000000A  move-result             v0
0000000C  return                  v0
.end method

public static setLocalDeviceID(String)Z
    .registers 3
    .param p0, "deviceID"
    .prologue
00000000  sget-object             v1, SpHelper->mySharedPreferences:SharedPreferences
00000004  invoke-interface        SharedPreferences->edit()SharedPreferences$Editor, v1
0000000A  move-result-object      v0
    .local v0, editor:Landroid/content/SharedPreferences$Editor;
0000000C  const-string            v1, "local_device_id"
00000010  invoke-interface        SharedPreferences$Editor->putString(String, String)SharedPreferences$Editor, v0, v1, p0
00000016  invoke-interface        SharedPreferences$Editor->commit()Z, v0
0000001C  move-result             v1
0000001E  return                  v1
.end method

public static setMyProvinceId(String)Z
    .registers 2
    .param p0, "myProvinceId"
    .prologue
00000000  const-string            v0, "my_province_id"
00000004  invoke-static           SpHelper->commitString(String, String)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

public static setNewBulletinsLasttime(String)V
    .registers 4
    .param p0, "lasttime"
    .prologue
00000000  invoke-static           SpHelper->getNewBulletinsLasttime()String
00000006  move-result-object      v2
00000008  invoke-static           Integer->parseInt(String)I, v2
0000000E  move-result             v1
    .local v1, beforeLasttime:I
00000010  invoke-static           Integer->parseInt(String)I, p0
00000016  move-result             v0
    .local v0, afterLasttime:I
00000018  if-ge                   v1, v0, :26
:1C
0000001C  const-string            v2, "new_bulletins_lasttime"
00000020  invoke-static           SpHelper->commitString(String, String)Z, v2, p0
:26
00000026  return-void
:28
00000028  move-exception          v2
0000002A  goto                    :26
    .catch NumberFormatException {:0 .. :26} :28
.end method

public static setPersuaderCssTag(J)Z
    .registers 4
    .param p0, "changeTag"
    .prologue
00000000  const-string            v0, "persuaderCssTag"
00000004  invoke-static           SpHelper->commitLong(String, J)Z, v0, p0, p1
0000000A  move-result             v0
0000000C  return                  v0
.end method

public static setPhoneAuth(Z)Z
    .registers 2
    .param p0, "isPhoneAuth"
    .prologue
00000000  const-string            v0, "phone_auth"
00000004  invoke-static           SpHelper->commitBoolean(String, Z)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

public static setPriceCssTag(J)Z
    .registers 4
    .param p0, "changeTag"
    .prologue
00000000  const-string            v0, "priceCssTag"
00000004  invoke-static           SpHelper->commitLong(String, J)Z, v0, p0, p1
0000000A  move-result             v0
0000000C  return                  v0
.end method

public static setSearchHistory(String)Z
    .registers 3
    .param p0, "value"
    .prologue
00000000  sget-object             v1, SpHelper->mSearchHistory:SharedPreferences
00000004  invoke-interface        SharedPreferences->edit()SharedPreferences$Editor, v1
0000000A  move-result-object      v0
    .local v0, editor:Landroid/content/SharedPreferences$Editor;
0000000C  const-string            v1, "search_log_key"
00000010  invoke-interface        SharedPreferences$Editor->putString(String, String)SharedPreferences$Editor, v0, v1, p0
00000016  invoke-interface        SharedPreferences$Editor->commit()Z, v0
0000001C  move-result             v1
0000001E  return                  v1
.end method

public static setSeriesArticlePosition(I)Z
    .registers 3
    .param p0, "seriesArticlePosition"
    .prologue
00000000  sget-object             v1, SpHelper->mySharedPreferences:SharedPreferences
00000004  invoke-interface        SharedPreferences->edit()SharedPreferences$Editor, v1
0000000A  move-result-object      v0
    .local v0, editor:Landroid/content/SharedPreferences$Editor;
0000000C  const-string            v1, "seriesarticleposition"
00000010  invoke-interface        SharedPreferences$Editor->putInt(String, I)SharedPreferences$Editor, v0, v1, p0
00000016  invoke-interface        SharedPreferences$Editor->commit()Z, v0
0000001C  move-result             v1
0000001E  return                  v1
.end method

public static setShowGuideForFM(I)V
    .registers 2
    .param p0, "versionCode"
    .prologue
00000000  const-string            v0, "guide_page_for_fm"
00000004  invoke-static           SpHelper->commitInt(String, I)Z, v0, p0
0000000A  return-void
.end method

public static setShowGuideForMain2(I)V
    .registers 2
    .param p0, "versionCode"
    .prologue
00000000  const-string            v0, "guide_page_for_main_2"
00000004  invoke-static           SpHelper->commitInt(String, I)Z, v0, p0
0000000A  return-void
.end method

public static setShowGuideForSale(I)V
    .registers 2
    .param p0, "versionCode"
    .prologue
00000000  const-string            v0, "guide_page_for_sale"
00000004  invoke-static           SpHelper->commitInt(String, I)Z, v0, p0
0000000A  return-void
.end method

public static setShowGuideForSeriesDealer(I)V
    .registers 2
    .param p0, "versionCode"
    .prologue
00000000  const-string            v0, "guide_page_for_series_dealer"
00000004  invoke-static           SpHelper->commitInt(String, I)Z, v0, p0
0000000A  return-void
.end method

public static setSpMode(I)Z
    .registers 2
    .param p0, "mode"
    .prologue
00000000  const-string            v0, "spmode"
00000004  invoke-static           SpHelper->commitInt(String, I)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

public static setThemePackageName(String)V
    .registers 3
    .param p0, "packageName"
    .prologue
00000000  sget-object             v0, SpHelper->mSkinConfig:SharedPreferences
00000004  invoke-interface        SharedPreferences->edit()SharedPreferences$Editor, v0
0000000A  move-result-object      v0
0000000C  const-string            v1, "themePackage"
00000010  invoke-interface        SharedPreferences$Editor->putString(String, String)SharedPreferences$Editor, v0, v1, p0
00000016  move-result-object      v0
00000018  invoke-interface        SharedPreferences$Editor->commit()Z, v0
0000001E  return-void
.end method

public static setVideoCssTag(J)Z
    .registers 4
    .param p0, "changeTag"
    .prologue
00000000  const-string            v0, "videoCssTag"
00000004  invoke-static           SpHelper->commitLong(String, J)Z, v0, p0, p1
0000000A  move-result             v0
0000000C  return                  v0
.end method

public static setVideoDisabledTips(String)Z
    .registers 2
    .param p0, "disabledTips"
    .prologue
00000000  const-string            v0, "video_disabled_tips"
00000004  invoke-static           SpHelper->commitString(String, String)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

public static setVideoEnabled(I)Z
    .registers 2
    .param p0, "enabledFlag"
    .prologue
00000000  const-string            v0, "video_enabled"
00000004  invoke-static           SpHelper->commitInt(String, I)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

public static setVideoHeight(I)Z
    .registers 2
    .param p0, "height"
    .prologue
00000000  const-string            v0, "video_height"
00000004  invoke-static           SpHelper->commitInt(String, I)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

public static setVideoMaxTime(I)Z
    .registers 2
    .param p0, "time"
    .prologue
00000000  const-string            v0, "video_max_time"
00000004  invoke-static           SpHelper->commitInt(String, I)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

public static setVideoRate(I)Z
    .registers 2
    .param p0, "rate"
    .prologue
00000000  const-string            v0, "video_rate"
00000004  invoke-static           SpHelper->commitInt(String, I)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

public static setVideoWidth(I)Z
    .registers 2
    .param p0, "width"
    .prologue
00000000  const-string            v0, "video_width"
00000004  invoke-static           SpHelper->commitInt(String, I)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

public static sethasNewBulletins(Z)Z
    .registers 2
    .param p0, "hasNewBulletins"
    .prologue
00000000  const-string            v0, "has_new_bulletins"
00000004  invoke-static           SpHelper->commitBoolean(String, Z)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
