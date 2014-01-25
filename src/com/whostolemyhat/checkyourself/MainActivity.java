package com.whostolemyhat.checkyourself;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        Button setNotification = (Button) findViewById(R.id.set_notification);
        setNotification.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d("Check Yourself", "button clicked");
				
				
				Intent intent = new Intent(MainActivity.this, AlarmService.class);
				PendingIntent pendingIntent = PendingIntent.getService(MainActivity.this, 0, intent, 0);
				
				AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(System.currentTimeMillis());
				// set alarm one hour from now
				calendar.add(Calendar.HOUR, 1);
//				calendar.add(Calendar.SECOND, 10);
				alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
				
				// trigger notification
				Toast.makeText(MainActivity.this, "start alarm", Toast.LENGTH_LONG).show();
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
