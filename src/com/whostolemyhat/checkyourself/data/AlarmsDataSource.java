package com.whostolemyhat.checkyourself.data;

import java.util.ArrayList;
import java.util.List;

import models.AlarmModel;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class AlarmsDataSource {
	private SQLiteDatabase database;
	private DatabaseHelper dbHelper;
	private String[] allColumns = {
			DatabaseHelper.COLUMN_ID,
			DatabaseHelper.COLUMN_HOUR,
			DatabaseHelper.COLUMN_MINUTE,
			DatabaseHelper.COLUMN_LABEL
	};
	
	public AlarmsDataSource(Context context) {
		dbHelper = new DatabaseHelper(context);
	}
	
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}
	
	public void close() {
		dbHelper.close();
	}
	
//	public AlarmModel createAlarm(int hour, int minute, String label) {
//		ContentValues values = new ContentValues();
//		values.put(DatabaseHelper.COLUMN_HOUR, hour);
//		values.put(DatabaseHelper.COLUMN_MINUTE, minute);
//		values.put(DatabaseHelper.COLUMN_LABEL, label);
//		
//		long insertId = database.insert(DatabaseHelper.TABLE_ALARMS, null, values);
//		Cursor cursor = database.query(DatabaseHelper.TABLE_ALARMS,
//				allColumns,
//				DatabaseHelper.COLUMN_ID + " = " + insertId,
//				null, 
//				null,
//				null,
//				null);
//	}
	
	public List<AlarmModel> getAll() {
		List<AlarmModel> alarms = new ArrayList<AlarmModel>();
		
		Cursor cursor = database.query(DatabaseHelper.TABLE_ALARMS, 
				allColumns,
				null, 
				null, 
				null,
				null,
				null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()) {
			AlarmModel alarm = cursorToAlarm(cursor);
			alarms.add(alarm);
			cursor.moveToNext();
		}
		cursor.close();
		
		return alarms;
	}
	
	public void deleteAlarm(AlarmModel alarm) {
		long id = alarm.getId();
		database.delete(DatabaseHelper.TABLE_ALARMS,
				DatabaseHelper.COLUMN_ID + " = " + id,
				null);
	}
	
	private AlarmModel cursorToAlarm(Cursor cursor) {
		AlarmModel alarm = new AlarmModel();
		alarm.setId(cursor.getLong(0));
		alarm.setHour(cursor.getInt(1));
		alarm.setMinute(cursor.getInt(2));
		alarm.setLabel(cursor.getString(3));
		
		return alarm;
	}
}
