package models;

public class AlarmModel {
	private long id = 0;
	private int hour;
	private int minute;
	private String label;

	
	public AlarmModel(int hour, int minute, String label) {
		this.setHour(hour);
		this.minute = minute;
		this.label = label;
	}
	
	public AlarmModel() {
		this(0, 0, "New Alarm");
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}
	
	public int getMinute() {
		return minute;
	}
	
	public void setMinute(int minute) {
		this.minute = minute;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
}
