package fr.esgi.myappointments.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class PrefsManager {

	public static final String PREFS_MYAPPOINTEMENTS = "myappointements";
		
	public static final String PREFS_USER = "USER_PREFERENCES";
	public static final String PREF_ID = "id";
	
	public static final String PREF_INIT_DATABASE = "init_database";

	private static SharedPreferences prefs;
	
	public static SharedPreferences getPreferences(Context context) {
		if (prefs == null)
			prefs = PreferenceManager.getDefaultSharedPreferences(context);
		
		return prefs; 
	}
	
	public static Editor getPreferencesEditor(Context context) {		
		return getPreferences(context).edit();
	}
	
}

