package fr.esgi.myappointments;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import android.app.Application;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class AppointmentsApp extends Application {
	
	private static final String TAG = "AppointmentsApp";
	
	public final static String SERVER_URL = "http://www.tonsite.com";
	public final static String SERVER_PAGE_ = "/page.jsp";

	private static SQLiteDatabase db;
	public static String DATABASE_NAME = "myappointments_db";
	
	public static String BUDDYDROID_PREFS = "APPOINTMENTS_PREFERENCES";
	public static String PREFS_USER = "USER_PREFERENCES";
	
	//Pref Friend
	public static String PREF_ID = "PREF_ID";
	
	

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
	}
	
	//Get access to the Database
	/*public static SQLiteDatabase getDB(Context context) {
		if (null == db) {
			DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DATABASE_NAME, null);
			db = helper.getWritableDatabase();
		}
		return db;
	}
	
	//Recreate Database
	public static void recreateDB(Context context) {
		DaoMaster.dropAllTables(AppointmentsApp.getDB(context), true);
		DaoMaster.createAllTables(AppointmentsApp.getDB(context), true);
	}*/
	
	//Check if connection possible
	public static boolean httpRequest(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        // Need permission : android.permission.ACCESS_NETWORK_STATE
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            // display error
            Toast.makeText(context, "Network Error", Toast.LENGTH_SHORT).show();
            return false;
        }
    } 
	
	public static String formatTimeSinceLastUpdate(Date date) {
		String lastConnection = "??";
		long timeUp = (new Date()).getTime() - date.getTime();
		
		if (TimeUnit.MILLISECONDS.toDays(timeUp) >= 1) {
			lastConnection = TimeUnit.MILLISECONDS.toDays(timeUp) + (TimeUnit.MILLISECONDS.toDays(timeUp) >1 ? "jours" : "jour");
		} else if (TimeUnit.MILLISECONDS.toHours(timeUp) >= 1) {
			lastConnection = TimeUnit.MILLISECONDS.toHours(timeUp) + (TimeUnit.MILLISECONDS.toHours(timeUp) >1 ? "heures" : "heure");
		} else if (TimeUnit.MILLISECONDS.toMinutes(timeUp) >= 1) {
			lastConnection = TimeUnit.MILLISECONDS.toMinutes(timeUp) + (TimeUnit.MILLISECONDS.toMinutes(timeUp) >1 ? "minutes" : "minute");
		} else if (TimeUnit.MILLISECONDS.toSeconds(timeUp) >= 1) {
			lastConnection = TimeUnit.MILLISECONDS.toMinutes(timeUp) + (TimeUnit.MILLISECONDS.toMinutes(timeUp) >1 ? "secondes" : "seconde");
		}
		
		return lastConnection;
	}
}
