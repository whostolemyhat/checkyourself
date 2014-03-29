package com.whostolemyhat.checkyourself.views;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.whostolemyhat.checkyourself.R;


public class AlarmView extends Fragment  {
	
//	AlarmReceiver alarm = new AlarmReceiver();
////	AlarmManager alarmManager;
//	private TextView alarmTime;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.alarm_list, container, false);
		
		final EditText label = (EditText) rootView.findViewById(R.id.label);
		
		final TextView time = (TextView) rootView.findViewById(R.id.time);
		time.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), "Changing the time", Toast.LENGTH_SHORT).show();
				
//				DateTimeDialogFragment newFrag = new DateTimeDialogFragment(getActivity());
//				newFrag.show(getActivity().getFragmentManager(), "timepicker");
				
//				Log.d("Check Yourself", newFrag.getDateTime());
				
				DialogFragment newFragment = new TimePickerFragment();
				newFragment.show(getActivity().getFragmentManager(), "timePicker");
			}
		});
		
		Button save = (Button) rootView.findViewById(R.id.save);
		save.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d("CheckYourself", label.getText().toString() + " " + time.getText().toString());
//				AlarmModel alarm = new AlarmModel();
			}
		});
		
		return rootView;
	}

}
