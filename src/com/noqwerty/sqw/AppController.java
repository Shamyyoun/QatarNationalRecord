package com.noqwerty.sqw;

import java.util.Locale;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.noqwerty.sqw.datamodels.Constants;

public class AppController {
	public static String appLanguage;

	/*
	 * used to get language from sp
	 */
	public static String getLanguage(Context context) {
		appLanguage = getCachedString(context, Constants.SP_SETTINGS,
				Constants.SP_SETTINGS_LANGUAGE_KEY);
		return appLanguage;
	}

	/*
	 * used to update lang in sp
	 */
	public static void updateLanguage(Context context, String language) {
		appLanguage = language;
		updateCacheString(context, Constants.SP_SETTINGS,
				Constants.SP_SETTINGS_LANGUAGE_KEY, language);
	}

	/*
	 * used to update locale setting language
	 */
	public static void updateLocaleSetting(Context context, String appLanguage) {
		// change locale settings
		Resources res = context.getResources();
		DisplayMetrics dm = res.getDisplayMetrics();
		android.content.res.Configuration conf = res.getConfiguration();
		conf.locale = new Locale(appLanguage);
		res.updateConfiguration(conf, dm);
		
		AppController.appLanguage = appLanguage;
	}

	/*
	 * update string value in SP
	 */
	private static void updateCacheString(Context context, String spName,
			String valueName, String value) {

		SharedPreferences sp = context.getSharedPreferences(spName,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.putString(valueName, value);
		editor.commit();
	}

	/*
	 * get chached String from SP
	 */
	private static String getCachedString(Context context, String spName,
			String valueName) {
		SharedPreferences sp = context.getSharedPreferences(spName,
				Context.MODE_PRIVATE);
		String value = sp.getString(valueName, null);

		return value;
	}
}
