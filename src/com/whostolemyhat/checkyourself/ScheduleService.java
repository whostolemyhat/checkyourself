package com.whostolemyhat.checkyourself;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class ScheduleService extends IntentService {
	public ScheduleService() {
		super("SchedulingService");
	}
	
	public static final String TAG = "Check Yourself Scheduler";
	// ID for notification
	public static final int NOTIFICATION_ID = 1;
	private NotificationManager notificationManager;
	NotificationCompat.Builder builder;

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.d("CheckYourself", "Service called");
		String name = intent.getStringExtra("name");
		// set notification
		sendNotification(String.format(getString(R.string.after_meal), name));
		
		Log.d("CheckYourself", "handleIntent " + name);
		
		// release wakelock
		AlarmReceiver.completeWakefulIntent(intent);
	}
	
	private void sendNotification(String msg) {
		Log.d("CheckYourself", "setting notification");
		
		notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
		
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				new Intent(this, MainActivity.class), 0);
		
		NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
			.setSmallIcon(R.drawable.ic_launcher)
			.setContentTitle(getString(R.string.app_name))
			.setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
			.setContentText(msg)
			.setVibrate(new long[]{100, 100, 100, 100, 100, 100, 100, 100});
		
		builder.setContentIntent(contentIntent);
		notificationManager.notify(NOTIFICATION_ID, builder.build());
	}
}