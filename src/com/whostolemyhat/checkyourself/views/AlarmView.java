package com.whostolemyhat.checkyourself.views;

import java.util.ArrayList;

import models.AlarmModel;
import android.app.ActionBar;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.whostolemyhat.checkyourself.AlarmAdapter;
import com.whostolemyhat.checkyourself.R;


public class AlarmView extends ListActivity implements TimePickerFragment.OnCompleteListener {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alarm_list_view);
		
		// read models in from storage		
		AlarmModel breakfast = new AlarmModel(9, 0, "Breakfast");
		AlarmModel lunch = new AlarmModel(15, 0, "Lunch");
		AlarmModel tea = new AlarmModel(20, 15, "Tea");
		
		ArrayList<AlarmModel> modelVals = new ArrayList<AlarmModel>();
		
		modelVals.add(breakfast);
		modelVals.add(lunch);
		modelVals.add(tea);

		AlarmAdapter adapter = new AlarmAdapter(this, R.layout.alarm_list, modelVals);
		setListAdapter(adapter);
		
		ActionBar actionBar = getActionBar();
		actionBar.setHomeButtonEnabled(true);
		
//		TextView time = (TextView) findViewById(R.id.time);
//		time.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				DialogFragment timePicker = new TimePickerFragment();
//				timePicker.show(getFragmentManager(), "timePicker");
//			}
//		});
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		AlarmModel item = (AlarmModel) l.getItemAtPosition(position);
		
		Log.d("CheckYourself", "click" + item.getLabel() + Integer.toString(item.getHour()));
		
		TimePickerFragment timePicker = new TimePickerFragment();
		// TODO: implement set time in timefragment
		timePicker.setTime(item.getHour(), item.getMinute());
		timePicker.show(getFragmentManager(), "timePicker");
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem) {
		onBackPressed();
		return true;
	}
	
    // listen for time picker changes
	@Override
	public void onComplete(int hour, int minute) {
		Log.d("CheckYourself", "Called from alarm view");
		Log.d("CheckYourself", Integer.toString(hour) + " " + Integer.toString(minute));
		// save changes
	}

}
