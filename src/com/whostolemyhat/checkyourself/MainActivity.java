package com.whostolemyhat.checkyourself;

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
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.whostolemyhat.checkyourself.data.AlarmsDataSource;
import com.whostolemyhat.checkyourself.views.AlarmView;


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
    	
    	Toast.makeText(getApplicationContext(), "On resume", Toast.LENGTH_SHORT).show();
    	if(prefs.getBoolean("firstRun", true)) {
    		Log.d("CheckYourself", "First run");
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
    	
    	// if it's first run, set alarms
        
		// get from db
//		AlarmsDataSource datasource = new AlarmsDataSource(this);
//		datasource.open();
//		List<AlarmModel> alarms = datasource.getAll();
//		Log.d("CheckYourself", Integer.toString(alarms.size()));
		
        alarmTime = (TextView) findViewById(R.id.alarm_time);
        
        // set all alarms
        Button setAlarm = (Button) findViewById(R.id.setAlarm);
        setAlarm.setOnClickListener(new View.OnClickListener() {
  			
  			@Override
  			public void onClick(View v) {
  				alarm.setAlarm(MainActivity.this);
  			}
  		});
        
        Button setNotification = (Button) findViewById(R.id.set_notification);
        setNotification.setOnClickListener(new View.OnClickListener() {
  			
  			@Override
  			public void onClick(View v) {
  				Intent intent = new Intent(MainActivity.this, AlarmService.class);
  				PendingIntent pendingIntent = PendingIntent.getService(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
  				
  				
  				Calendar calendar = Calendar.getInstance();
  				calendar.setTimeInMillis(System.currentTimeMillis());
  				// set alarm one hour from now
  				calendar.add(Calendar.HOUR, 1);
  				calendar.add(Calendar.MINUTE, 1);
//    	  				calendar.add(Calendar.SECOND, 30);
  				AlarmManager alarmManager = (AlarmManager)getApplicationContext().getSystemService(Service.ALARM_SERVICE);
  				alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
  				Log.d("Check yourself", calendar.getTime().toString());
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
