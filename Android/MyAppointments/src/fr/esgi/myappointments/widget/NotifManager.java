package fr.esgi.myappointments.widget;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import fr.esgi.myappointments.R;

public class NotifManager {

	private NotificationManager notificationManager;
	private Context context;
	
	public NotifManager(Context ctx) {
		context = ctx;
		notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
	}
	
	@SuppressLint("NewApi")
	public void addNotif(Class<?> goActivity, int idNotif, String title, String text) {
		// prepare intent which is triggered if the notification is selected
		Intent intent = new Intent(context, goActivity);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, 0);
		
		// build notification
		Notification notif = new Notification.Builder(context)
			.setTicker("MyAppointments "+title)
	        .setContentTitle(title)
	        .setContentText(text)
	        .setSmallIcon(R.drawable.icon)
	        .setWhen(System.currentTimeMillis()+1000000)
	        .setPriority(Notification.PRIORITY_DEFAULT)
	        .setNumber(10)
//	        .setProgress(100, 15, false)
	        .setContentIntent(pIntent)
	        .setAutoCancel(true).build();
//		        .addAction(R.drawable.icone, "Call", pIntent)
//		        .addAction(R.drawable.icone, "More", pIntent)
//		        .addAction(R.drawable.icone, "And more", pIntent)
		
		notificationManager.notify(idNotif, notif);	
	}
	
	public void deleteNotif(int id){
    	notificationManager.cancel(id);
    }
	
	public void deleteAll() {
		notificationManager.cancelAll();
	}
}
