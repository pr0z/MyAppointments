package fr.esgi.myappointments.widget;

import java.util.Calendar;

import fr.esgi.myappointments.util.FormatValue;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.DatePicker;
import android.widget.EditText;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

//	private DatePickedListener mListener;
	private EditText editText;
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current time as the default values for the picker
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MINUTE);
		int day = c.get(Calendar.DAY_OF_MONTH);
		
		// Create a new instance of TimePickerDialog and return it
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}
	
	public void setEditText(EditText edit) {
		editText = edit;
	}
	
	@Override
	public void onAttach(Activity activity) {
	    // when the fragment is initially shown (i.e. attached to the activity), cast the activity to the callback interface type
    	super.onAttach(activity);
//	    try {
//	        mListener = (DatePickedListener) activity;
//	    } catch (ClassCastException e) {
//	        throw new ClassCastException(activity.toString() + " must implement " + DatePickedListener.class.getName());
//	    }
	}

	 @Override
	 public void onDateSet(DatePicker view, int year, int month, int day) {
	    // when the time is selected, send it to the activity via its callback interface method
	    Calendar c = Calendar.getInstance();
	    c.set(Calendar.YEAR, year);
	    c.set(Calendar.MONTH, month);
	    c.set(Calendar.DAY_OF_MONTH, day);

//	    mListener.onDatePicked(c, editText);
	    editText.setText(FormatValue.dateFormat.format(c.getTime()));
	    editText.setTag(c.getTime());
	 }
	
	
//	 public static interface DatePickedListener {
//		 public void onDatePicked(Calendar time, EditText edit);
//	 }
}
