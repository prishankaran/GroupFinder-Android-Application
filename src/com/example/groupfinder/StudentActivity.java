package com.example.groupfinder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

public class StudentActivity extends Activity {

	ExpandableListCustomAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		int height = size.y;

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_student);
		expListView = (ExpandableListView) findViewById(R.id.lstView);
		prepareListData();
		listAdapter = new ExpandableListCustomAdapter(this, listDataHeader,
				listDataChild);

		expListView.setAdapter(listAdapter);
		expListView.setIndicatorBoundsRelative(width - 80, width - 10);
		Log.v("GroupFinder", String.valueOf(width));
		Log.v("GroupFinder", String.valueOf(height));
		Log.v("GroupFinder", String.valueOf(expListView.getLeft()));

		expListView.setOnChildClickListener(new OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				Toast.makeText(
						getApplicationContext(),
						listDataHeader.get(groupPosition)
								+ " : "
								+ listDataChild.get(
										listDataHeader.get(groupPosition)).get(
										childPosition), Toast.LENGTH_SHORT)
						.show();
				Intent i = new Intent(StudentActivity.this, ViewGroup.class);
				i.putExtra("CourseName", listDataHeader.get(groupPosition));
				i.putExtra("GroupName",
						listDataChild.get(listDataHeader.get(groupPosition))
								.get(childPosition));
				startActivity(i);
				return false;
			}
		});

		// Join group jump to GroupList Page
		Button btnJoinGroup = (Button) findViewById(R.id.angry_btn);
		btnJoinGroup.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(StudentActivity.this, GroupList.class);
				startActivity(i);
			}
		});

		// Join group jump to GroupList Page
		Button btnCreateGroup = (Button) findViewById(R.id.button2);
		btnCreateGroup.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(StudentActivity.this, NewGroupActivity.class);
				startActivity(i);
			}
		});
	}

	private void prepareListData() {
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();

		// Adding child data
		listDataHeader.add("CSC 540-DBMS");
		listDataHeader.add("CSC 541-ADS");
		listDataHeader.add("CSC 542-OS");
		listDataHeader.add("CSC 543-Fracture Mechanics");

		// Adding child data
		List<String> csc1 = new ArrayList<String>();
		csc1.add("DataBase Basics Brush-Up");
		csc1.add("Mid Term Group Study");
		csc1.add("Project Ideas Proposal");
		csc1.add("Flash Puzzles");

		List<String> csc2 = new ArrayList<String>();
		csc2.add("Pre-Requisites discussion");
		csc2.add("In class notes discussion");
		csc2.add("Memory Indexing brush-up");
		csc2.add("Hashing and B-Trees");
		csc2.add("Searching quick go through");
		csc2.add("Storage types discussion");

		List<String> csc3 = new ArrayList<String>();
		csc3.add("Basics of Operating Systems");
		csc3.add("In-memory processing vs out-of-memory processing");
		csc3.add("Memory Management");
		csc3.add("All about UNIX");
		
		List<String> csc4 = new ArrayList<String>();
		csc4.add("Fracure of Items");
		csc4.add("Mechanism to prevent fractures");
		csc4.add("Ellipsoidal Hypothesis");

		listDataChild.put(listDataHeader.get(0), csc1);
		listDataChild.put(listDataHeader.get(1), csc2);
		listDataChild.put(listDataHeader.get(2), csc3);
		listDataChild.put(listDataHeader.get(3), csc4);
	}
}