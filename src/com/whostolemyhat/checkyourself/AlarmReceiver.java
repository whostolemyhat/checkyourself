package com.whostolemyhat.checkyourself;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends WakefulBroadcastReceiver {
	
	private AlarmManager alarmManager;
	private PendingIntent alarmIntent;
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
		Log.d("CheckYourself", "Alarm set");
		
		// TODO: put this in a loop and get alarms/labels from an activity
		
		alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//		Intent intent = new Intent(context, AlarmReceiver.class);
//		alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
		
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTimeInMillis(System.currentTimeMillis());
		
		// hardcode to 8.30am
//		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR));
//		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + 1);
		// fire at approx 8:30am, repeat once a day
//		alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
//				calendar.getTimeInMillis(),
//				AlarmManager.INTERVAL_DAY,
//				alarmIntent);
		
        // breakfast alarm = 9:00am
        Calendar breakfast = Calendar.getInstance();
        breakfast.setTimeInMillis(System.currentTimeMillis());
        breakfast.set(Calendar.HOUR_OF_DAY, 9);
        breakfast.set(Calendar.MINUTE, 0);
        
        long breakfastMillis = breakfast.getTimeInMillis();
        if(breakfastMillis < System.currentTimeMillis()) {
        	breakfastMillis = breakfastMillis + INTERVAL_DAY;
        }
        Intent intent1 = new Intent(context, AlarmReceiver.class);
        intent1.putExtra("name", "breakfast");
        
        PendingIntent breakfastIntent = PendingIntent.getBroadcast(context, 12345, intent1, 0);
		alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
				breakfastMillis,
				AlarmManager.INTERVAL_DAY,
				breakfastIntent);
        
        // lunch alarm = 2:30pm
        Calendar lunch = Calendar.getInstance();
        lunch.setTimeInMillis(System.currentTimeMillis());
        lunch.set(Calendar.HOUR_OF_DAY, 14);
        lunch.set(Calendar.MINUTE, 30);
        
        long lunchMillis = lunch.getTimeInMillis();
       
        if(lunchMillis < System.currentTimeMillis()) {
        	lunchMillis = lunchMillis + INTERVAL_DAY;
        }
        
        Intent intent2 = new Intent(context, AlarmReceiver.class);
        intent2.putExtra("name", "lunch");
        
        PendingIntent lunchIntent = PendingIntent.getBroadcast(context, 22345, intent2, 0);
		alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
				lunchMillis,
				AlarmManager.INTERVAL_DAY,
				lunchIntent);
        
        // tea alarm = 8:15pm
        Calendar tea = Calendar.getInstance();
        tea.setTimeInMillis(System.currentTimeMillis());
        tea.set(Calendar.HOUR_OF_DAY, 20);
        tea.set(Calendar.MINUTE, 15);
        
    	long teaMillis = tea.getTimeInMillis();
        if(teaMillis < System.currentTimeMillis()) {
        	teaMillis = teaMillis + INTERVAL_DAY;
        }
        
        Intent intent3 = new Intent(context, AlarmReceiver.class);
        intent3.putExtra("name", "tea");
        
        PendingIntent teaIntent = PendingIntent.getBroadcast(context, 32345, intent3, 0);
        
		alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
				teaMillis,
				AlarmManager.INTERVAL_DAY,
				teaIntent);

		
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

}
