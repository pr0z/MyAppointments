package fr.esgi.myappointments;

import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

@SuppressLint("NewApi")
public class InscriptionActivity extends FragmentActivity {

	private static final String TAG = "InscriptionActivity";
	
	private EditText editBirth, editLogin, editEmail, editPassword, editFirstname, editLastname, editConfirmPassword, editPhone;
	private RadioGroup radioGroup;
	private RadioButton radioButton;
//	private Calendar calBirth;
//	private Date dateBirth;
	private String login, email, password, confirmpassword, firstname, lastname, phone;
	private int gender; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inscription);
		
		editConfirmPassword = (EditText) findViewById(R.id.edit_passwordconfirm);
		editFirstname = (EditText) findViewById(R.id.edit_firstname);
		editLastname = (EditText) findViewById(R.id.edit_lastname);
		editPhone = (EditText) findViewById(R.id.edit_phone);
		editEmail = (EditText) findViewById(R.id.edit_email);
		editLogin = (EditText) findViewById(R.id.edit_login);
		editPassword = (EditText) findViewById(R.id.edit_password);
		radioGroup = (RadioGroup) findViewById(R.id.radiogroup_gender);
		
//		editBirth = (EditText) findViewById(R.id.edit_birth);
//		//Update DatePickers
//		calBirth = Calendar.getInstance();
////		dateBirth = new Date();
//		editBirth.setText(calBirth.get(Calendar.DAY_OF_MONTH)+"/"+calBirth.get(Calendar.MONTH)+"/"+calBirth.get(Calendar.YEAR));
//		editBirth.setEnabled(true);
//		InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//		imm.hideSoftInputFromWindow(editBirth.getWindowToken(), 0);
//				
//		//DatePickerDialog
//		final DatePickerDialog datePickerDialog = new DatePickerDialog();
//		datePickerDialog.initialize(new OnDateSetListener() {
//			@Override
//			public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) {
////				Toast.makeText(getBaseContext(), "new date:" + year + "-" + month + "-" + day, Toast.LENGTH_LONG).show();
//				editBirth.setText(day+"/"+month+"/"+year);
//				calBirth.set(year, month, day);
//				dateBirth = new Date(calBirth.getTimeInMillis());
//				editBirth.setInputType(InputType.TYPE_NULL);
//				editBirth.requestFocus();
//			}
//		}, calBirth.get(Calendar.YEAR), calBirth.get(Calendar.MONTH), calBirth.get(Calendar.DAY_OF_MONTH));
//		
//		editBirth.setInputType(InputType.TYPE_NULL);
//		editBirth.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				editLogin.setInputType(InputType.TYPE_CLASS_TEXT);
//				datePickerDialog.setYearRange(1920, 2015);
//				if (!datePickerDialog.isAdded() || datePickerDialog.isHidden())
//					datePickerDialog.show(getSupportFragmentManager(), "datepicker");
//			}
//		});
	}
	
	@Override 
	protected void onStart() { 
	    super.onStart(); 
	} 
	
	public void createAccount(View v) {
		boolean isOk = checkForm();
		
		if (isOk) {
			if (password.equals(confirmpassword)) {
				if (AppointmentsApp.httpRequest(this)) {
//					User user = new User(null,0,lastname,firstname,login,phone,email,confirmpassword,gender,new Date(),true,true,true,false,true,null);
//					postAccount(user);
					finish();
				}
			} else 
				Crouton.makeText(this, R.string.toast_error_password, Style.ALERT).show();
		} else 
			Crouton.makeText(this, R.string.toast_fields_empty, Style.ALERT).show();
	}
	
	private boolean checkForm() {
		confirmpassword = editConfirmPassword.getText().toString();
		password = editPassword.getText().toString();
		phone = editPhone.getText().toString();
		email = editEmail.getText().toString();
		login = editLogin.getText().toString();
		lastname = editLastname.getText().toString();
		firstname = editFirstname.getText().toString();
		int selectedId = radioGroup.getCheckedRadioButtonId();
	    radioButton = (RadioButton) findViewById(selectedId);
		gender = Integer.parseInt(radioButton.getTag().toString());
		
		boolean isOk = true;
		
		isOk &= confirmpassword != null && confirmpassword.length() > 0;
		isOk &= password != null && password.length() > 0;
		isOk &= email != null && email.length() > 0 && email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,4}$");
		isOk &= login != null && login.length() > 0;
		isOk &= lastname != null && lastname.length() > 0;
		isOk &= firstname != null && firstname.length() > 0;
		isOk &= phone != null && phone.length()>0;
		
		return (isOk);
	}
	
//	private void postAccount(User user) {
//		UserRequest request = new UserRequest();
//		JsonHttpResponseHandler handler = new JsonHttpResponseHandler() {
//		    @Override
//		    public void onSuccess(JSONArray response) {
//		    	Crouton.makeText(InscriptionActivity.this, "WIN", Style.CONFIRM).show();
//		    	Log.d("RESULT Inscription", response.toString());
//		    	try {
//			        for(int i=0 ; i<response.length() ; i++) {
//						JSONObject object = (JSONObject) response.get(i);
//			        }
//		    	} catch (JSONException e) {
//		    		Log.e("JSONException", e.toString());
//		    	}
//		    }
//		    @Override
//		    public void onFailure(Throwable e, String errorResponse) {
//		    	Crouton.makeText(InscriptionActivity.this, "FAIL "+errorResponse, Style.ALERT).show();
//		        Log.d("FAIL Inscription", errorResponse);
//		    }
//		    @Override
//		     public void onFinish() {
//		    	Crouton.makeText(InscriptionActivity.this, "FINISH", Style.INFO).show();
//		     }
//		};
//		request.postNewUser(handler, user);
//	}
}
