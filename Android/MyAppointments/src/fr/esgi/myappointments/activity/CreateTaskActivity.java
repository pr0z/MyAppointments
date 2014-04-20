package fr.esgi.myappointments.activity;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import fr.esgi.myappointments.AppointmentsApp;
import fr.esgi.myappointments.R;
import fr.esgi.myappointments.business.Category;
import fr.esgi.myappointments.business.CategoryDao;
import fr.esgi.myappointments.business.CategoryDao.Properties;
import fr.esgi.myappointments.business.DaoSession;
import fr.esgi.myappointments.business.Task;
import fr.esgi.myappointments.widget.DatePickerFragment;
import fr.esgi.myappointments.widget.TimePickerFragment;

public class CreateTaskActivity extends FragmentActivity implements OnClickListener {

	private static final String TAG = "CreateTaskActivity";
	
//	private TextView textTitle, textDesc;
	private EditText editTitle, editDesc, editDateBegin, editDateEnd, editTimeBegin, editTimeEnd;
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
		editDateBegin = (EditText) findViewById(R.id.edit_datebegin);
		editDateEnd = (EditText) findViewById(R.id.edit_dateend);
		editTimeBegin = (EditText) findViewById(R.id.edit_timebegin);
		editTimeEnd = (EditText) findViewById(R.id.edit_timeend);
		spinCategory = (Spinner) findViewById(R.id.spinner_category);
		spinSubcategory = (Spinner) findViewById(R.id.spinner_subcategory);
		
		editDateBegin.setOnClickListener(this);
		editDateEnd.setOnClickListener(this);
		editTimeBegin.setOnClickListener(this);
		editTimeEnd.setOnClickListener(this);
		
		//Load CategoryDao
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
			Date dateBegin = (Date) editDateBegin.getTag();
			Date dateEnd = (Date) editDateEnd.getTag();
			Date timeBegin = (Date) editTimeBegin.getTag();
			Date timeEnd = (Date) editTimeEnd.getTag();
			
			if (dateBegin != null && timeBegin != null)
				dateBegin = addTimeToDate(dateBegin, timeBegin);
			if (dateEnd != null && timeEnd != null)
				dateEnd = addTimeToDate(dateEnd, timeEnd);
			
//			dateBegin = new Date(dateBegin.getTime() + timeBegin.getTime());
			Log.v(TAG, dateBegin.toLocaleString());
			//TODO Create new Task and save in DB
			Task newTask = new Task();
			newTask.setTitle(title);
			newTask.setDesc(desc);
			newTask.setDateBegin(dateBegin);
			
			Toast.makeText(this, title+" "+desc+" "+categ.getLabel()+" "+subcateg.getLabel(), Toast.LENGTH_LONG).show();
		}
	}
	
	private Date addTimeToDate(Date date, Date time) {
		Calendar cDate = Calendar.getInstance();
		Calendar cTime = Calendar.getInstance();
		cDate.setTime(date);
		cTime.setTime(time);
		cDate.set(Calendar.HOUR, cTime.get(Calendar.HOUR));
		cDate.set(Calendar.MINUTE, cTime.get(Calendar.MINUTE));
		cDate.set(Calendar.SECOND, 0);
		
		return cDate.getTime();
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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.edit_datebegin:
			showDatePickerDialog(editDateBegin);
			break;
		case R.id.edit_dateend:
			showDatePickerDialog(editDateEnd);
			break;
		case R.id.edit_timebegin:
			showTimePickerDialog(editTimeBegin);
			break;
		case R.id.edit_timeend:
			showTimePickerDialog(editTimeEnd);
			break;
		}
	}

	public void showDatePickerDialog(EditText edit) {
		// show the time picker dialog
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setEditText(edit);
        newFragment.show(getSupportFragmentManager(), "datePicker");
	}
	
	public void showTimePickerDialog(EditText edit) {
		// show the time picker dialog
		TimePickerFragment newFragment = new TimePickerFragment();
        newFragment.setEditText(edit);
        newFragment.show(getSupportFragmentManager(), "timePicker");
	}
	
//	@Override
//	public void onDatePicked(Calendar date, EditText edit) {
//		//Display the selected date in the EditText
//		edit.setText(FormatValue.dateFormat.format(date.getTime()));
//	}
	
//	@Override
//	public void onTimePicked(Calendar time, EditText edit)	{
//	    //Display the selected time in the EditText
//		edit.setText(FormatValue.timeFormat.format(time.getTime()));
//	}
}
