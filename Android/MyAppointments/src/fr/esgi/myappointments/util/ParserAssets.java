package fr.esgi.myappointments.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import fr.esgi.myappointments.AppointmentsApp;
import fr.esgi.myappointments.business.Category;
import fr.esgi.myappointments.business.CategoryDao;

public class ParserAssets {

	public static final String TAG = "ParserAssets";
	
	public static final String pathDataDatabase = "database/";
	public static final String fileCategory = "table_category.json"; 
	
	public static List<Category> loadCategories(Context context, String node) {
		List<Category> categories = new ArrayList<Category>();
		
		try {
			JSONObject jsonObject = new JSONObject(loadJSONFromAsset(context, pathDataDatabase+fileCategory));
			
			//Categories
			JSONArray jsonArrayCateg = jsonObject.getJSONArray(node);
			for (int i = 0; i < jsonArrayCateg.length(); i++) {
				JSONObject jsonObjectCateg = jsonArrayCateg.getJSONObject(i);
				Category categ = new Category();
				
				if (jsonObjectCateg.has("parentId"))
					categ.setParentCategoryId(jsonObjectCateg.getLong("parentId"));
				categ.setServerId(jsonObjectCateg.getInt("serverId"));
				categ.setLabel(jsonObjectCateg.getString("label"));
				
				categories.add(categ);
			}	
		} catch (JSONException e) {
			Log.e(TAG, e.toString());
		}
		
		return categories;
	}
	
	public static String loadJSONFromAsset(Context context, String filename) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}
