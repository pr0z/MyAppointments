package fr.esgi.myappointments.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class NotifReceiver extends BroadcastReceiver {
	public static final String TAG = "NotifReceiver";
	
    @Override
    public void onReceive(Context context, Intent intent) {
//        WakefulIntentService.acquireStaticLock(context); //acquire a partial WakeLock
    	Log.v(TAG, "Receive");
    	
        context.startService(new Intent(context, NotifService.class)); //start NotifService
    }
}
