package com.whostolemyhat.checkyourself.data;

import java.util.List;

import models.AlarmModel;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.whostolemyhat.checkyourself.R;

public class AlarmAdapter extends ArrayAdapter<AlarmModel> {
	private final Context context;
	int resource;
	
	public AlarmAdapter(Context context, int resource, List<AlarmModel> items) {
		super(context, resource, items);
		this.context = context;
		this.resource = resource;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView;
		AlarmModel alarm = (AlarmModel) getItem(position);
		
		if(convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView = inflater.inflate(resource, parent, false);
	
		} else {
			rowView = convertView;
		}
		
		TextView time = (TextView) rowView.findViewById(R.id.time);		
		time.setText(alarm.getTimeString());
		time.setClickable(true);
		time.setFocusable(true);
		
		TextView label = (TextView) rowView.findViewById(R.id.label);
		label.setText(alarm.getLabel());
		label.setClickable(true);
		label.setFocusable(true);
		
		return rowView;
	}
}
