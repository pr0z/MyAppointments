package fr.esgi.myappointments.server;

import java.util.Date;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class UserRequest {
	
	private final static String URL_NEARUSERS = "nearusers";
	private final static String URL_USERS = "users";
	private final static String URL_INSCRIPTION = "inscription";
	private final static String URL_PASSWORDFORGOTTEN = "sendpassword";
	private final static String URL_AUTHENTIFICATION = "connection";
	
//	private DaoSession daoSession;
	
	public UserRequest() {}
	
//	public UserRequest(DaoSession session) {
//		daoSession = session;
//	}
	
	//Get near Users with Position
	public void getNearUsers(JsonHttpResponseHandler handler, int myuid, int radius) {
		RequestParams params = new RequestParams();
		params.put("radius", radius+"");
		
		MyAppointmentsRestClient.get(URL_NEARUSERS+"/"+myuid, params, handler);
	}
	
	//Post new User
	public void postNewUser(JsonHttpResponseHandler handler, User user) {
		RequestParams params = new RequestParams();
		
		params.put(User.FIRSTNAME_KEY, user.getFirstname());
		params.put(User.LASTNAME_KEY, user.getLastname());
		params.put(User.NICKNAME_KEY, user.getNickname());
		params.put(User.EMAIL_KEY, user.getEmail());
		params.put(User.PASSWORD_KEY, user.getPassword());
		params.put(User.PHONE_KEY, user.getPhone());
		params.put(User.GENDER_KEY, user.getGender()+"");
		params.put(User.CREATIONDATE_KEY, (new Date()).getTime()+"");
		params.put(User.ALLOWTOBEFOLLOWED_KEY, user.getAllowToBeFollowed() ? "1" : "0");
		params.put(User.ACCEPTMESSAGE_KEY, user.getAcceptMessage() ? "1" : "0");
		params.put(User.SHOWONLINE_KEY, user.getShowOnline() ? "1" : "0");
		
		MyAppointmentsRestClient.post(URL_INSCRIPTION, params, handler);
	}
	
	//Post new User
	public void sendPasswordForgotten(JsonHttpResponseHandler handler, String email) {
		RequestParams params = new RequestParams();
		
		params.put(User.EMAIL_KEY, email);
		
		MyAppointmentsRestClient.post(URL_PASSWORDFORGOTTEN, params, handler);
	}
	
	//Get Authentification User
	public void getAuthentification(JsonHttpResponseHandler handler, String email, String password) {
		RequestParams params = new RequestParams();
		
		params.put(User.EMAIL_KEY, email);
		params.put(User.PASSWORD_KEY, password);
		
		MyAppointmentsRestClient.post(URL_AUTHENTIFICATION, params, handler);
	}
}
