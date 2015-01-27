package com.example.groupfinder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.Telephony.Sms.Conversations;

import java.text.DateFormat;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.TextView;
import android.widget.Toast;

public class ViewGroup extends Activity implements OnItemClickListener,
		OnClickListener {

	@SuppressWarnings("deprecation")
	private SlidingDrawer slider;
	private Button handle;
	ArrayList<String> values = new ArrayList<String>();
	ListView lstNotifications;
	ArrayAdapter<String> lstAdapter;
	String grpName;
	TextView txtNotify;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_group);
		Log.v("GroupFinder", "Entering Group View!");
		Intent i = getIntent();
		grpName = i.getExtras().getString("GroupName");
		String courseName = i.getExtras().getString("CourseName");
		String schedule = DateFormat.getDateTimeInstance().format(new Date());
		String desc = "This group " + grpName + " is dedicated to" + courseName
				+ "for the time schedule: " + schedule
				+ ".\nEveryone is welcome to join!";
		Log.v("GroupFinder", "In View Group On create");
		TextView txtGrpName = (TextView) findViewById(R.id.txtGroupName);
		txtGrpName.setText(grpName);
		TextView txtCourseName = (TextView) findViewById(R.id.txtCourseName);
		txtCourseName.setText(courseName);
		TextView txtSchedule = (TextView) findViewById(R.id.txtSchedule);
		txtSchedule.setText(schedule);
		TextView txtDesc = (TextView) findViewById(R.id.txtGrpDesc);
		txtDesc.setText(desc);
		TextView txtPart = (TextView) findViewById(R.id.txtPart);
		txtPart.setText("Participant1\nParticipant2\nParticipant3\nParticipant4\n");

		handle = (Button) findViewById(R.id.handle1);
		slider = (SlidingDrawer) findViewById(R.id.slidingDrawer1);
		slider.setOnDrawerOpenListener(new OnDrawerOpenListener() {
			@Override
			public void onDrawerOpened() {
				TextView txtNotify = (TextView) findViewById(R.id.txtNotify);
				txtNotify.setBackgroundColor(getResources().getColor(
						R.color.gray));
				txtNotify.setTextColor(0xFFFFFFFF);
				txtNotify.setText(String.valueOf(0));
				Drawable image = getResources().getDrawable(
						R.drawable.notification_down);
				image.setBounds(0, 0, 60, 60);
				handle.setText("Close");
				handle.setCompoundDrawables(null, null, image, null);
			}
		});

		slider.setOnDrawerCloseListener(new OnDrawerCloseListener() {
			@Override
			public void onDrawerClosed() {
				Drawable image = getResources().getDrawable(
						R.drawable.notification_up);
				image.setBounds(0, 0, 60, 60);
				handle.setText("Notifications");
				handle.setCompoundDrawables(null, null, image, null);
			}
		});
		Log.v("GroupFinder", "Slider Set up!");
		lstNotifications = (ListView) findViewById(R.id.lstNotifications);
		// ListView lstNotifications =
		// (ListView)slider.findViewById(R.id.lstNotifications);
		String[] notifies = new String[] { "The group is created.",
				"First milestone reached!", "Mid Term Preparation!",
				"Milestone2",
				"Please prepare question banks for the next meet...",
				"Finals are here!", "Farewell Meet!!" };
		values.addAll(Arrays.asList(notifies));
		lstAdapter = new ArrayAdapter<String>(this,
				R.layout.notifications_view, R.id.row_notifications, values) {
			public View getView(int position, View convertView,
					android.view.ViewGroup parent) {
				View row = super.getView(position, convertView, parent);

				View btnDel = row.findViewById(R.id.btnDel);
				btnDel.setTag(position);
				btnDel.setOnClickListener(ViewGroup.this);
				return row;
			}
		};
		lstNotifications.setAdapter(lstAdapter);
		lstNotifications.setOnItemClickListener(this);

		Log.v("GroupFinder",
				"ListView inside slider created and button started listening onclick");
		txtNotify = (TextView) findViewById(R.id.txtNotify);
		txtNotify.setText(String.valueOf(values.size()));
		Log.v("GroupFinder", "Load TextView with number of notifications");

		// Join group jump to GroupList Page
		Button btnLeaveGroup = (Button) findViewById(R.id.leaveGroup);
		btnLeaveGroup.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(
						getApplicationContext(),
						"You have left the group \"" + grpName
								+ "\" successfully!", Toast.LENGTH_SHORT)
						.show();
				Intent i = new Intent(ViewGroup.this, StudentActivity.class);
				startActivity(i);
			}
		});

		// Join group jump to GroupList Page
		Button btnGoBack = (Button) findViewById(R.id.goBack);
		btnGoBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(ViewGroup.this, StudentActivity.class);
				startActivity(i);
			}
		});

		// Post updates on the group
		Button btnPost = (Button) findViewById(R.id.btnPost);
		btnPost.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TextView txtAdd = (TextView) findViewById(R.id.txtGrpNotify);
				String toAdd = txtAdd.getText().toString();
				if(!toAdd.isEmpty()){
					lstAdapter.add(toAdd);
					txtAdd.setText("");
					txtNotify.setText(String.valueOf(lstAdapter.getCount()));
					txtNotify.setTextColor(getResources().getColor(R.color.opaque_red));
					txtNotify.setBackgroundColor(0xFFFFFFFF);
					Log.v("GroupFinder", "Posted to List!");
				}else{
					Log.v("GroupFinder", "Post empty");
				}
			}
		});
	}

	public void onClick(View v) {

		Integer p = (Integer) v.getTag();

		Object toRemove = lstAdapter.getItem(p);
		lstAdapter.remove(String.valueOf(toRemove));
		Log.v("GroupFinder", String.valueOf(p) + " deleted from List!");
		Log.v("GroupFinder", String.valueOf(values.size())
				+ " is the size of values array");
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
	}

}