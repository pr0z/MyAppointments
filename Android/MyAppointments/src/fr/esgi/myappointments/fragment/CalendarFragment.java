package fr.esgi.myappointments.fragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import fr.esgi.myappointments.R;
import fr.esgi.myappointments.activity.CreateTaskActivity;
import fr.esgi.myappointments.activity.HomeActivity;
import fr.esgi.myappointments.service.NotifReceiver;
import fr.esgi.myappointments.util.FormatValue;
import fr.esgi.myappointments.widget.NotifManager;

public class CalendarFragment extends Fragment {

	public static final String TAG = "CalendarActivity";
	
	private CaldroidFragment caldroidFragment;
	
	private final boolean enableSwipe = true;
	private final boolean showNavArrow = true;
	
	private Date minDate, maxDate, fromDate, toDate;
	private ArrayList<Date> disabledDates;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		((ActionBarActivity) getActivity()).getSupportActionBar().setTitle(R.string.title_home);
		
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
			args.putInt(CaldroidFragment.START_DAY_OF_WEEK, CaldroidFragment.MONDAY); 
			caldroidFragment.setArguments(args);
		}

		// Attach to the activity
		getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.calendarview, caldroidFragment).commit();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.activity_calendar, container, false);
		
		return rootView;
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
		initCalendar();
		setupCalendar();
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setHasOptionsMenu(true);
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		if (caldroidFragment != null) {
			caldroidFragment.saveStatesToKey(outState, "CALDROID_SAVED_STATE");
		}
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		getActivity().getMenuInflater().inflate(R.menu.calendar, menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_create_task:
			Intent intent = new Intent(getActivity(), CreateTaskActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
		
		return super.onOptionsItemSelected(item);
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
			Toast.makeText(getActivity().getApplicationContext(), FormatValue.dateSpaceFormat.format(date),	Toast.LENGTH_SHORT).show();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			
			//Notif
			NotifManager notifManager = new NotifManager(getActivity().getApplicationContext());
			notifManager.addNotif(HomeActivity.class, 0, "Date", date.toString());
			
			//Alarm
			AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
			Intent intent = new Intent(getActivity().getApplicationContext(), HomeActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			PendingIntent pIntent = PendingIntent.getActivity(getActivity().getApplicationContext(), 0, intent, 0);
			alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pIntent);
		}

		@Override
		public void onChangeMonth(int month, int year) {
			String text = "month: " + month + " year: " + year;
			Toast.makeText(getActivity().getApplicationContext(), text, Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onLongClickDate(Date date, View view) {
			Toast.makeText(getActivity().getApplicationContext(), "Long click " + FormatValue.dateSpaceFormat.format(date), Toast.LENGTH_SHORT).show();
			
			Intent myIntent = new Intent(getActivity().getApplicationContext(), NotifReceiver.class);    
			myIntent.putExtra("date", date.getTime());
			myIntent.putExtra("text", "Rendez vous le "+FormatValue.dateSpaceFormat.format(date));
			
			AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(getActivity().ALARM_SERVICE);
			PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity().getApplicationContext(), 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
			
			alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+1000*5, pendingIntent); 
		}

		@Override
		public void onCaldroidViewCreated() {
			if (caldroidFragment.getLeftArrowButton() != null) {
				Toast.makeText(getActivity().getApplicationContext(),	"Caldroid view is created", Toast.LENGTH_SHORT).show();
			}
		}
	};
}
