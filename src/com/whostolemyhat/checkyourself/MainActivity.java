package com.whostolemyhat.checkyourself;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {
	
	private NotificationManager notificationManager;
	private Notification n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
		Intent intent = new Intent(this, MainActivity.class);
		PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		
		n = new Notification.Builder(this)
			.setContentTitle("Check Yourself")
			.setContentText("right now")
			.setSmallIcon(R.drawable.ic_launcher)
			.setContentIntent(pIntent)
			.setAutoCancel(true)
			.build();
		
		notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		
        
        Button setNotification = (Button) findViewById(R.id.set_notification);
        setNotification.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d("Check Yourself", "button clicked");
				
				notificationManager.notify(0, n);
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
