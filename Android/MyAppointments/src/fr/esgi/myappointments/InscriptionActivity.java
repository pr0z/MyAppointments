package fr.esgi.myappointments;

import java.util.Date;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.ViewSwitcher;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import fr.esgi.myappointments.util.FormValidator;

public class InscriptionActivity extends FragmentActivity {

	private static final String TAG = "InscriptionActivity";
	
	private final String CLIENT_KEY = "client";
	private final String PRO_KEY = "pro";
	
	private EditText editClientBirth, editClientFirstname, editClientLastname;
	private EditText editProCompanyName, editProNumSiren, editProContactFirstname, editProContactLastname, editProAddress;
	private EditText editEmail, editPhone, editPassword, editConfirmPassword;
	private RadioGroup radioGroup;
	private RadioButton radioButton;
	private ViewSwitcher viewSwitcher;
//	private Calendar calBirth;
//	private Date dateBirth;
	private Date dateCreation;
	private String firstname, lastname, phone;
	private String companyName, numSiren, contactFirstname, contactLastname, address;
	private String status, email, password, confirmpassword; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inscription);
		
		//Client
		editClientFirstname = (EditText) findViewById(R.id.edit_firstname);
		editClientLastname = (EditText) findViewById(R.id.edit_lastname);

		//Pro
		editProCompanyName = (EditText) findViewById(R.id.edit_company_name);
		editProNumSiren = (EditText) findViewById(R.id.edit_num_siren);
		editProContactFirstname = (EditText) findViewById(R.id.edit_contact_firstname);
		editProContactLastname = (EditText) findViewById(R.id.edit_contact_lastname);
		editProAddress = (EditText) findViewById(R.id.edit_address);
		
		//Global
		editPhone = (EditText) findViewById(R.id.edit_phone);
		editEmail = (EditText) findViewById(R.id.edit_email);
		editPassword = (EditText) findViewById(R.id.edit_password);
		editConfirmPassword = (EditText) findViewById(R.id.edit_passwordconfirm);
		
		//Other
		radioGroup = (RadioGroup) findViewById(R.id.radiogroup_status);
		viewSwitcher = (ViewSwitcher)findViewById(R.id.viewSwitcher);
		
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.radio_status_client) {
					status = CLIENT_KEY;
					viewSwitcher.showPrevious();
				} else if (checkedId == R.id.radio_status_pro) {
					status = PRO_KEY;
					viewSwitcher.showNext();
				}
			}
		});

		
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
			//Get values
			getEditTextValues();
			
			if (password.equals(confirmpassword)) {
				if (AppointmentsApp.httpRequest(this)) {
//					User user = new User(null,0,lastname,firstname,login,phone,email,confirmpassword,gender,new Date(),true,true,true,false,true,null);
					postAccount();
					finish();
				}
			} else 
				Crouton.makeText(this, R.string.toast_error_password, Style.ALERT).show();
		} else 
			Crouton.makeText(this, R.string.toast_fields_empty, Style.ALERT).show();
	}
	
	//Check EditText
	private boolean checkForm() {
		boolean isOk = true;
		
		int selectedId = radioGroup.getCheckedRadioButtonId();
	    radioButton = (RadioButton) findViewById(selectedId);
		status = radioButton.getTag().toString();
		Log.v("STATUT", status);
		
		//Check values
		if (status.equalsIgnoreCase(CLIENT_KEY)) {
			isOk &= checkEditTextValue(editClientLastname);
			isOk &= checkEditTextValue(editClientFirstname);
		} else if (status.equalsIgnoreCase(PRO_KEY)) {
			isOk &= checkEditTextValue(editProCompanyName);
			isOk &= checkEditTextValue(editProNumSiren);
			isOk &= checkEditTextValue(editProContactFirstname);
			isOk &= checkEditTextValue(editProContactLastname);
			isOk &= checkEditTextValue(editProAddress);
		}
		boolean emailOk = checkEditTextValue(editEmail);	
		boolean emailValid = false;
		if (emailOk) {
			emailValid = editEmail.getText().toString().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,4}$");
			if (!emailValid) editEmail.setError(getResources().getString(R.string.error_email));
		}
		isOk &= emailOk && emailValid;
		isOk &= checkEditTextValue(editPassword);
		isOk &= checkEditTextValue(editConfirmPassword);
		isOk &= checkEditTextValue(editPhone);
		
		return (isOk);
	}
	
	private boolean checkEditTextValue(EditText edit) {
		boolean ok = FormValidator.checkString(edit.getText().toString());
		if (!ok)
			edit.setError(getResources().getString(R.string.error_empty_field));
		return ok;
	}
	
	private void getEditTextValues() {
		if (status == CLIENT_KEY) {
			lastname = editClientLastname.getText().toString();
			firstname = editClientFirstname.getText().toString();
		} else if (status == PRO_KEY) {
			companyName = editProCompanyName.getText().toString();
			numSiren = editProNumSiren.getText().toString();
			contactFirstname = editProContactFirstname.getText().toString();
			contactLastname = editProContactLastname.getText().toString();
			address = editProAddress.getText().toString(); 
		}
		phone = editPhone.getText().toString();
		email = editEmail.getText().toString();
		password = editPassword.getText().toString();
		confirmpassword = editConfirmPassword.getText().toString();
	}
	
	private void postAccount() {
		Log.v(status, email+" "+password+" "+confirmpassword);
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
//		
//		request.postNewUser(handler, user);
	}
}
