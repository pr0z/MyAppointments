package fr.esgi.myappointments.service;

import java.util.Date;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import fr.esgi.myappointments.R;
import fr.esgi.myappointments.activity.CalendarActivity;

@SuppressLint("NewApi")
public class NotifService extends IntentService {
	public static final String TAG = "NotifService";

	private NotificationManager mManager;
	
	public NotifService() {
		super(TAG);
	}

    @Override
    public void onCreate() {
    	super.onCreate();
    	
    	//Getting Notification Service
	    mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }
    
    @Override
    public void onStart(Intent intent, int startId) {
    	super.onStart(intent, startId);
    	Log.v(TAG, "Start");
    }
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
    	Log.v(TAG, "StartCommand");
    	return super.onStartCommand(intent, flags, startId);
    }
    
    @Override
	protected void onHandleIntent(Intent intent) {
    	Log.v(TAG, "HandleIntent");
    	
    	//Values
	    String title = "MyAppointments Event";
	    String text = "";
	    Date date = null;
	    
	    //Get extra data from Intent
	    if (intent != null) {
	    	text = intent.getStringExtra("text");
	    	date = new Date(intent.getLongExtra("date", 0));
	    }
	    
	   	//When the user taps the notification we have to show the Home Screen of our App, this job can be done with the help of the following Intent.
	    Intent notifIntent = new Intent(this, CalendarActivity.class);
	    notifIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
	    PendingIntent pNotifIntent = PendingIntent.getActivity(this, 0, notifIntent, 0);
	
	    //Build notification
 		Notification notif = new Notification.Builder(this)
 			.setTicker(title)
 	        .setContentTitle(title)
 	        .setContentText(text)
 	        .setSmallIcon(R.drawable.icon)
 	        .setWhen(date.getTime())
 	        .setPriority(Notification.PRIORITY_DEFAULT)
 	        .setNumber(10)
 	        .setContentIntent(pNotifIntent)
 	        .setAutoCancel(false).build();
	
 		//Send notification
	    mManager.notify(0, notif);
    }

  
	@Override
	public void onDestroy() {
		Log.v(TAG, "Notif Service Destroyed");
		super.onDestroy();
	}

	
}
