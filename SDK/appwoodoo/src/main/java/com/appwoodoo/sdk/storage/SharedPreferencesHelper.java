package com.appwoodoo.sdk.storage;

import android.content.Context;
import android.content.SharedPreferences;

public final class SharedPreferencesHelper {

	private static SharedPreferencesHelper _instance;
	private static SharedPreferences sp;

	private SharedPreferencesHelper() {}
	
	public static SharedPreferencesHelper getInstance() {
		synchronized(SharedPreferencesHelper.class) {
			if (_instance == null) {
				_instance = new SharedPreferencesHelper();
			}
		}
		return _instance;
	}

	public SharedPreferences getSharedPreferences(Context context) {
		if (sp == null) {
			String PREFERENCES_ID = "APPWOODOO_SHARED_PREFERENCES";
			sp = context.getSharedPreferences(PREFERENCES_ID, 0);
		}
		return sp;
	}

	public void storeValue(SharedPreferences preferences, String key, Object value) {
		SharedPreferences.Editor edit = preferences.edit();
		if (value == null) {
			edit.remove(key);
		} else if (value instanceof String) {
			edit.putString(key, (String) value);
		} else if (value instanceof Integer) {
			edit.putInt(key, (Integer) value);
		}
		edit.apply();
	}
	
	public String getStoredString(SharedPreferences preferences, String key) {
		return preferences.getString(key, "");
	}

	public Integer getStoredInteger(SharedPreferences preferences, String key) {
		if (!preferences.contains(key)) {
			return null;
		}
		return preferences.getInt(key, 0);
	}
}
