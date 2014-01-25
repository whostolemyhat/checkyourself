package com.whostolemyhat.checkyourself;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class AlarmService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		Toast.makeText(this, "AlarmService.onBind()", Toast.LENGTH_LONG).show();
		return null;
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		Toast.makeText(this, "AlarmService.onUnbind()", Toast.LENGTH_LONG).show();
		return super.onUnbind(intent);
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		
		Intent myIntent = new Intent(this, MainActivity.class);
		PendingIntent pendingIntent = 
				PendingIntent.getActivity(this, 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		
		
		Toast.makeText(this, "Alarm service start", Toast.LENGTH_LONG).show();
		
		Notification n = new Notification.Builder(this)
		.setContentTitle("Check Yourself")
		.setContentText("right now")
		.setSmallIcon(R.drawable.ic_launcher)
		.setContentIntent(pendingIntent)
		.setAutoCancel(true)
		.build();
		
		
		n.defaults |= Notification.DEFAULT_LIGHTS;
		n.defaults |= Notification.DEFAULT_SOUND;
		// pause delay ...
		n.vibrate = new long[]{100, 100, 100, 100, 100, 100, 100, 100};
		

	
		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		notificationManager.notify(0, n);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
	}
}
