package com.whostolemyhat.checkyourself.views;

import models.AlarmModel;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.whostolemyhat.checkyourself.R;

public class LabelDialog extends DialogFragment {
	AlarmModel alarm;
	LabelDialogListener listener;
	
	public interface LabelDialogListener {
		public void onDialogPositiveClick(DialogFragment dialog, AlarmModel alarm, String newLabel);
		public void onDialogNegativeClick(DialogFragment dialog);
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			listener = (LabelDialogListener) activity;
		} catch(ClassCastException e) {
			throw new ClassCastException(activity.toString() + " must implement LabelDialogListener"); 
		}
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflator = getActivity().getLayoutInflater();
		final View dialogView = inflator.inflate(R.layout.label_dialog, null);
		final EditText label = (EditText) dialogView.findViewById(R.id.alarmLabel);
		label.setText(alarm.getLabel());
		
		builder.setView(dialogView)
				.setPositiveButton("Save", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(getActivity(), "Saving", Toast.LENGTH_SHORT).show();
						listener.onDialogPositiveClick(LabelDialog.this, alarm, label.getText().toString());
					}
				})
				.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						listener.onDialogNegativeClick(LabelDialog.this);						
					}
				});
		return builder.create();
	}
	
	public void setAlarm(AlarmModel alarm) {
		this.alarm = alarm;
	}
}
