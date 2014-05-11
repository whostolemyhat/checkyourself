package com.whostolemyhat.checkyourself.views;

import java.util.Calendar;

import models.AlarmModel;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

public class TimePickerFragment extends DialogFragment implements OnTimeSetListener {
	Calendar c;
	AlarmModel alarm;
	
	public static interface OnCompleteListener {
		public abstract void onComplete(AlarmModel alarm);
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
	

	public void setAlarm(AlarmModel alarm) {
		this.alarm = alarm;
		
		c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, alarm.getHour());
		c.set(Calendar.MINUTE, alarm.getMinute());
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		
		return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
	}

	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		alarm.setHour(hourOfDay);
		alarm.setMinute(minute);

		this.listener.onComplete(alarm);
	}
	
}
