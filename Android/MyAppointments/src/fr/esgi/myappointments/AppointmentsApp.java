package fr.esgi.myappointments;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.SparseArray;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import fr.esgi.myappointments.business.Category;
import fr.esgi.myappointments.business.CategoryDao;
import fr.esgi.myappointments.business.DaoMaster;
import fr.esgi.myappointments.business.DaoMaster.DevOpenHelper;
import fr.esgi.myappointments.business.DaoSession;
import fr.esgi.myappointments.util.BitmapLruCache;
import fr.esgi.myappointments.util.ParserAssets;
import fr.esgi.myappointments.util.PrefsManager;

public class AppointmentsApp extends Application {
	
	private static final String TAG = "AppointmentsApp";
	
	public final static String SERVER_URL = "http://www.tonsite.com";
	public final static String SERVER_PAGE_ = "/page.jsp";

	private static SQLiteDatabase db;
	public static String DATABASE_NAME = "myappointments_db";
	
	private SharedPreferences mPrefs;
	private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

	@Override
	public void onCreate() {
		super.onCreate();
	
		mPrefs = PrefsManager.getPreferences(this);
		
		initPoolRequest();
		
		if (mPrefs.contains(PrefsManager.PREF_INIT_DATABASE) && mPrefs.getBoolean(PrefsManager.PREF_INIT_DATABASE, true))
			recreateDB(this);
	}

	@Override
	public void onTerminate() {
		 mRequestQueue.stop();
		 
		super.onTerminate();
	}
	
	//Get access to the Database
	public static SQLiteDatabase getDB(Context context) {
		if (null == db) {
			DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DATABASE_NAME, null);
			db = helper.getWritableDatabase();
		}
		return db;
	}
	
	public static DaoSession getDBSession(Context context) {
		SQLiteDatabase sqldb = getDB(context);
		DaoMaster daoMaster = new DaoMaster(sqldb);
		return daoMaster.newSession();
	}
	
	//Recreate Database
	public void recreateDB(Context context) {
		SQLiteDatabase sqldb = AppointmentsApp.getDB(context);
		
		DaoMaster.dropAllTables(sqldb, true);
		DaoMaster.createAllTables(sqldb, true);
		initDatabase(sqldb, context); 
		
		Editor editor = PrefsManager.getPreferencesEditor(context);
		editor.putBoolean(PrefsManager.PREF_INIT_DATABASE, false).commit();
	}
	
	//Init Database
	public static void initDatabase(SQLiteDatabase sqldb, Context context) {
		DaoMaster daoMaster = new DaoMaster(sqldb);
		DaoSession daoSession = daoMaster.newSession();
		CategoryDao categDao = daoSession.getCategoryDao();
		
		//Category
		List<Category> listCateg = ParserAssets.loadCategories(context, "categories");
		categDao.insertInTx(listCateg);
		
		SparseArray<Category> mapCateg = new SparseArray<Category>();
		for (Category categ : listCateg)
			mapCateg.put(categ.getServerId(), categ);
		
		//Subcategory
		List<Category> listSubcateg = ParserAssets.loadCategories(context, "subcategories");
		for (Category categ : listSubcateg) {
			Category parent = mapCateg.get(categ.getParentCategoryId().intValue());
			if (parent != null)
				categ.setParentCategory(parent);
//			Log.v(TAG, "PARENT="+categ.getParentCategory().getServerId()+" "+categ.getParentCategory().getLabel()+" "+categ.getParentCategoryId());
		}
		categDao.insertInTx(listSubcateg);
	}
	
	//Init Volley pool request
	private void initPoolRequest() {
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        mImageLoader = new ImageLoader(mRequestQueue, new BitmapLruCache());
        mRequestQueue.start();
	}
	
	public RequestQueue getVolleyRequestQueue() {
		if (mRequestQueue == null)
            initPoolRequest();

        return mRequestQueue;
    }
 
    public ImageLoader getVolleyImageLoader() {
    	if (mImageLoader == null)
    		initPoolRequest();
    	
        return mImageLoader;
    }
	
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
