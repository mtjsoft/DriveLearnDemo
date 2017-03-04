package com.huahan.readerviewpager.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

public class UserInfoUtils {
	public static final String STUDY_ID = "study_id";// 学习进度
	public static final String CONFIG_NAME = "DriveLearn";
	public static final String USER_ID = "user_id";
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
		spEd.commit();
	}

}
