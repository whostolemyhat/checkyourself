package com.whostolemyhat.checkyourself;

import java.util.ArrayList;
import java.util.Calendar;
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
		String name = intent.getStringExtra("alarmLabel");
		if(name == null || name.isEmpty()) {
			name = "right now";
		}
		Log.d("CheckYourself", name);
		Intent service = new Intent(context, ScheduleService.class);
		service.putExtra("alarmLabel", name);
		Log.d("CheckYourself", "onReceive " + name);
		
		startWakefulService(context, service);
	}
	
	public void setAlarm(Context context) {
		Toast.makeText(context, "Alarms set", Toast.LENGTH_SHORT).show();
		
		AlarmsDataSource datasource = new AlarmsDataSource(context);
		datasource.open();
		List<AlarmModel> alarms = datasource.getAll();		
		datasource.close();
		
		alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		int id = 12345;
		
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
			alarmIntent.putExtra("alarmLabel", alarm.getLabel().toString());
			
			PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(context,
					id,
					alarmIntent,
					PendingIntent.FLAG_UPDATE_CURRENT);
			
			intentArray.add(alarmPendingIntent);
			
			alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
					alarmMillis,
					AlarmManager.INTERVAL_DAY,
					alarmPendingIntent);
			id++;
		}

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
	public void cancelAll() {
		for(int i = 0; i < intentArray.size(); i++) {
			alarmManager.cancel(intentArray.get(i));
		}
	}

}
