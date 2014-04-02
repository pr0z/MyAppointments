package fr.esgi.myappointments;

import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import fr.esgi.myappointments.business.Category;
import fr.esgi.myappointments.business.CategoryDao;
import fr.esgi.myappointments.business.CategoryDao.Properties;
import fr.esgi.myappointments.business.DaoSession;
import fr.esgi.myappointments.business.Task;

public class CreateTaskActivity extends Activity {

	private static final String TAG = "CreateTaskActivity";
	
//	private TextView textTitle, textDesc;
	private EditText editTitle, editDesc;
//	private String email, password;
//	private CheckBox checkRememberMe;
	private Spinner spinCategory, spinSubcategory;
	
	private CategoryDao categDao;
	private HashMap<Long, List<Category>> mapSubcategory;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_task);
		
		getActionBar().setTitle(R.string.title_create_task);
		
		editTitle = (EditText) findViewById(R.id.edit_title);
		editDesc = (EditText) findViewById(R.id.edit_desc);
		spinCategory = (Spinner) findViewById(R.id.spinner_category);
		spinSubcategory = (Spinner) findViewById(R.id.spinner_subcategory);
		
		DaoSession daoSession = AppointmentsApp.getDBSession(this);
		categDao = daoSession.getCategoryDao();
		
		initCategoryList();
	}
	
	@Override 
	protected void onStart() { 
	    super.onStart(); 
	} 
	
	@SuppressLint("UseSparseArrays")
	private void initCategoryList() {
		List<Category> listCateg = categDao.queryBuilder().where(Properties.ParentCategoryId.isNull()).list();

		initSpinnerAdapter(spinCategory, listCateg);
		
		spinCategory.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapter, View view, int pos, long id) {
				Category cat = (Category) adapter.getItemAtPosition(pos);
				initSubcategoryList(cat.getId());
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
		});
		
		initSubcategories(listCateg);
	}
	
	private void initSubcategories(List<Category> listCateg) {
		//Init Subcategory
		mapSubcategory = new HashMap<Long, List<Category>>();
		
		List<Category> listSubcat = categDao.queryBuilder()
				.where(Properties.ParentCategoryId.isNotNull())
				.list();
		
		for (Category cat : listCateg) {
			//Search correct Subcategory List
			List<Category> listSubcateg = categDao.queryBuilder()
					.where(Properties.ParentCategoryId.isNotNull(), 
							Properties.ParentCategoryId.eq(cat.getId()))
					.list();
			
			mapSubcategory.put(cat.getId(), listSubcateg);
		}
	}
	
	private void initSubcategoryList(Long cat) {
		List<Category> listSubcateg = mapSubcategory.get(cat);
		
		initSpinnerAdapter(spinSubcategory, listSubcateg);
	}
	
	private void initSpinnerAdapter(Spinner spinner, List<Category> listCateg) {
		if (listCateg != null) {
			//Create Adapter
			ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listCateg.toArray());
			spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	//		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.array_category, android.R.layout.simple_spinner_item);
			
			spinner.setAdapter(spinnerArrayAdapter);
		}
	}
	
	public void createTask(View v) {
		if (checkFields()) {
			String title = editTitle.getText().toString();
			String desc = editDesc.getText().toString();
			Category categ = (Category) spinCategory.getSelectedItem();
			Category subcateg = (Category) spinSubcategory.getSelectedItem();
			
			//TODO Create new Task and save in DB
	//		Task newTask = new Task(id, serverId, title, desc, dateBegin, dateEnd, categoryId, companyId)
			Toast.makeText(this, title+" "+desc+" "+categ.getLabel()+" "+subcateg.getLabel(), Toast.LENGTH_LONG).show();
		}
	}
	
	private boolean checkFields() {
		String title = editTitle.getText().toString();
		String desc = editDesc.getText().toString();
		Category categ = (Category) spinCategory.getSelectedItem();
		Category subcateg = (Category) spinSubcategory.getSelectedItem();
		
		boolean isOk = true;
		
		isOk &= title != null && title.length() > 0;
//		isOk &= desc != null && desc.length() > 0;
		isOk &= categ != null;
		isOk &= subcateg != null;
		
		return isOk;
	}

	
}
