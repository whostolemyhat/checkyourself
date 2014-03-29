package com.whostolemyhat.checkyourself.views;

import java.util.Calendar;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TimePicker;

public class TimePickerFragment extends DialogFragment implements OnTimeSetListener {
	public static interface OnCompleteListener {
		public abstract void onComplete(int hour, int minute);
	}
	
	private OnCompleteListener listener;
	
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			this.listener = (OnCompleteListener) getActivity();
		} catch(final ClassCastException e) {
			throw new ClassCastException(activity.toString() + " must implement OnCompleteListener");
		}
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		
		return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
	}

	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		this.listener.onComplete(hourOfDay, minute);
		Log.d("CheckYourself", Integer.toString(hourOfDay) + " " + Integer.toString(minute));
	}
	
}
