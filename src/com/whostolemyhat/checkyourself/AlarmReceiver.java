package com.whostolemyhat.checkyourself;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import models.AlarmModel;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import android.widget.Toast;

import com.whostolemyhat.checkyourself.data.AlarmsDataSource;

public class AlarmReceiver extends WakefulBroadcastReceiver {
	
	private AlarmManager alarmManager;
	private PendingIntent alarmIntent;
	public static ArrayList<PendingIntent> intentArray = new ArrayList<PendingIntent>();
	public static final int INTERVAL_DAY = 24 * 60 * 60 * 1000;

	@Override
	public void onReceive(Context context, Intent intent) {
		String name = intent.getStringExtra("name");
		Intent service = new Intent(context, ScheduleService.class);
		service.putExtra("name", name);
		Log.d("CheckYourself", "onReceive " + name);
		
		startWakefulService(context, service);
	}
	
	public void setAlarm(Context context) {
		Toast.makeText(context, "Alarms set", Toast.LENGTH_SHORT).show();
		
		AlarmsDataSource datasource = new AlarmsDataSource(context);
		datasource.open();
		// TODO: for now, delete everything
		List<AlarmModel> alarms = datasource.getAll();
		for(AlarmModel alarm : alarms) {
			datasource.deleteAlarm(alarm);
		}
		
		// if first run, create alarms
		AlarmModel breakfast = new AlarmModel(9, 0, "Breakfast");
		AlarmModel lunch = new AlarmModel(23, 6, "Lunch test");
		AlarmModel tea = new AlarmModel(23, 8, "Tea2");
		
		datasource.createAlarm(breakfast);
		datasource.createAlarm(lunch);
		datasource.createAlarm(tea);
		// else read from db
		// clear all

		alarms = datasource.getAll();
		Log.d("CheckYourself", Integer.toString(alarms.size()));
		
		datasource.close();
		
		alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

		
		int id = 0;
		
		for(AlarmModel alarm: alarms) {
			Calendar c = Calendar.getInstance();
			c.set(Calendar.HOUR_OF_DAY, alarm.getHour());
			c.set(Calendar.MINUTE, alarm.getMinute());
			
			// make sure time is in the future
			long alarmMillis = c.getTimeInMillis();
			if(alarmMillis < System.currentTimeMillis()) {
				alarmMillis = alarmMillis + INTERVAL_DAY;
			}

			Intent alarmIntent = new Intent(context, AlarmReceiver.class);
			alarmIntent.putExtra("name", alarm.getLabel());
			
			PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(context,
					id,
					alarmIntent,
					0);
			
			intentArray.add(alarmPendingIntent);
			
			alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
					alarmMillis,
					AlarmManager.INTERVAL_DAY,
					alarmPendingIntent);
			id++;
			
			// debug
			Date alarmDate = new Date(alarmMillis);
			Date timeNow = new Date(System.currentTimeMillis());
			
			Log.d("CheckYourself", alarmDate.toString() + alarm.getLabel());
			Log.d("CheckYourself", timeNow.toString());
			Log.d("CheckYourself", Boolean.toString(System.currentTimeMillis() < alarmMillis));
		}
		
		boolean alarmUp = (PendingIntent.getBroadcast(context, 1, 
		        new Intent(context, AlarmReceiver.class), 
		        PendingIntent.FLAG_NO_CREATE) != null);

		if (alarmUp)
		{
		    Log.d("CheckYourself", "Alarm is already active");
		}
		
        // breakfast alarm = 9:00am
//        Calendar breakfast = Calendar.getInstance();
//        breakfast.setTimeInMillis(System.currentTimeMillis());
//        // get time and set calendar
//        breakfast.set(Calendar.HOUR_OF_DAY, 9);
//        breakfast.set(Calendar.MINUTE, 0);
//        
//        long breakfastMillis = breakfast.getTimeInMillis();
//        if(breakfastMillis < System.currentTimeMillis()) {
//        	breakfastMillis = breakfastMillis + INTERVAL_DAY;
//        }
//        Intent intent1 = new Intent(context, AlarmReceiver.class);
//        // get this label
//        intent1.putExtra("name", "breakfast");
//        
//        PendingIntent breakfastIntent = PendingIntent.getBroadcast(context, 12345, intent1, 0);
//		alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
//				breakfastMillis,
//				AlarmManager.INTERVAL_DAY,
//				breakfastIntent);
//        
//        // lunch alarm = 3:00pm
//        Calendar lunch = Calendar.getInstance();
//        lunch.setTimeInMillis(System.currentTimeMillis());
//        lunch.set(Calendar.HOUR_OF_DAY, 15);
//        lunch.set(Calendar.MINUTE, 0);
//        
//        long lunchMillis = lunch.getTimeInMillis();
//       
//        if(lunchMillis < System.currentTimeMillis()) {
//        	lunchMillis = lunchMillis + INTERVAL_DAY;
//        }
//        
//        Intent intent2 = new Intent(context, AlarmReceiver.class);
//        intent2.putExtra("name", "lunch");
//        
//        PendingIntent lunchIntent = PendingIntent.getBroadcast(context, 22345, intent2, 0);
//		alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
//				lunchMillis,
//				AlarmManager.INTERVAL_DAY,
//				lunchIntent);
//        
//        // tea alarm = 8:15pm
//        Calendar tea = Calendar.getInstance();
//        tea.setTimeInMillis(System.currentTimeMillis());
//        tea.set(Calendar.HOUR_OF_DAY, 20);
//        tea.set(Calendar.MINUTE, 15);
//        
//    	long teaMillis = tea.getTimeInMillis();
//        if(teaMillis < System.currentTimeMillis()) {
//        	teaMillis = teaMillis + INTERVAL_DAY;
//        }
//        
//        Intent intent3 = new Intent(context, AlarmReceiver.class);
//        intent3.putExtra("name", "tea");
//        
//        PendingIntent teaIntent = PendingIntent.getBroadcast(context, 32345, intent3, 0);
//        
//		alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
//				teaMillis,
//				AlarmManager.INTERVAL_DAY,
//				teaIntent);

		
		// restart alarm on reboot - code in bootreceiver
		ComponentName receiver = new ComponentName(context, BootReceiver.class);
		PackageManager pm = context.getPackageManager();
		
		pm.setComponentEnabledSetting(receiver, 
				PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
				PackageManager.DONT_KILL_APP);
	}
	
	public void cancelAlarm(Context context) {
		if(alarmManager != null) {
			alarmManager.cancel(alarmIntent);
			
//			disable bootreceiver so it doesn't get restarted
			ComponentName receiver = new ComponentName(context, BootReceiver.class);
			PackageManager pm = context.getPackageManager();
			
			pm.setComponentEnabledSetting(receiver,
					PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
					PackageManager.DONT_KILL_APP);
		}
	}
	
	// cancel all alarms
	public void cancelAll(Context context) {
		for(int i = 0; i < intentArray.size(); i++) {
			alarmManager.cancel(intentArray.get(i));
		}
	}

}
