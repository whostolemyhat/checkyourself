package com.whostolemyhat.checkyourself.views;

import java.util.ArrayList;
import java.util.List;

import models.AlarmModel;
import android.app.ActionBar;
import android.app.DialogFragment;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.whostolemyhat.checkyourself.AlarmAdapter;
import com.whostolemyhat.checkyourself.AlarmReceiver;
import com.whostolemyhat.checkyourself.R;
import com.whostolemyhat.checkyourself.data.AlarmsDataSource;
import com.whostolemyhat.checkyourself.views.LabelDialog.LabelDialogListener;


public class AlarmView extends ListActivity implements TimePickerFragment.OnCompleteListener,
LabelDialogListener{
	AlarmReceiver alarmReceiver = new AlarmReceiver();
	List<AlarmModel> modelVals = new ArrayList<AlarmModel>();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alarm_list_view);
		
		// read models in from storage		
		AlarmsDataSource datasource = new AlarmsDataSource(this);
		datasource.open();
		modelVals = datasource.getAll();
		datasource.close();

		final AlarmAdapter adapter = new AlarmAdapter(this, R.layout.alarm_list, modelVals);
		setListAdapter(adapter);
		getListView().setItemsCanFocus(true);
		
		ActionBar actionBar = getActionBar();
		actionBar.setHomeButtonEnabled(true);

		
		Button addNew = (Button) findViewById(R.id.add);
		addNew.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "Make new alarm lol", Toast.LENGTH_SHORT).show();
				// create new alarm with defaults
				AlarmModel newAlarm = new AlarmModel(9, 0, "New alarm");
				modelVals.add(newAlarm);
				adapter.notifyDataSetChanged();
				
				// save alarm to db
				AlarmsDataSource datasource = new AlarmsDataSource(getApplicationContext());
				datasource.open();
				datasource.createAlarm(newAlarm);
				datasource.close();
			}
		});
		
	}
	
	
	// TODO: alarm set being called twice :s
	public void setAlarmTime(View v) {
		Toast.makeText(getApplicationContext(), "Setting alarm time", Toast.LENGTH_SHORT).show();
		
		RelativeLayout parent = (RelativeLayout)v.getParent();
		int position = getListView().getPositionForView(parent);
		
		AlarmModel item = (AlarmModel) getListView().getItemAtPosition(position);
		item.setListPosition(position);
		TimePickerFragment timePicker = new TimePickerFragment();
		timePicker.setAlarm(item);
		timePicker.show(getFragmentManager(), "timePicker");
	}
	
	public void setAlarmLabel(View v) {
		Toast.makeText(getApplicationContext(), "Change label", Toast.LENGTH_SHORT).show();
		LabelDialog labelDialog = new LabelDialog();
		
		RelativeLayout parent = (RelativeLayout)v.getParent();
		int position = getListView().getPositionForView(parent);
		
		AlarmModel alarm = (AlarmModel) getListView().getItemAtPosition(position);
		alarm.setListPosition(position);
		
		labelDialog.setAlarm(alarm);
		labelDialog.show(getFragmentManager(), "labelDialog");
	}
	
    // listen for time picker changes
	@Override
	public void onComplete(AlarmModel alarm) {
		Log.d("CheckYourself", "Called from alarm view");
		Log.d("CheckYourself", Integer.toString(alarm.getHour()) + " " + Integer.toString(alarm.getMinute()));
		// save changes
		// update view
		TextView time = (TextView) getListView().getChildAt((int) alarm.getListPosition()).findViewById(R.id.time);
		time.setText(alarm.getTimeString());

		// save to db in background
		AlarmsDataSource datasource = new AlarmsDataSource(this);
		datasource.open();
		datasource.updateAlarm(alarm);
		datasource.close();
		
		// clear all alarms and reset
		alarmReceiver.cancelAll();
		alarmReceiver.setAlarm(getApplicationContext());
	}
	
	public void deleteAlarm(View v) {
		TextView button = (TextView) v;
		RelativeLayout parent = (RelativeLayout)v.getParent();
		int position = getListView().getPositionForView(parent);
		AlarmModel item = (AlarmModel) getListView().getItemAtPosition(position);
		
		Toast.makeText(getApplicationContext(), button.getText().toString(), Toast.LENGTH_SHORT).show();
		
		// delete alarm
		AlarmsDataSource datasource = new AlarmsDataSource(this);
		datasource.open();
		datasource.deleteAlarm(item);
		
		// refresh list
		List<AlarmModel> modelVals = datasource.getAll();
		datasource.close();

		AlarmAdapter adapter = new AlarmAdapter(this, R.layout.alarm_list, modelVals);
		setListAdapter(adapter);
		
		// reset alarms
		alarmReceiver.cancelAll();
		alarmReceiver.setAlarm(getApplicationContext());
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem) {
		onBackPressed();
		return true;
	}


	@Override
	public void onDialogPositiveClick(DialogFragment dialog, AlarmModel alarm, String newLabel) {
		// update list
		TextView alarmLabel = (TextView) getListView().getChildAt((int) alarm.getListPosition()).findViewById(R.id.label);
		alarmLabel.setText(newLabel);

		// save to db in background
		alarm.setLabel(newLabel);
		AlarmsDataSource datasource = new AlarmsDataSource(this);
		datasource.open();
		datasource.updateAlarm(alarm);
		datasource.close();
		
		// clear all alarms and reset
		alarmReceiver.cancelAll();
		alarmReceiver.setAlarm(getApplicationContext());
	}


	@Override
	public void onDialogNegativeClick(DialogFragment dialog) {
		dialog.dismiss();
	}
}
