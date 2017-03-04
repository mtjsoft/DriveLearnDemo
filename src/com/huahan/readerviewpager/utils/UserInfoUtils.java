package com.huahan.readerviewpager.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

public class UserInfoUtils {
	public static final String STUDY_ID = "study_id";// 
	private static final String CONFIG_NAME = "DriveLearn";
	public static final String LOGIN_NAME = "login_name";// 登录名
	public static final String USER_ID = "user_id";// 用户ID
	public static final String HEAD_IMAGE = "head_image";// 头像
	public static final String NICK_NAME = "nick_name";// 头像
	public static final String USER_TYPE = "user_type";// 用户类别1:用户,2:教练)
	// public static final String DEVICE_TOKEN = "device_token";// 设备号
	public static final String CLIENT_ID = "client_id";// cid
	public static final String QR_CODE_URL = "qr_code_url";// 二维码
	public static final String IS_PUSH = "is_push";// 是否推送
	public static final String LA = "la";
	public static final String LO = "lo";
	public static final String TEST_ACCOUNT = "test_account";// 考试账号
	public static final String TEST_PWD = "test_pwd";// 考试账号//
	public static final String DRIVING_SCHEDULE = "driving_schedule";// 学车进度状态（1未受理
																		// 2、受理、3科目一、4科目二、5科目三、6科目四、7出证））
	public static final String IS_PAY_PASS_WORD = "is_pay_pass_word";// 是否设置提现密码【0：否
																		// 1：是】

	/**
	 * 获取SharedPreferences
	 * 
	 * @param context
	 * @return
	 */
	private static SharedPreferences getSharedPreferences(Context context) {
		return context.getSharedPreferences(CONFIG_NAME, Context.MODE_PRIVATE);
	}

	/**
	 * 获取Editor
	 * 
	 * @param context
	 * @return
	 */
	private static Editor getEditor(Context context) {
		return getSharedPreferences(context).edit();
	}

	/**
	 * 判断用户是否登录
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isLogin(Context context) {
		SharedPreferences preferences = getSharedPreferences(context);
		String userID = preferences.getString(USER_ID, "");
		if (TextUtils.isEmpty(userID)) {
			return false;
		}
		return true;
	}

	/**
	 * 获取用户id
	 * 
	 * @param context
	 * @return
	 */
	public static String getUserID(Context context) {
		String paramInfo = getUserInfo(context, USER_ID);
		if (TextUtils.isEmpty(paramInfo)) {
			return "";
		} else {
			return paramInfo;
		}
	}

	/**
	 * 获取用户的配置信息
	 * 
	 * @param context
	 * @param paramName
	 * @return
	 */
	public static String getUserInfo(Context context, String paramName) {
		SharedPreferences sharedPreferences = getSharedPreferences(context);
		return sharedPreferences.getString(paramName, "");
	}

	/**
	 * 保存用户的配置信息
	 * 
	 * @param context
	 * @param paramName
	 * @param paramValue
	 */
	public static void saveUserInfo(Context context, String paramName,
			String paramValue) {
		Editor edit = getEditor(context);
		edit.putString(paramName, paramValue);
		edit.commit();
	}

	/**
	 * 重置信息
	 * 
	 * @param mContext
	 * @return
	 */
	public static void resetUserInfo(Context mContext) {
		// 载入配置文件
		SharedPreferences sp = mContext.getSharedPreferences(CONFIG_NAME, 0);
		// 写入配置文件
		Editor spEd = sp.edit();
		spEd.putString(USER_ID, "");
		spEd.putString(HEAD_IMAGE, "");
		spEd.putString(NICK_NAME, "");
		spEd.putString(LOGIN_NAME, "");
		spEd.commit();
	}

}
