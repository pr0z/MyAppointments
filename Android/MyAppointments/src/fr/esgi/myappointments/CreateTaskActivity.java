package fr.esgi.myappointments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

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
	
	public void connect(View v) {
		
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
	
	public void inscription(View v) {
		Intent intent = new Intent(this, InscriptionActivity.class);
		startActivity(intent);
	}
	
	public void passwordForgotten(View v) {
		Intent intent = new Intent(this, ForgetPasswordActivity.class);
		startActivity(intent);
	}
	
	private void initData() {
//		DaoSession daoSession = (new DaoMaster(AppointmentsApp.getDB(this))).newSession();
//		UserDao userDao = daoSession.getUserDao();
//		
//		List<User> listUser = userDao.loadAll();
//		if (listUser.size()==0) {
//			userDao.deleteAll();
//			
//			Position p1 = new Position(null, (long) 1, 48.8782753, 2.3836715, 50, new Date());
//			Position p2 = new Position(null, (long) 2, 48.85652902, 2.3412584359025, 55, new Date());
//			Position p3 = new Position(null, (long) 3, 48.855379206, 2.341239091, 61, new Date());
//			daoSession.getPositionDao().insert(p1);
//			daoSession.getPositionDao().insert(p2);
//			daoSession.getPositionDao().insert(p3);
//			
//			User u1 = new User((long)1,1,"WETSTEIN","Julien","ThundeR","0638664364","julienwetstein@msn.com","thunder",1,new Date(),true,true,true,true,true,p1.getId());		
//			User u2 = new User((long)2,2,"MARTIN","Sébastien","Seb Way","+33650181320","seb.vay@gmail.com","thunder",1,new Date(),true,true,true,true,true,p2.getId());		
//			User u3 = new User((long)3,3,"METIAS","Pierre","Pepito","","pepito@gmail.com","thunder",1,new Date(),true,true,true,false,false,p3.getId());		
//		
//			listUser.add(u1);
//			listUser.add(u2);
//			listUser.add(u3);
//			
//			for (User u : listUser)
//				userDao.insert(u);
//		}
	}
}
