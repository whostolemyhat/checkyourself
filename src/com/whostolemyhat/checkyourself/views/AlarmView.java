package com.whostolemyhat.checkyourself.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whostolemyhat.checkyourself.R;


public class AlarmView extends Fragment {
	
//	AlarmReceiver alarm = new AlarmReceiver();
////	AlarmManager alarmManager;
//	private TextView alarmTime;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.alarm_list, container, false);
		
//      alarmTime = (TextView) getView().findViewById(R.id.alarm_time);
//      
//      // set all alarms
//      Button setAlarm = (Button) getView().findViewById(R.id.setAlarm);
//      setAlarm.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				alarm.setAlarm(getActivity());
//			}
//		});
      
      
      
//      Button dialog = (Button) rootView.findViewById(R.id.dialog);
//      dialog.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				DateTimeDialogFragment newFrag = new DateTimeDialogFragment(AlarmView.this);
//				newFrag.show(getFragmentManager(), "timepicker");
//				
//				Intent intent = new Intent(AlarmView.this, AlarmService.class);
//				PendingIntent pendingIntent = PendingIntent.getService(AlarmView.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//				AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
//				alarmManager.set(AlarmManager.RTC_WAKEUP, newFrag.getDateTimeMillis(), pendingIntent);
//				
//				Log.d("Check Yourself", newFrag.getDateTime());
//			}
//		});

//      
//      Button setNotification = (Button) getView().findViewById(R.id.set_notification);
//      setNotification.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				Intent intent = new Intent(getActivity(), AlarmService.class);
//				PendingIntent pendingIntent = PendingIntent.getService(getActivity(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//				
//				
//				Calendar calendar = Calendar.getInstance();
//				calendar.setTimeInMillis(System.currentTimeMillis());
//				// set alarm one hour from now
////				calendar.add(Calendar.HOUR, 1);
////				calendar.add(Calendar.MINUTE, 1);
//				calendar.add(Calendar.SECOND, 30);
//				AlarmManager alarmManager = (AlarmManager)getActivity().getApplicationContext().getSystemService(Service.ALARM_SERVICE);
//				alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
//				Log.d("Check yourself", calendar.getTime().toString());
//				// trigger notification
//				Toast.makeText(getActivity(), "Reminder set", Toast.LENGTH_LONG).show();
//				alarmTime.setText("Next reminder: " + DateFormat.getTimeFormat(getActivity()).format(calendar.getTime()));
//			}
//		}); 
//		
		return rootView;
	}
}
