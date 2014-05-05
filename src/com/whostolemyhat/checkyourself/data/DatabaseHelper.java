package com.whostolemyhat.checkyourself.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
	public static final String TABLE_ALARMS = "alarms";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_HOUR = "hour";
	public static final String COLUMN_MINUTE = "minute";
	public static final String COLUMN_LABEL = "label";
	
	private static final String DATABASE_NAME = "alarms.db";
	private static final int DATABASE_VERSION = 1;
	
	// creation sql
	private static final String DATABASE_CREATE = "create table " +
			TABLE_ALARMS + "(" + 
			COLUMN_ID +	" integer primary key autoincrement, " +
			COLUMN_HOUR + " integer not null, " +
			COLUMN_MINUTE + " integer not null, " +
			COLUMN_LABEL + " text);";
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(DatabaseHelper.class.getName(), "Upgrading database");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALARMS);
		onCreate(db);
	}
}
