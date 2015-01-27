package com.example.groupfinder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

public class AdhocFragmentActivity extends Fragment {
	Calendar myCalendar = Calendar.getInstance();
	TextView txtCal,txtTime;
	View adView;
	DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			myCalendar.set(Calendar.YEAR, year);
			myCalendar.set(Calendar.MONTH, monthOfYear);
			myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			String myFormat = "MM/dd/yy";
			SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
			txtCal.setText(sdf.format(myCalendar.getTime()));
		}

	};

	TimePickerDialog.OnTimeSetListener timePicker = new TimePickerDialog.OnTimeSetListener() {
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			// TODO Auto-generated method stub
			txtTime.setText(hourOfDay + ":" + minute);
		}
	};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		adView = inflater.inflate(R.layout.activity_adhoc_fragment, container,false);
		txtCal = (TextView) adView.findViewById(R.id.txtadhocDate);
		txtCal.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DatePickerDialog(adView.getContext(), date, myCalendar
						.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
						myCalendar.get(Calendar.DAY_OF_MONTH)).show();

			}
		});
		
		txtTime = (TextView) adView.findViewById(R.id.txtadhocTime);
		txtTime.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new TimePickerDialog(adView.getContext(), timePicker, myCalendar.get(Calendar.HOUR_OF_DAY),
						myCalendar.get(Calendar.MINUTE),false).show();
			}
		});
		Log.v("GroupFinder", "Inside RegFragJava");
		return adView;

	}
}
