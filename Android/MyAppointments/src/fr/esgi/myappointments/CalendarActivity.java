package fr.esgi.myappointments;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import fr.esgi.myappointments.service.NotifService;
import fr.esgi.myappointments.widget.NotifManager;

public class CalendarActivity extends FragmentActivity {

	public static final String TAG = "CalendarActivity";
	
	private CaldroidFragment caldroidFragment;
	
	private final SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
	
	private final boolean enableSwipe = true;
	private final boolean showNavArrow = true;
	
	private Date minDate, maxDate, fromDate, toDate;
	private ArrayList<Date> disabledDates;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendar);
		
		caldroidFragment = new CaldroidFragment();
		caldroidFragment.setCaldroidListener(calendarListener);
		
		// If Activity is created after rotation
		if (savedInstanceState != null) {
			caldroidFragment.restoreStatesFromKey(savedInstanceState, "CALDROID_SAVED_STATE");
		}
		// If activity is created from fresh
		else {
			Calendar cal = Calendar.getInstance();
			Bundle args = new Bundle();
			args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
			args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
			args.putBoolean(CaldroidFragment.ENABLE_SWIPE, enableSwipe);
			args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, true);
			args.putInt(CaldroidFragment.START_DAY_OF_WEEK, CaldroidFragment.MONDAY); //Sunday
			caldroidFragment.setArguments(args);
		}

		// Attach to the activity
		getSupportFragmentManager().beginTransaction().replace(R.id.calendarview, caldroidFragment).commit();
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
		initCalendar();
		setupCalendar();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		if (caldroidFragment != null) {
			caldroidFragment.saveStatesToKey(outState, "CALDROID_SAVED_STATE");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
	
	//Initialize Calendar
	private void setupCalendar() {
		caldroidFragment.setMinDate(minDate);
		caldroidFragment.setMaxDate(maxDate);
		caldroidFragment.setDisableDates(disabledDates);
		caldroidFragment.setSelectedDates(fromDate, toDate);
		caldroidFragment.setShowNavigationArrows(showNavArrow);
		caldroidFragment.setEnableSwipe(enableSwipe);
		caldroidFragment.refreshView();
	}
	
	private void initCalendar() {
		Calendar cal = Calendar.getInstance();
		
		// Min date is last 7 days
		cal.add(Calendar.DATE, -7);
		minDate = cal.getTime();
		// Max date is next 7 days
		cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 14);
		maxDate = cal.getTime();

		// Set selected dates
		// From Date
		cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 2);
		fromDate = cal.getTime();
		// To Date
		cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 3);
		toDate = cal.getTime();

		// Set disabled dates
		disabledDates = new ArrayList<Date>();
		for (int i = 5; i < 8; i++) {
			cal = Calendar.getInstance();
			cal.add(Calendar.DATE, i);
			disabledDates.add(cal.getTime());
		}
	}

	//Calendar Listener
	final CaldroidListener calendarListener = new CaldroidListener() {
		@Override
		public void onSelectDate(Date date, View view) {
			Toast.makeText(getApplicationContext(), formatter.format(date),	Toast.LENGTH_SHORT).show();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			
			//Notif
			NotifManager notifManager = new NotifManager(getApplicationContext());
			notifManager.addNotif(HomeActivity.class, 0, "Date", date.toString());
			
			//Alarm
			AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
			Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
			alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pIntent);
		}

		@Override
		public void onChangeMonth(int month, int year) {
			String text = "month: " + month + " year: " + year;
			Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onLongClickDate(Date date, View view) {
			Toast.makeText(getApplicationContext(), "Long click " + formatter.format(date), Toast.LENGTH_SHORT).show();
			
			Intent myIntent = new Intent(getApplicationContext(), NotifService.class);    
			myIntent.putExtra("date", date.getTime());
			myIntent.putExtra("text", "Rendez vous le "+formatter.format(date));
			
			AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
			PendingIntent pendingIntent = PendingIntent.getService(getApplicationContext(), 0, myIntent, 0);
			
			alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+1000*5, pendingIntent); 
		}

		@Override
		public void onCaldroidViewCreated() {
			if (caldroidFragment.getLeftArrowButton() != null) {
				Toast.makeText(getApplicationContext(),	"Caldroid view is created", Toast.LENGTH_SHORT).show();
			}
		}
	};
}
