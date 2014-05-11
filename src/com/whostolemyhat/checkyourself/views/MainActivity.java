package com.whostolemyhat.checkyourself.views;

import java.util.Calendar;

import models.AlarmModel;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.whostolemyhat.checkyourself.AlarmReceiver;
import com.whostolemyhat.checkyourself.R;
import com.whostolemyhat.checkyourself.ScheduleService;
import com.whostolemyhat.checkyourself.R.id;
import com.whostolemyhat.checkyourself.R.layout;
import com.whostolemyhat.checkyourself.R.menu;
import com.whostolemyhat.checkyourself.data.AlarmsDataSource;


public class MainActivity extends Activity {
	AlarmReceiver alarm = new AlarmReceiver();
	private TextView alarmTime;
	SharedPreferences prefs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button_screen);
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	prefs = getSharedPreferences("com.whostolemyhat.checkyourself", MODE_PRIVATE);

    	if(prefs.getBoolean("firstRun", true)) {
    		prefs.edit().putBoolean("firstRun", false).commit();
    		
    		AlarmModel breakfast = new AlarmModel(9, 0, "Breakfast");
    		AlarmModel lunch = new AlarmModel(15, 30, "Lunch");
    		AlarmModel tea = new AlarmModel(20, 15, "Tea");
    		
    		AlarmsDataSource datasource = new AlarmsDataSource(getApplicationContext());
    		datasource.open();
    		datasource.createAlarm(breakfast);
    		datasource.createAlarm(lunch);
    		datasource.createAlarm(tea);
    		
    		datasource.close();
    	}

		
        alarmTime = (TextView) findViewById(R.id.alarm_time);
        
        // set all alarms
//        Button setAlarm = (Button) findViewById(R.id.setAlarm);
//        setAlarm.setOnClickListener(new View.OnClickListener() {
//  			
//  			@Override
//  			public void onClick(View v) {
//  				alarm.setAlarm(MainActivity.this);
//  			}
//  		});
        
        Button setNotification = (Button) findViewById(R.id.set_notification);
        setNotification.setOnClickListener(new View.OnClickListener() {
  			
  			@Override
  			public void onClick(View v) {
  				Intent intent = new Intent(MainActivity.this, ScheduleService.class);
  				PendingIntent pendingIntent = PendingIntent.getService(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
  				
  				
  				Calendar calendar = Calendar.getInstance();
  				calendar.setTimeInMillis(System.currentTimeMillis());
  				// set alarm one hour from now
//  				calendar.add(Calendar.HOUR, 1);
  				calendar.add(Calendar.MINUTE, 1);
  				AlarmManager alarmManager = (AlarmManager)getApplicationContext().getSystemService(Service.ALARM_SERVICE);
  				alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

  				// trigger notification
  				Toast.makeText(MainActivity.this, "Reminder set", Toast.LENGTH_LONG).show();
  				alarmTime.setText("Next reminder: " + DateFormat.getTimeFormat(getApplicationContext()).format(calendar.getTime()));
  			}
  		});
        
        Button setAlarms = (Button)findViewById(R.id.change);
        setAlarms.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, AlarmView.class);
				startActivity(i);
			}
		});
    }


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


}
