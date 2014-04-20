package fr.esgi.myappointments.widget;

import java.util.Calendar;

import fr.esgi.myappointments.util.FormatValue;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.EditText;
import android.widget.TimePicker;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

//	private TimePickedListener mListener;
	private EditText editText;
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current time as the default values for the picker
		final Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		
		// Create a new instance of TimePickerDialog and return it
		return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
	}
	
	public void setEditText(EditText edit) {
		editText = edit;
	}
	
	@Override
	public void onAttach(Activity activity) {
	    // when the fragment is initially shown (i.e. attached to the activity), cast the activity to the callback interface type
    	super.onAttach(activity);
//	    try {
//	        mListener = (TimePickedListener) activity;
//	    } catch (ClassCastException e) {
//	        throw new ClassCastException(activity.toString() + " must implement " + TimePickedListener.class.getName());
//	    }
	}

	 @Override
	 public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
	    // when the time is selected, send it to the activity via its callback interface method
	    Calendar c = Calendar.getInstance();
	    c.set(Calendar.HOUR_OF_DAY, hourOfDay);
	    c.set(Calendar.MINUTE, minute);

	    editText.setText(FormatValue.timeFormat.format(c.getTime()));
	    editText.setTag(c.getTime());
//	    mListener.onTimePicked(c, editText);
	 }
	
	
//	 public static interface TimePickedListener {
//		 public void onTimePicked(Calendar time, EditText edittext);
//	 }
}
