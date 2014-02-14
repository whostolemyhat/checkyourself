package com.whostolemyhat.checkyourself;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

@SuppressLint("ValidFragment")
public class DateTimeDialogFragment extends DialogFragment implements OnDateChangedListener, OnTimeChangedListener {
	// constants
	public static final int DATE_PICKER = 1;
	public static final int TIME_PICKER = 2;
	public static final int DATE_TIME_PICKER = 3;
	
	private DatePicker datePicker;
	private TimePicker timePicker;
	private Calendar cal;
	private Activity activity;
	
	private int DialogType;
	private View view;
	
	public DateTimeDialogFragment(Activity activity) {
		this(activity, TIME_PICKER);
	}
	
	public DateTimeDialogFragment(Activity activity, int DialogType) {
		this.activity = activity;
		this.DialogType = DialogType;
		
		// inflate layout
		// pass null as parent
		LayoutInflater inflater = activity.getLayoutInflater();
		view = inflater.inflate(R.layout.date_time_dialog, null);
		
		cal = Calendar.getInstance();
		
		datePicker = (DatePicker) view.findViewById(R.id.DatePicker);
		datePicker.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), this);
		
		timePicker = (TimePicker) view.findViewById(R.id.TimePicker);
		
		setIs24HourView(true);
		setCalendarViewShown(false);
		
		switch(DialogType) {
		case DATE_PICKER:
			timePicker.setVisibility(View.GONE);
			break;
		case TIME_PICKER:
			datePicker.setVisibility(View.GONE);
			break;
		}
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Builder builder = new AlertDialog.Builder(activity);
		
		builder.setView(view);
		builder.setMessage("Set Date")
				.setPositiveButton("Set",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// user ok
							}
						})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								DateTimeDialogFragment.this.getDialog().cancel();
							}
						});
		return builder.create();
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		timePicker.setOnTimeChangedListener(this);
	}
	
	public int get(final int field) {
		return cal.get(field);
	}
	
	public long getDateTimeMillis() {
		return cal.getTimeInMillis();
	}
	
	public void setIs24HourView(boolean is24HourView) {
		timePicker.setIs24HourView(is24HourView);
	}
	
	public boolean is24HourView() {
		return timePicker.is24HourView();
	}
	
	public void setCalendarViewShown(boolean calendarView) {
		datePicker.setCalendarViewShown(calendarView);
	}
	
	public boolean CalendarViewShown() {
		return datePicker.getCalendarViewShown();
	}
	
	public void updateDate(int year, int monthOfYear, int dayOfMonth) {
		datePicker.updateDate(year, monthOfYear, dayOfMonth);
	}
	
	public void updateTime(int currentHour, int currentMinute) {
		timePicker.setCurrentHour(currentHour);
		timePicker.setCurrentMinute(currentMinute);
	}
	
	public String getDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:mm:ss", Locale.UK);
		return sdf.format(cal.getTime());
	}
	
	@Override
	public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		cal.set(year, monthOfYear, dayOfMonth, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE));
	}

	@Override
	public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), hourOfDay, minute);
	}

}
