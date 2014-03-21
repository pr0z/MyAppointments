package fr.esgi.myappointments.util;

import android.widget.EditText;
import fr.esgi.myappointments.R;

public class FormValidator {

	public static boolean checkString(String value) {
		return value != null && value.length() > 0;
	}
	
	public static boolean checkEditText(EditText edit, String var) {
		String value = edit.getText().toString();
		boolean ok = checkString(value);
//		var = value;

		return ok;
	}
}
