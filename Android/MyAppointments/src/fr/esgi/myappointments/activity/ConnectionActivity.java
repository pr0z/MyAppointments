package fr.esgi.myappointments.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import fr.esgi.myappointments.R;
import fr.esgi.myappointments.R.id;
import fr.esgi.myappointments.R.layout;
import fr.esgi.myappointments.R.string;
import fr.esgi.myappointments.fragment.CalendarFragment;
import fr.esgi.myappointments.util.PrefsManager;

public class ConnectionActivity extends Activity {

	private static final String TAG = "ConnectionActivity";
	
	private TextView textInscription, textPasswordForgotten;
	private EditText editEmail, editPassword;
	private String email, password;
	private CheckBox checkRememberMe;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_connection);
		
		getActionBar().setTitle(R.string.title_connection);
//		getActionBar().hide();
		
		editEmail = (EditText) findViewById(R.id.edit_email);
		editPassword = (EditText) findViewById(R.id.edit_password);
//		checkRememberMe = (CheckBox) findViewById(R.id.check_rememberme);
		textInscription = (TextView) findViewById(R.id.inscription);
		textPasswordForgotten = (TextView) findViewById(R.id.passwordForgotten);
		
//		AppointmentsApp.recreateDB(this);
		
		//TODO : Remove to presentation
		editEmail.setText("julienwetstein@gmail.com");
		editPassword.setText("thunder");
	}
	
	@Override 
	protected void onStart() { 
	    super.onStart(); 
	} 
	
	public void connect(View v) {
		initData();
		
		if (checkFields()) {
			if (email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,4}$")) {
//				if (AppointmentsApp.httpRequest(this))
					authentification(email, password);
			} else
				Crouton.makeText(this, R.string.toast_error_email, Style.ALERT).show();
		} else
			Crouton.makeText(this, R.string.toast_fields_empty, Style.ALERT).show();
	}
	
	private void savePreferences() {
		//give form data in SharedPreferences
		SharedPreferences settings = getSharedPreferences(PrefsManager.PREFS_USER, 0);
		SharedPreferences.Editor editor = settings.edit();
		
//		editor.putInt(User.ID_KEY, 1);
//		editor.putInt(User.UNIQUEID_KEY, 1);
//		editor.putString(User.FIRSTNAME_KEY, "Julien");
//		editor.putString(User.LASTNAME_KEY, "WETSTEIN");
//		editor.putString(User.NICKNAME_KEY, "JulienW");
//		editor.putString(User.EMAIL_KEY, "julienwetstein@msn.com");
//		editor.putString(User.PASSWORD_KEY, "thunder");
//		Date createdDate = new Date(113,2,3);
//		editor.putLong(User.CREATIONDATE_KEY, createdDate.getTime());
//		Date lastModifiedDate = new Date(113,2,23);
//		editor.putLong("lastModifiedDate", lastModifiedDate.getTime());
 
		// Commit the edits!
	    editor.commit();
	}
	
	private boolean checkFields() {
		email = editEmail.getText().toString();
		password = editPassword.getText().toString();
		
		boolean isOk = true;
		
		isOk &= email != null && email.length()> 0;
		isOk &= password != null && password.length() > 0;
		
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
	
	private void authentification(String mail, String password) {
		/*UserRequest request = new UserRequest();
		JsonHttpResponseHandler handler = new JsonHttpResponseHandler() {
		    @Override
		    public void onSuccess(JSONObject response) {
		    	Crouton.makeText(ConnectionActivity.this, "WIN", Style.CONFIRM).show();
		    	Log.d("RESULT Connection", response.toString());
//		    	try {
//			        
//		    	} catch (JSONException e) {
//		    		Log.e("JSONException", e.toString());
//		    	}
		    	
				Intent intent = new Intent(ConnectionActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
		    }
		    @Override
		    public void onFailure(Throwable e, String errorResponse) {
		    	Crouton.makeText(ConnectionActivity.this, "FAIL : "+errorResponse, Style.ALERT).show();
		        Log.d("FAIL Connection", errorResponse);
		    }
		    @Override
		     public void onFinish() {
		    	Crouton.makeText(ConnectionActivity.this, "FINISH", Style.INFO).show();
		    	//TODO : Get for Debug
		    	*/Intent intent = new Intent(ConnectionActivity.this, CalendarFragment.class);
				startActivity(intent);/*
		     }
		};
		request.getAuthentification(handler, mail, password);*/
	}
}
