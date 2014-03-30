package fr.esgi.myappointments;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import fr.esgi.myappointments.business.Category;
import fr.esgi.myappointments.util.ParserAssets;

public class CreateTaskActivity extends Activity {

	private static final String TAG = "CreateTaskActivity";
	
//	private TextView textTitle, textDesc;
	private EditText editTitle, editDesc;
//	private String email, password;
//	private CheckBox checkRememberMe;
	private Spinner spinCategory, spinSubcategory;
	
	private SparseArray<Integer> mapSubcategory;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_task);
		
		getActionBar().setTitle(R.string.title_create_task);
		
		editTitle = (EditText) findViewById(R.id.edit_title);
		editDesc = (EditText) findViewById(R.id.edit_desc);
		spinCategory = (Spinner) findViewById(R.id.spinner_category);
		spinSubcategory = (Spinner) findViewById(R.id.spinner_subcategory);
		
		initCategoryList();
		
		AppointmentsApp.recreateDB(this);
//		List<Category> listCateg = ParserAssets.loadCategories(this);
//		Log.v(TAG, "CATEG "+listCateg.size());
	}
	
	@Override 
	protected void onStart() { 
	    super.onStart(); 
	} 
	
	@SuppressLint("UseSparseArrays")
	private void initCategoryList() {
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.array_category, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinCategory.setAdapter(adapter);
		
		spinCategory.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapter, View view, int pos, long id) {
				initSubcategoryList(pos);
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		
		//Init Subcategory
		mapSubcategory = new SparseArray<Integer>();
		mapSubcategory.put(0, R.array.array_category_house);
		mapSubcategory.put(1, R.array.array_category_health);
		mapSubcategory.put(2, R.array.array_category_hobby);
		mapSubcategory.put(3, R.array.array_category_administration);
		mapSubcategory.put(4, R.array.array_category_diverse);
	}
	
	private void initSubcategoryList(int cat) {
		int idArray = mapSubcategory.get(cat);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, idArray, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinSubcategory.setAdapter(adapter);
	}
	
	public void createTask(View v) {
		String title = editTitle.getText().toString();
		String desc = editDesc.getText().toString();
//		Category categ = spinCategory.getSelectedItem();
//		Category subcateg = spinSubcategory.getSelectedItem();
	}
	
	private boolean checkFields() {
//		email = editEmail.getText().toString();
//		password = editPassword.getText().toString();
//		
		boolean isOk = true;
//		
//		isOk &= email != null && email.length()> 0;
//		isOk &= password != null && password.length() > 0;
		
		return isOk;
	}

	
}
