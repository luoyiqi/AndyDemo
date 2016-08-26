package com.andy.a.demo.context.config;


import com.andy.a.demo.BuildConfig;

@SuppressWarnings("unused")
public class BaseHttpURL {

	private BaseHttpURL() {
	}

	private static final boolean isLive = BuildConfig.BESTKEEP_LIVE;

	private static final boolean isDev = BuildConfig.BESTKEEP_DEV;

	public static final boolean isTest = BuildConfig.BESTKEEP_TEST;

	/** ------------------基础数据接口请求地址-------------------- */
	private static final String BASE_URL1 = "http://www.utouu.com";// 生产环境
	private static final String BASE_URL2 = "http://api.utouu.com";// 生产环境(NEW)
	private static final String BASE_URL3 = "http://www.dev.utouu.com";// 开发环境
	private static final String BASE_URL4 = "http://www.test.utouu.com";// 测试环境
	private static final String BASE_URL5 = "http://api.dev.utouu.com";// 开发环境(NEW)
	private static final String BASE_URL6 = "http://api.test.utouu.com";// 测试环境(NEW)

	public static final String BASE_URL = isLive ? BASE_URL2 : (isDev ? BASE_URL5 : (isTest ? BASE_URL6 : ""));

	/** ----------------------资源请求地址----------------------- */
	private static final String STATIC_BASE_LIVE_URL = "http://cdn.bestkeep.cn";// 生产环境
	private static final String STATIC_BASE_DEV_URL = "http://cdn.bestkeep.cn";// 开发环境
	private static final String STATIC_BASE_TEST_URL = "http://cdn.bestkeep.cn";// 测试环境

	private static final String STATIC_BASE_LIVE_URL1 = "http://cdn1.utouu.com";// 生产环境

	public static final String STATIC_BASE_URL = isLive ? STATIC_BASE_LIVE_URL1 : isDev ? STATIC_BASE_DEV_URL : STATIC_BASE_TEST_URL;

	/** -----------------passport认证接口请求地址---------------- */
	private static final String PASSPORT_BASE_LIVE_URL = "https://passport.utouu.com";// 生产环境
	private static final String PASSPORT_BASE_DEV_URL = "https://passport.dev.utouu.com";// 开发环境
	private static final String PASSPORT_BASE_TEST_URL = "https://passport.test.utouu.com";// 测试环境

	public static final String LOAD_BASEURL = isLive ? PASSPORT_BASE_LIVE_URL : isDev ? PASSPORT_BASE_DEV_URL : PASSPORT_BASE_TEST_URL;

	/** -------------------图形验证码接口请求地址----------------- */
	private static final String MSG_BASE_LIVE_URL = "http://msg.utouu.com";// 生产环境
	private static final String MSG_BASE_DEV_URL = "http://msg.dev.utouu.com";// 开发环境
	private static final String MSG_BASE_TEST_URL = "http://msg.test.utouu.com";// 测试环境

	public static final String MSG_BASEURL = isLive ? MSG_BASE_LIVE_URL : isDev ? MSG_BASE_DEV_URL : MSG_BASE_TEST_URL;

	/** -------------------BestKepp接口请求地址----------------------------- */
	private static final String BESTKEEP_BASE_LIVE_URL = "http://api.bestkeep.cn";
	private static final String BESTKEEP_BASE_DEV_URL = "http://api.dev.bestkeep.cn";
	private static final String BESTKEEP_BASE_TEST_URL = "http://api.test.bestkeep.cn";

	/***----版本升级****/
	public static final String DEV_app_update = "https://api.open.dev.utouu.com";
	public static final String LIVE_app_update = "https://api.open.utouu.com";
	public static final String BESTKEEP_UPDATE =isLive ? LIVE_app_update: isDev ?DEV_app_update:DEV_app_update;

	public static final String BESTKEEP_BASE_URL = isLive ? BESTKEEP_BASE_LIVE_URL : isDev ? BESTKEEP_BASE_DEV_URL : BESTKEEP_BASE_TEST_URL;

	/** ---------------------资金系统接口请求地址------------------------- */
	private static final String CAC_BASE_LIVE_URL = "http://cac.utouu.com";
	private static final String CAC_BASE_DEV_URL = "http://cac.dev.utouu.com";
	private static final String CAC_BASE_TEST_URL = "http://cac.test.utouu.com";

	public static final String CAC_BASE_URL = isLive ? CAC_BASE_LIVE_URL : isDev ? CAC_BASE_DEV_URL : CAC_BASE_TEST_URL;

	/** ---------------------支付宝接口请求地址------------------------- */
	public static final String ALIPAY_BASE_LIVE_URL = "http://api.cac.utouu.com";
	public static final String ALIPAY_BASE_TEST_URL = "http://api.cac.test.utouu.com";
	public static final String ALIPAY_BASE_DEV_URL = "http://api.cac.dev.utouu.com";
	public static final String ALIPAY_CAC_BASE_URL = isLive ? ALIPAY_BASE_LIVE_URL : isDev ? ALIPAY_BASE_DEV_URL : ALIPAY_BASE_TEST_URL;

	/** ----------------------------HTTP请求地址--------------------------- */
	private static final String HTTP_BASE_LIVE_URL = "http://m.bestkeep.cn";
	private static final String HTTP_BASE_DEV_URL = "http://m.dev.bestkeep.cn";
	private static final String HTTP_BASE_TEST_URL = "http://m.test.bestkeep.cn";

	public static final String HTTP_BASE_URL = isLive ? HTTP_BASE_LIVE_URL : isDev ? HTTP_BASE_DEV_URL : HTTP_BASE_TEST_URL;

	/** ---------------------------UTOUU-Http请求地址------------------------------ */
	private static final String HTTP_BASE_UTOUU_LIVE_URL = "http://m.utouu.com";
	private static final String HTTP_BASE_UTOUU_DEV_URL = "http://m.dev.utouu.com";
	private static final String HTTP_BASE_UTOUU_TEST_URL = "http://m.test.utouu.com";

	public static final String HTTP_BASE_UTOUU_URL = isLive ? HTTP_BASE_UTOUU_LIVE_URL : isDev ? HTTP_BASE_UTOUU_DEV_URL : HTTP_BASE_UTOUU_TEST_URL;


	private static final String HTTP_BASE_SRV_IM_LIVE_URL = "http://srv.im.utouu.com";
	private static final String HTTP_BASE_SRV_IM_DEV_URL = "http://srv.im.dev.utouu.com";
	private static final String HTTP_BASE_SRV_IM_TEST_URL = "http://srv.im.test.utouu.com";

	public static final String HTTP_BASE_SRV_IM_URL = isLive ? HTTP_BASE_SRV_IM_LIVE_URL : isDev ? HTTP_BASE_SRV_IM_DEV_URL : HTTP_BASE_SRV_IM_TEST_URL;

}
