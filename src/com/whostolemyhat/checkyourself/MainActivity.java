package com.whostolemyhat.checkyourself;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
	
	private TextView alarmTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
//        Intent i = getIntent();
//        boolean clearAlarm = i.getBooleanExtra("clearAlarm", false);
        
        setContentView(R.layout.activity_main);
        
        alarmTime = (TextView) findViewById(R.id.alarm_time);
        
        Button dialog = (Button) findViewById(R.id.dialog);
        dialog.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				DateTimeDialogFragment newFrag = new DateTimeDialogFragment(MainActivity.this);
				newFrag.show(getFragmentManager(), "timepicker");
				
				Intent intent = new Intent(MainActivity.this, AlarmService.class);
				PendingIntent pendingIntent = PendingIntent.getService(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
				AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
				alarmManager.set(AlarmManager.RTC_WAKEUP, newFrag.getDateTimeMillis(), pendingIntent);
				
				Log.d("Check Yourself", newFrag.getDateTime());
			}
		});

        
        Button setNotification = (Button) findViewById(R.id.set_notification);
        setNotification.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, AlarmService.class);
				PendingIntent pendingIntent = PendingIntent.getService(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
				
				AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(System.currentTimeMillis());
				// set alarm one hour from now
				calendar.add(Calendar.HOUR, 1);
//				calendar.add(Calendar.MINUTE, 1);
//				calendar.add(Calendar.SECOND, 10);
				alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
				Log.d("Check yourself", calendar.getTime().toString());
				// trigger notification
				Toast.makeText(MainActivity.this, "Reminder set", Toast.LENGTH_LONG).show();
				alarmTime.setText("Next reminder: " + DateFormat.getTimeFormat(getApplicationContext()).format(calendar.getTime()));
			}
		});   
        
    }
//    
//    @Override
//    public void onStart() {
//    	super.onStart();
//    	Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
//    }
//    
//    @Override
//    public void onRestart() {
//    	super.onRestart();
//    	Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
//    }
//    
//    @Override
//    public void onResume() {
//    	super.onResume();
//    	Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
//    }


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    
}
