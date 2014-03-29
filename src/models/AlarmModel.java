package models;

public class AlarmModel {
	int hour;
	int minute;
	String label;
	
	public AlarmModel(int hour, int minute, String label) {
		this.hour = hour;
		this.minute = minute;
		this.label = label;
	}
}
