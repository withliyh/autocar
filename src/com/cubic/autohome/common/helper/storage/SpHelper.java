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


.method public static getLocationProvinceName()String
    .registers 2
    .prologue
00000000  const-string            v0, "location_province_name"
00000004  const-string            v1, ""
00000008  invoke-static           SpHelper->getString(String, String)String, v0, v1
0000000E  move-result-object      v0
00000010  return-object           v0
.end method

.method public static getLong(String, J)J
    .registers 6
    .param p0, "key"
    .param p1, "defaultValue"
    .prologue
00000000  sget-object             v0, SpHelper->mySharedPreferences:SharedPreferences
00000004  invoke-interface        SharedPreferences->getLong(String, J)J, v0, p0, p1, p2
0000000A  move-result-wide        v0
0000000C  return-wide             v0
.end method

.method public static getMyCityId()I
    .registers 2
    .prologue
00000000  const-string            v0, "my_city_id"
00000004  const                   v1, 0x1AE14
0000000A  invoke-static           SpHelper->getInt(String, I)I, v0, v1
00000010  move-result             v0
00000012  return                  v0
.end method

.method public static getMyCityName()String
    .registers 2
    .prologue
00000000  const-string            v0, "my_city_name"
00000004  const-string            v1, "北京"
00000008  invoke-static           SpHelper->getString(String, String)String, v0, v1
0000000E  move-result-object      v0
00000010  return-object           v0
.end method

.method public static getMyProvinceId()String
    .registers 2
    .prologue
00000000  const-string            v0, "my_province_id"
00000004  const-string            v1, ""
00000008  invoke-static           SpHelper->getString(String, String)String, v0, v1
0000000E  move-result-object      v0
00000010  return-object           v0
.end method

.method public static getMyRealCityId()I
    .registers 2
    .prologue
00000000  const-string            v0, "my_real_city_id"
00000004  const                   v1, 0x1AE14
0000000A  invoke-static           SpHelper->getInt(String, I)I, v0, v1
00000010  move-result             v0
00000012  return                  v0
.end method

.method public static getNewBulletinsLasttime()String
    .registers 2
    .prologue
00000000  const-string            v0, "new_bulletins_lasttime"
00000004  const-string            v1, "0"
00000008  invoke-static           SpHelper->getString(String, String)String, v0, v1
0000000E  move-result-object      v0
00000010  return-object           v0
.end method

.method public static getOldCompareCars()ArrayList
    .registers 3
    .annotation system Signature
        value = {
            "()",
            "Ljava/util/ArrayList",
            "<",
            "Lcom/cubic/autohome/car/model/SpecEntity;",
            ">;"
        }
    .end annotation
    .prologue
00000000  const-string            v1, "compare_cars"
00000004  const/4                 v2, 0x0
00000006  invoke-static           SpHelper->getString(String, String)String, v1, v2
0000000C  move-result-object      v0
    .local v0, config:Ljava/lang/String;
0000000E  new-instance            v1, ArrayListAndStringUtil
00000012  invoke-direct           ArrayListAndStringUtil-><init>()V, v1
00000018  invoke-virtual          ArrayListAndStringUtil->StringToArrayList(String)ArrayList, v1, v0
0000001E  move-result-object      v1
00000020  return-object           v1
.end method

.method public static getPersuaderCssTag()J
    .registers 4
    .prologue
00000000  const-string            v0, "persuaderCssTag"
00000004  const-wide/16           v2, 0x0
00000008  invoke-static           SpHelper->getLong(String, J)J, v0, v2, v3
0000000E  move-result-wide        v0
00000010  return-wide             v0
.end method

.method public static getPhoneAuth()Z
    .registers 2
    .prologue
00000000  const-string            v0, "phone_auth"
00000004  const/4                 v1, 0x0
00000006  invoke-static           SpHelper->getBoolean(String, Z)Z, v0, v1
0000000C  move-result             v0
0000000E  return                  v0
.end method

.method public static getPhoneDensity()F
    .registers 2
    .prologue
00000000  const-string            v0, "phone_density"
00000004  const/high16            v1, 0x3F800000
00000008  invoke-static           SpHelper->getFloat(String, F)F, v0, v1
0000000E  move-result             v0
00000010  return                  v0
.end method

.method public static getPriceCssTag()J
    .registers 4
    .prologue
00000000  const-string            v0, "priceCssTag"
00000004  const-wide/16           v2, 0x0
00000008  invoke-static           SpHelper->getLong(String, J)J, v0, v2, v3
0000000E  move-result-wide        v0
00000010  return-wide             v0
.end method

.method public static getRightCompareCarID()String
    .registers 2
    .prologue
00000000  const-string            v0, "right_compare_carID"
00000004  const-string            v1, "0"
00000008  invoke-static           SpHelper->getString(String, String)String, v0, v1
0000000E  move-result-object      v0
00000010  return-object           v0
.end method

.method public static getSaveOverViewClickNew(I)I
    .registers 3
    .param p0, "typeid"
    .prologue
00000000  new-instance            v0, StringBuilder
00000004  invoke-static           String->valueOf(I)String, p0
0000000A  move-result-object      v1
0000000C  invoke-direct           StringBuilder-><init>(String)V, v0, v1
00000012  invoke-virtual          StringBuilder->toString()String, v0
00000018  move-result-object      v0
0000001A  const/4                 v1, 0xFFFFFFFFFFFFFFFF
0000001C  invoke-static           SpHelper->getInt(String, I)I, v0, v1
00000022  move-result             v0
00000024  return                  v0
.end method

.method public static getSaveSaleClickNew(I)I
    .registers 3
    .param p0, "seriesId"
    .prologue
00000000  new-instance            v0, StringBuilder
00000004  invoke-static           String->valueOf(I)String, p0
0000000A  move-result-object      v1
0000000C  invoke-direct           StringBuilder-><init>(String)V, v0, v1
00000012  invoke-virtual          StringBuilder->toString()String, v0
00000018  move-result-object      v0
0000001A  const/4                 v1, 0xFFFFFFFFFFFFFFFF
0000001C  invoke-static           SpHelper->getInt(String, I)I, v0, v1
00000022  move-result             v0
00000024  return                  v0
.end method

.method public static getScreenHeight()I
    .registers 1
    .prologue
00000000  const-string            v0, "screen_height"
00000004  invoke-static           SpHelper->getInt(String)I, v0
0000000A  move-result             v0
0000000C  return                  v0
.end method

.method public static getScreenWidth()I
    .registers 1
    .prologue
00000000  const-string            v0, "screen_width"
00000004  invoke-static           SpHelper->getInt(String)I, v0
0000000A  move-result             v0
0000000C  return                  v0
.end method

.method public static getScreenWidthForPage()I
    .registers 1
    .prologue
00000000  const-string            v0, "screen_width_page"
00000004  invoke-static           SpHelper->getInt(String)I, v0
0000000A  move-result             v0
0000000C  return                  v0
.end method

.method public static getSearchHistory()String
    .registers 3
    .prologue
00000000  sget-object             v0, SpHelper->mSearchHistory:SharedPreferences
00000004  const-string            v1, "search_log_key"
00000008  const-string            v2, ""
0000000C  invoke-interface        SharedPreferences->getString(String, String)String, v0, v1, v2
00000012  move-result-object      v0
00000014  return-object           v0
.end method

.method public static getSeriesArticlePosition()I
    .registers 3
    .prologue
00000000  sget-object             v0, SpHelper->mySharedPreferences:SharedPreferences
00000004  const-string            v1, "seriesarticleposition"
00000008  const/4                 v2, 0x0
0000000A  invoke-interface        SharedPreferences->getInt(String, I)I, v0, v1, v2
00000010  move-result             v0
00000012  return                  v0
.end method

.method public static getSlipSwitch()Z
    .registers 2
    .prologue
00000000  const-string            v0, "slip_switch"
00000004  const/4                 v1, 0x0
00000006  invoke-static           SpHelper->getBoolean(String, Z)Z, v0, v1
0000000C  move-result             v0
0000000E  return                  v0
.end method

.method public static getSpMode()I
    .registers 2
    .prologue
00000000  const-string            v0, "spmode"
00000004  const/4                 v1, 0x0
00000006  invoke-static           SpHelper->getInt(String, I)I, v0, v1
0000000C  move-result             v0
0000000E  return                  v0
.end method

.method public static getString(String)String
    .registers 3
    .param p0, "key"
    .prologue
00000000  sget-object             v0, SpHelper->mySharedPreferences:SharedPreferences
00000004  const-string            v1, ""
00000008  invoke-interface        SharedPreferences->getString(String, String)String, v0, p0, v1
0000000E  move-result-object      v0
00000010  return-object           v0
.end method

.method public static getString(String, String)String
    .registers 3
    .param p0, "key"
    .param p1, "defaultValue"
    .prologue
00000000  sget-object             v0, SpHelper->mySharedPreferences:SharedPreferences
00000004  invoke-interface        SharedPreferences->getString(String, String)String, v0, p0, p1
0000000A  move-result-object      v0
0000000C  return-object           v0
.end method

.method public static getThemePackageName()String
    .registers 3
    .prologue
00000000  sget-object             v0, SpHelper->mSkinConfig:SharedPreferences
00000004  const-string            v1, "themePackage"
00000008  const-string            v2, ""
0000000C  invoke-interface        SharedPreferences->getString(String, String)String, v0, v1, v2
00000012  move-result-object      v0
00000014  return-object           v0
.end method

.method public static getVideoCssTag()J
    .registers 4
    .prologue
00000000  const-string            v0, "videoCssTag"
00000004  const-wide/16           v2, 0x0
00000008  invoke-static           SpHelper->getLong(String, J)J, v0, v2, v3
0000000E  move-result-wide        v0
00000010  return-wide             v0
.end method

.method public static getVideoDisabledTips()String
    .registers 2
    .prologue
00000000  const-string            v0, "video_disabled_tips"
00000004  const-string            v1, ""
00000008  invoke-static           SpHelper->getString(String, String)String, v0, v1
0000000E  move-result-object      v0
00000010  return-object           v0
.end method

.method public static getVideoEnabled()I
    .registers 2
    .prologue
00000000  const-string            v0, "video_enabled"
00000004  const/4                 v1, 0x1
00000006  invoke-static           SpHelper->getInt(String, I)I, v0, v1
0000000C  move-result             v0
0000000E  return                  v0
.end method

.method public static getVideoHeight()I
    .registers 2
    .prologue
00000000  const-string            v0, "video_height"
00000004  const/16                v1, 0x168
00000008  invoke-static           SpHelper->getInt(String, I)I, v0, v1
0000000E  move-result             v0
00000010  return                  v0
.end method

.method public static getVideoMaxTime()I
    .registers 2
    .prologue
00000000  const-string            v0, "video_max_time"
00000004  const/16                v1, 0x1E
00000008  invoke-static           SpHelper->getInt(String, I)I, v0, v1
0000000E  move-result             v0
00000010  return                  v0
.end method

.method public static getVideoRate()I
    .registers 2
    .prologue
00000000  const-string            v0, "video_rate"
00000004  const/4                 v1, 0x2
00000006  invoke-static           SpHelper->getInt(String, I)I, v0, v1
0000000C  move-result             v0
0000000E  return                  v0
.end method

.method public static getVideoWidth()I
    .registers 2
    .prologue
00000000  const-string            v0, "video_width"
00000004  const/16                v1, 0x1E0
00000008  invoke-static           SpHelper->getInt(String, I)I, v0, v1
0000000E  move-result             v0
00000010  return                  v0
.end method

.method public static hasNewBulletins()Z
    .registers 2
    .prologue
00000000  const-string            v0, "has_new_bulletins"
00000004  const/4                 v1, 0x1
00000006  invoke-static           SpHelper->getBoolean(String, Z)Z, v0, v1
0000000C  move-result             v0
0000000E  return                  v0
.end method

.method public static increaseDraftCount()V
    .registers 3
    .prologue
00000000  const-string            v1, "draft_count"
00000004  const/4                 v2, 0x0
00000006  invoke-static           SpHelper->getInt(String, I)I, v1, v2
0000000C  move-result             v0
    .local v0, total:I
0000000E  if-lez                  v0, :22
:12
00000012  const-string            v1, "draft_count"
00000016  add-int/lit8            v2, v0, 0x1
0000001A  invoke-static           SpHelper->commitInt(String, I)Z, v1, v2
:20
00000020  return-void
:22
00000022  const-string            v1, "draft_count"
00000026  const/4                 v2, 0x1
00000028  invoke-static           SpHelper->commitInt(String, I)Z, v1, v2
0000002E  goto                    :20
.end method

.method public static isShowGuideForFM()Z
    .registers 2
00000000  const/4                 v0, 0x0
    .prologue
00000002  const-string            v1, "guide_page_for_fm"
00000006  invoke-static           SpHelper->getInt(String, I)I, v1, v0
0000000C  move-result             v1
0000000E  if-nez                  v1, :14
:12
00000012  const/4                 v0, 0x1
:14
00000014  return                  v0
.end method

.method public static isShowGuideForMain2()Z
    .registers 2
00000000  const/4                 v0, 0x0
    .prologue
00000002  const-string            v1, "guide_page_for_main_2"
00000006  invoke-static           SpHelper->getInt(String, I)I, v1, v0
0000000C  move-result             v1
0000000E  if-nez                  v1, :14
:12
00000012  const/4                 v0, 0x1
:14
00000014  return                  v0
.end method

.method public static isShowGuideForSale()Z
    .registers 2
00000000  const/4                 v0, 0x0
    .prologue
00000002  const-string            v1, "guide_page_for_sale"
00000006  invoke-static           SpHelper->getInt(String, I)I, v1, v0
0000000C  move-result             v1
0000000E  if-nez                  v1, :14
:12
00000012  const/4                 v0, 0x1
:14
00000014  return                  v0
.end method

.method public static isShowGuideForSeriesDealer()Z
    .registers 2
00000000  const/4                 v0, 0x0
    .prologue
00000002  const-string            v1, "guide_page_for_series_dealer"
00000006  invoke-static           SpHelper->getInt(String, I)I, v1, v0
0000000C  move-result             v1
0000000E  if-nez                  v1, :14
:12
00000012  const/4                 v0, 0x1
:14
00000014  return                  v0
.end method

.method public static isShowMoreRedDot()Z
    .registers 2
    .prologue
00000000  const-string            v0, "more_red_dot"
00000004  const/4                 v1, 0x0
00000006  invoke-static           SpHelper->getBoolean(String, Z)Z, v0, v1
0000000C  move-result             v0
0000000E  return                  v0
.end method

.method public static reduceDraftCount()V
    .registers 3
00000000  const/4                 v2, 0x0
    .prologue
00000002  const-string            v1, "draft_count"
00000006  invoke-static           SpHelper->getInt(String, I)I, v1, v2
0000000C  move-result             v0
    .local v0, total:I
0000000E  if-lez                  v0, :22
:12
00000012  const-string            v1, "draft_count"
00000016  add-int/lit8            v2, v0, 0xFFFFFFFFFFFFFFFF
0000001A  invoke-static           SpHelper->commitInt(String, I)Z, v1, v2
:20
00000020  return-void
:22
00000022  const-string            v1, "draft_count"
00000026  invoke-static           SpHelper->commitInt(String, I)Z, v1, v2
0000002C  goto                    :20
.end method

.method public static registerPrefChangeListener(SharedPreferences$OnSharedPreferenceChangeListener)V
    .registers 2
    .param p0, "listener"
    .prologue
00000000  sget-object             v0, SpHelper->mSkinConfig:SharedPreferences
00000004  invoke-interface        SharedPreferences->registerOnSharedPreferenceChangeListener(SharedPreferences$OnSharedPreferenceChangeListener)V, v0, p0
0000000A  return-void
.end method

.method public static remove(String)Z
    .registers 2
    .param p0, "key"
    .prologue
00000000  sget-object             v0, SpHelper->mySharedPreferences:SharedPreferences
00000004  invoke-interface        SharedPreferences->edit()SharedPreferences$Editor, v0
0000000A  move-result-object      v0
0000000C  invoke-interface        SharedPreferences$Editor->remove(String)SharedPreferences$Editor, v0, p0
00000012  move-result-object      v0
00000014  invoke-interface        SharedPreferences$Editor->commit()Z, v0
0000001A  move-result             v0
0000001C  return                  v0
.end method

.method public static saveAppRecommand(ArrayList)Z
    .registers 3
    .annotation system Signature
        value = {
            "(",
            "Ljava/util/ArrayList",
            "<",
            "Lcom/cubic/autohome/business/user/owner/bean/AppRecommandEntity;",
            ">;)Z"
        }
    .end annotation
    .param p0, ""
    .prologue
    .local v2, appRecommand:Ljava/util/ArrayList;
00000000  new-instance            v1, ArrayListAndStringUtil
00000004  invoke-direct           ArrayListAndStringUtil-><init>()V, v1
0000000A  invoke-virtual          ArrayListAndStringUtil->ArrayListToString(ArrayList)String, v1, p0
00000010  move-result-object      v0
    .local v0, recommand:Ljava/lang/String;
00000012  const-string            v1, "apprecommand"
00000016  invoke-static           SpHelper->commitString(String, String)Z, v1, v0
0000001C  move-result             v1
0000001E  return                  v1
.end method

.method public static saveAskPriceUserNameAndPhone(ArrayList)Z
    .registers 3
    .annotation system Signature
        value = {
            "(",
            "Ljava/util/ArrayList",
            "<",
            "Ljava/lang/String;",
            ">;)Z"
        }
    .end annotation
    .param p0, ""
    .prologue
    .local v2, userInfo:Ljava/util/ArrayList;
00000000  new-instance            v1, ArrayListAndStringUtil
00000004  invoke-direct           ArrayListAndStringUtil-><init>()V, v1
0000000A  invoke-virtual          ArrayListAndStringUtil->ArrayListToString(ArrayList)String, v1, p0
00000010  move-result-object      v0
    .local v0, config:Ljava/lang/String;
00000012  const-string            v1, "user_info"
00000016  invoke-static           SpHelper->commitString(String, String)Z, v1, v0
0000001C  move-result             v1
0000001E  return                  v1
.end method

.method public static saveClubReplyRefresh(Z)Z
    .registers 2
    .param p0, "isRefresh"
    .prologue
00000000  const-string            v0, "club_reply_refresh"
00000004  invoke-static           SpHelper->commitBoolean(String, Z)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

.method public static saveCommentReplyRefresh(Z)Z
    .registers 2
    .param p0, "isRefresh"
    .prologue
00000000  const-string            v0, "comment_reply_refresh"
00000004  invoke-static           SpHelper->commitBoolean(String, Z)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

.method public static saveCompareCars(ArrayList)Z
    .registers 3
    .annotation system Signature
        value = {
            "(",
            "Ljava/util/ArrayList",
            "<",
            "Lcom/cubic/autohome/business/car/bean/SpecEntity;",
            ">;)Z"
        }
    .end annotation
    .param p0, ""
    .prologue
    .local v2, compareCars:Ljava/util/ArrayList;
00000000  new-instance            v1, ArrayListAndStringUtil
00000004  invoke-direct           ArrayListAndStringUtil-><init>()V, v1
0000000A  invoke-virtual          ArrayListAndStringUtil->ArrayListToString(ArrayList)String, v1, p0
00000010  move-result-object      v0
    .local v0, config:Ljava/lang/String;
00000012  const-string            v1, "compare_cars"
00000016  invoke-static           SpHelper->commitString(String, String)Z, v1, v0
0000001C  move-result             v1
0000001E  return                  v1
.end method

.method public static saveLeftCompareCarId(String)Z
    .registers 2
    .param p0, "leftCarId"
    .prologue
00000000  const-string            v0, "left_compare_carID"
00000004  invoke-static           SpHelper->commitString(String, String)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

.method public static saveLocalLa(D)Z
    .registers 4
    .param p0, "la"
    .prologue
00000000  const-string            v0, "local_la"
00000004  invoke-static           SpHelper->commitDouble(String, D)Z, v0, p0, p1
0000000A  move-result             v0
0000000C  return                  v0
.end method

.method public static saveLocalLo(D)Z
    .registers 4
    .param p0, "lo"
    .prologue
00000000  const-string            v0, "local_lo"
00000004  invoke-static           SpHelper->commitDouble(String, D)Z, v0, p0, p1
0000000A  move-result             v0
0000000C  return                  v0
.end method

.method public static saveLocationCityId(I)Z
    .registers 2
    .param p0, "locationCityId"
    .prologue
00000000  const-string            v0, "location_city_id"
00000004  invoke-static           SpHelper->commitInt(String, I)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

.method public static saveLocationCityName(String)Z
    .registers 2
    .param p0, "locationCityName"
    .prologue
00000000  const-string            v0, "location_city_name"
00000004  invoke-static           SpHelper->commitString(String, String)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

.method public static saveLocationProvinceId(I)Z
    .registers 2
    .param p0, "locationProvinceId"
    .prologue
00000000  const-string            v0, "location_province_id"
00000004  invoke-static           SpHelper->commitInt(String, I)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

.method public static saveLocationProvinceName(String)Z
    .registers 2
    .param p0, "locationProvinceName"
    .prologue
00000000  const-string            v0, "location_province_name"
00000004  invoke-static           SpHelper->commitString(String, String)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

.method public static saveMyCityId(I)Z
    .registers 2
    .param p0, "myCityId"
    .prologue
00000000  const-string            v0, "my_city_id"
00000004  invoke-static           SpHelper->commitInt(String, I)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

.method public static saveMyCityName(String)Z
    .registers 2
    .param p0, "myCityName"
    .prologue
00000000  const-string            v0, "my_city_name"
00000004  invoke-static           SpHelper->commitString(String, String)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

.method public static saveMyRealCityId(I)Z
    .registers 2
    .param p0, "myCityId"
    .prologue
00000000  const-string            v0, "my_real_city_id"
00000004  invoke-static           SpHelper->commitInt(String, I)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

.method public static saveMyTopicRefresh(Z)Z
    .registers 2
    .param p0, "isRefresh"
    .prologue
00000000  const-string            v0, "mytopic_refresh"
00000004  invoke-static           SpHelper->commitBoolean(String, Z)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

.method public static saveOverViewClickNew(I)Z
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

.method public static savePhoneDensity(F)Z
    .registers 2
    .param p0, "phoneDensity"
    .prologue
00000000  const-string            v0, "phone_density"
00000004  invoke-static           SpHelper->commitFloat(String, F)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

.method public static saveRightCompareCarId(String)Z
    .registers 2
    .param p0, "rightCarId"
    .prologue
00000000  const-string            v0, "right_compare_carID"
00000004  invoke-static           SpHelper->commitString(String, String)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

.method public static saveScreenHeight(I)Z
    .registers 2
    .param p0, "screenHeight"
    .prologue
00000000  const-string            v0, "screen_height"
00000004  invoke-static           SpHelper->commitInt(String, I)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

.method public static saveScreenWidth(I)Z
    .registers 2
    .param p0, "screenWidth"
    .prologue
00000000  const-string            v0, "screen_width"
00000004  invoke-static           SpHelper->commitInt(String, I)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

.method public static saveScreenWidthForPage(I)Z
    .registers 2
    .param p0, "screenWidthForPage"
    .prologue
00000000  const-string            v0, "screen_width_page"
00000004  invoke-static           SpHelper->commitInt(String, I)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

.method public static setAppsUploadState(Z)Z
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

.method public static setArticleCssTag(J)Z
    .registers 4
    .param p0, "changeTag"
    .prologue
00000000  const-string            v0, "articleCssTag"
00000004  invoke-static           SpHelper->commitLong(String, J)Z, v0, p0, p1
0000000A  move-result             v0
0000000C  return                  v0
.end method

.method public static setAutohomeVersionCode(I)Z
    .registers 2
    .param p0, "versionCode"
    .prologue
00000000  const-string            v0, "versionCode"
00000004  invoke-static           SpHelper->commitInt(String, I)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

.method public static setChannel(String)Z
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

.method public static setClubRecappCloset(J)Z
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

.method public static setClubUserShow(String)Z
    .registers 2
    .param p0, "clubUserShow"
    .prologue
00000000  const-string            v0, "clubUserShow"
00000004  invoke-static           SpHelper->commitString(String, String)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

.method public static setCommonJsTag(J)Z
    .registers 4
    .param p0, "changeTag"
    .prologue
00000000  const-string            v0, "commonJsTag"
00000004  invoke-static           SpHelper->commitLong(String, J)Z, v0, p0, p1
0000000A  move-result             v0
0000000C  return                  v0
.end method

.method public static setDevDebugMode(Z, Context)V
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

.method private static setFontSize(WebView, I)V
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

.method public static setFontSize(I)Z
    .registers 2
    .param p0, "fontSize"
    .prologue
00000000  const-string            v0, "font_size"
00000004  invoke-static           SpHelper->commitInt(String, I)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

.method public static setGuidePage(I)Z
    .registers 2
    .param p0, "mode"
    .prologue
00000000  const-string            v0, "guide_page"
00000004  invoke-static           SpHelper->commitInt(String, I)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

.method public static setInstalledSkinVersion(I)Z
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

.method public static setIsSwitchedFlag(Z)Z
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

.method public static setIsSwitchedLocationFlag(Z)Z
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

.method public static setJqueryJsTag(J)Z
    .registers 4
    .param p0, "changeTag"
    .prologue
00000000  const-string            v0, "jqueryJsTag"
00000004  invoke-static           SpHelper->commitLong(String, J)Z, v0, p0, p1
0000000A  move-result             v0
0000000C  return                  v0
.end method

.method public static setJquerynewJsTag(J)Z
    .registers 4
    .param p0, "changeTag"
    .prologue
00000000  const-string            v0, "jquerynewJsTag"
00000004  invoke-static           SpHelper->commitLong(String, J)Z, v0, p0, p1
0000000A  move-result             v0
0000000C  return                  v0
.end method

.method public static setLoadvideoJsTag(J)Z
    .registers 4
    .param p0, "changeTag"
    .prologue
00000000  const-string            v0, "loadvideoJsTag"
00000004  invoke-static           SpHelper->commitLong(String, J)Z, v0, p0, p1
0000000A  move-result             v0
0000000C  return                  v0
.end method

.method public static setLocalDeviceID(String)Z
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

.method public static setMyProvinceId(String)Z
    .registers 2
    .param p0, "myProvinceId"
    .prologue
00000000  const-string            v0, "my_province_id"
00000004  invoke-static           SpHelper->commitString(String, String)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

.method public static setNewBulletinsLasttime(String)V
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

.method public static setPersuaderCssTag(J)Z
    .registers 4
    .param p0, "changeTag"
    .prologue
00000000  const-string            v0, "persuaderCssTag"
00000004  invoke-static           SpHelper->commitLong(String, J)Z, v0, p0, p1
0000000A  move-result             v0
0000000C  return                  v0
.end method

.method public static setPhoneAuth(Z)Z
    .registers 2
    .param p0, "isPhoneAuth"
    .prologue
00000000  const-string            v0, "phone_auth"
00000004  invoke-static           SpHelper->commitBoolean(String, Z)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

.method public static setPriceCssTag(J)Z
    .registers 4
    .param p0, "changeTag"
    .prologue
00000000  const-string            v0, "priceCssTag"
00000004  invoke-static           SpHelper->commitLong(String, J)Z, v0, p0, p1
0000000A  move-result             v0
0000000C  return                  v0
.end method

.method public static setSearchHistory(String)Z
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

.method public static setSeriesArticlePosition(I)Z
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

.method public static setShowGuideForFM(I)V
    .registers 2
    .param p0, "versionCode"
    .prologue
00000000  const-string            v0, "guide_page_for_fm"
00000004  invoke-static           SpHelper->commitInt(String, I)Z, v0, p0
0000000A  return-void
.end method

.method public static setShowGuideForMain2(I)V
    .registers 2
    .param p0, "versionCode"
    .prologue
00000000  const-string            v0, "guide_page_for_main_2"
00000004  invoke-static           SpHelper->commitInt(String, I)Z, v0, p0
0000000A  return-void
.end method

.method public static setShowGuideForSale(I)V
    .registers 2
    .param p0, "versionCode"
    .prologue
00000000  const-string            v0, "guide_page_for_sale"
00000004  invoke-static           SpHelper->commitInt(String, I)Z, v0, p0
0000000A  return-void
.end method

.method public static setShowGuideForSeriesDealer(I)V
    .registers 2
    .param p0, "versionCode"
    .prologue
00000000  const-string            v0, "guide_page_for_series_dealer"
00000004  invoke-static           SpHelper->commitInt(String, I)Z, v0, p0
0000000A  return-void
.end method

.method public static setSpMode(I)Z
    .registers 2
    .param p0, "mode"
    .prologue
00000000  const-string            v0, "spmode"
00000004  invoke-static           SpHelper->commitInt(String, I)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

.method public static setThemePackageName(String)V
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

.method public static setVideoCssTag(J)Z
    .registers 4
    .param p0, "changeTag"
    .prologue
00000000  const-string            v0, "videoCssTag"
00000004  invoke-static           SpHelper->commitLong(String, J)Z, v0, p0, p1
0000000A  move-result             v0
0000000C  return                  v0
.end method

.method public static setVideoDisabledTips(String)Z
    .registers 2
    .param p0, "disabledTips"
    .prologue
00000000  const-string            v0, "video_disabled_tips"
00000004  invoke-static           SpHelper->commitString(String, String)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

.method public static setVideoEnabled(I)Z
    .registers 2
    .param p0, "enabledFlag"
    .prologue
00000000  const-string            v0, "video_enabled"
00000004  invoke-static           SpHelper->commitInt(String, I)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

.method public static setVideoHeight(I)Z
    .registers 2
    .param p0, "height"
    .prologue
00000000  const-string            v0, "video_height"
00000004  invoke-static           SpHelper->commitInt(String, I)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

.method public static setVideoMaxTime(I)Z
    .registers 2
    .param p0, "time"
    .prologue
00000000  const-string            v0, "video_max_time"
00000004  invoke-static           SpHelper->commitInt(String, I)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

.method public static setVideoRate(I)Z
    .registers 2
    .param p0, "rate"
    .prologue
00000000  const-string            v0, "video_rate"
00000004  invoke-static           SpHelper->commitInt(String, I)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

.method public static setVideoWidth(I)Z
    .registers 2
    .param p0, "width"
    .prologue
00000000  const-string            v0, "video_width"
00000004  invoke-static           SpHelper->commitInt(String, I)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method

.method public static sethasNewBulletins(Z)Z
    .registers 2
    .param p0, "hasNewBulletins"
    .prologue
00000000  const-string            v0, "has_new_bulletins"
00000004  invoke-static           SpHelper->commitBoolean(String, Z)Z, v0, p0
0000000A  move-result             v0
0000000C  return                  v0
.end method
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
