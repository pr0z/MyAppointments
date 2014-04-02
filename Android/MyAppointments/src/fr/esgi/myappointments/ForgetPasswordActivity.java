package fr.esgi.myappointments;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

@SuppressLint("NewApi")
public class ForgetPasswordActivity extends FragmentActivity {

	private static final String TAG = "ForgottenPasswordActivity";
	
	private EditText editEmail;
	private String email;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forget_password);
		
		getActionBar().setTitle(R.string.title_forget_password);
		
		editEmail = (EditText) findViewById(R.id.edit_email);
	}
	
	@Override 
	protected void onStart() { 
	    super.onStart(); 
	} 
	
	public void sendEmail(View v) {
		email = editEmail.getText().toString();
		
		if (email!=null && email.length()>0) {
			if (AppointmentsApp.httpRequest(this)) {
				sendPasswordForgotten(email);
			}
		} else 
			Crouton.makeText(this, R.string.toast_fields_empty, Style.ALERT).show();
	}
	
	private void sendPasswordForgotten(String mail) {
		/*UserRequest request = new UserRequest();
		JsonHttpResponseHandler handler = new JsonHttpResponseHandler() {
		    @Override
		    public void onSuccess(JSONObject response) {
		    	Crouton.makeText(ForgetPasswordActivity.this, "WIN", Style.CONFIRM).show();
		    	Log.d("RESULT PasswordForgotten", response.toString());
//		    	try {
//			       
//		    	} catch (JSONException e) {
//		    		Log.e("JSONException", e.toString());
//		    	}
		    }
		    @Override
		    public void onFailure(Throwable e, String errorResponse) {
		    	Crouton.makeText(ForgetPasswordActivity.this, "FAIL "+errorResponse, Style.ALERT).show();
		        Log.d("FAIL PasswordForgotten", errorResponse);
		    }
		    @Override
		     public void onFinish() {
		    	Crouton.makeText(ForgetPasswordActivity.this, "FINISH", Style.INFO).show();
		     }
		};
		request.sendPasswordForgotten(handler, mail);*/
	}
}
