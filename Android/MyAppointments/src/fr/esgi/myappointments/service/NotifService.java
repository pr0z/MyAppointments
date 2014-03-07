package fr.esgi.myappointments.service;

import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import fr.esgi.myappointments.CalendarActivity;
import fr.esgi.myappointments.R;

@SuppressLint("NewApi")
public class NotifService extends Service {
	public static final String TAG = "NotifService";
	
	private NotificationManager mManager;

    @Override
	public IBinder onBind(Intent arg0) {
    	return null;
    }

    @Override
    public void onCreate() {
    	super.onCreate();
    	
    	// Getting Notification Service
	    mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
	    //values
	    String title = "MyAppointments Event";
	    String text = "";
	    Date date = null;
	    
	    //Get extra data
	    if (intent != null) {
	    	text = intent.getStringExtra("text");
	    	date = new Date(intent.getLongExtra("date", 0));
	    }
	    
	   	//When the user taps the notification we have to show the Home Screen of our App, this job can be done with the help of the following Intent.
	    Intent notifIntent = new Intent(this, CalendarActivity.class);
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
	
	    mManager.notify(0, notif);
	    
	    return super.onStartCommand(intent, Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP, startId);
	}
	
	@Override
	public void onDestroy() {
		Log.e(TAG, "Alam Services Destroyed");
		super.onDestroy();
	}

}
