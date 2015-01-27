package com.example.groupfinder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class GroupList extends Activity implements View.OnClickListener,
		DialogInterface.OnClickListener {

	private ListView mainListView;
	private Planet[] planets;
	private ArrayAdapter<Planet> listAdapter;
	Planet planet;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.groups_list);

		// Find the ListView resource.
		mainListView = (ListView) findViewById(R.id.mainListView);

		// When item is tapped, toggle checked properties of CheckBox and
		// Planet.
		mainListView
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View item,
							int position, long id) {
						planet = listAdapter.getItem(position);
						planet.toggleChecked();
						PlanetViewHolder viewHolder = (PlanetViewHolder) item
								.getTag();
						viewHolder.getCheckBox().setChecked(planet.isChecked());
					}
				});

		mainListView
				.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

					@Override
					public boolean onItemLongClick(AdapterView<?> parent,
							View item, int position, long id) {
						// TODO Auto-generated method stub
						AlertDialog ad = new AlertDialog.Builder(GroupList.this)
								.setMessage(
										"Purpose : Exam Study Group! \n Member Count : 12 \n Time : 6:00 PM \n Day : Wednesday")
								.setIcon(R.drawable.ic_launcher)
								.setTitle("CSC 541 - Exam Study")
								.setPositiveButton("OK",
										(OnClickListener) GroupList.this)
								// .setNegativeButton("Back", (OnClickListener)
								// GroupList.this)
								// .setNeutralButton("Cancel", (OnClickListener)
								// GroupList.this)
								.setCancelable(false).create();

						ad.show();
						// onItemClick(dialog, which, item, position);
						return true;
					}

				});

		// Create and populate planets.
		planets = (Planet[]) getLastNonConfigurationInstance();
		if (planets == null) {
			planets = new Planet[] {
					new Planet("CSC 541 - Group 1"),
					new Planet("CSC 540 - DataBase Basics Brush-Up"),
					new Planet("CSC 540 - Mid Term Group Study"),
					new Planet("CSC 540 - Project Ideas Proposal"),
					new Planet("CSC 540 - Flash Puzzles"),
					new Planet("CSC 541 - Pre-Requisites discussion"),
					new Planet("CSC 541 - In class notes discussion"),
					new Planet("CSC 541 - Memory Indexing brush-up"),
					new Planet("CSC 541 - Hashing and B-Trees"),
					new Planet("CSC 541 - Searching quick go through"),
					new Planet("CSC 541 - Storage types discussion"),
					new Planet("CSC 542 - Basics of Operating Systems"),
					new Planet(
							"CSC 542 - In-memory processing vs out-of-memory processing"),
					new Planet("CSC 542 - Memory Management"),
					new Planet("CSC 542 - All about UNIX"),
					new Planet("CSC 543 - Fracure of Items"),
					new Planet("CSC 543 - Mechanism to prevent fractures"),
					new Planet("CSC 543 - Ellipsoidal Hypothesis") };
		}
		ArrayList<Planet> planetList = new ArrayList<Planet>();
		planetList.addAll(Arrays.asList(planets));

		// Set our custom array adapter as the ListView's adapter.
		listAdapter = new PlanetArrayAdapter(this, planetList);
		mainListView.setAdapter(listAdapter);

		// Button to jump back to student home page
		Button btnBack = (Button) findViewById(R.id.btnBack);
		btnBack.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(GroupList.this, StudentActivity.class);
				Toast.makeText(getApplicationContext(),
						"You have been successfully added to the groups!",
						Toast.LENGTH_LONG).show();
				startActivity(i);
			}
		});

		// Button to jump back to student home page
		Button btnBack1 = (Button) findViewById(R.id.btnBackJoin);
		btnBack1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(GroupList.this, StudentActivity.class);
				startActivity(i);
			}
		});
	}

	/** Holds planet data. */
	private static class Planet {
		private String name = "";
		private boolean checked = false;

		public Planet() {
		}

		public Planet(String name) {
			this.name = name;
		}

		public Planet(String name, boolean checked) {
			this.name = name;
			this.checked = checked;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public boolean isChecked() {
			return checked;
		}

		public void setChecked(boolean checked) {
			this.checked = checked;
		}

		public String toString() {
			return name;
		}

		public void toggleChecked() {
			checked = !checked;
		}
	}

	/** Holds child views for one row. */
	private static class PlanetViewHolder {
		private CheckBox checkBox;
		private TextView textView;

		public PlanetViewHolder() {
		}

		public PlanetViewHolder(TextView textView, CheckBox checkBox) {
			this.checkBox = checkBox;
			this.textView = textView;
		}

		public CheckBox getCheckBox() {
			return checkBox;
		}

		public void setCheckBox(CheckBox checkBox) {
			this.checkBox = checkBox;
		}

		public TextView getTextView() {
			return textView;
		}

		public void setTextView(TextView textView) {
			this.textView = textView;
		}
	}

	/** Custom adapter for displaying an array of Planet objects. */
	private static class PlanetArrayAdapter extends ArrayAdapter<Planet> {

		private LayoutInflater inflater;

		public PlanetArrayAdapter(Context context, List<Planet> planetList) {
			super(context, R.layout.simplerow, R.id.rowTextView, planetList);
			// Cache the LayoutInflate to avoid asking for a new one each time.
			inflater = LayoutInflater.from(context);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// Planet to display
			Planet planet = (Planet) this.getItem(position);

			// The child views in each row.
			CheckBox checkBox;
			TextView textView;

			// Create a new row view
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.simplerow, null);

				// Find the child views.
				textView = (TextView) convertView
						.findViewById(R.id.rowTextView);
				checkBox = (CheckBox) convertView.findViewById(R.id.CheckBox01);

				// Optimization: Tag the row with it's child views, so we don't
				// have to
				// call findViewById() later when we reuse the row.
				convertView.setTag(new PlanetViewHolder(textView, checkBox));

				// If CheckBox is toggled, update the planet it is tagged with.
				checkBox.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						CheckBox cb = (CheckBox) v;
						Planet planet = (Planet) cb.getTag();
						planet.setChecked(cb.isChecked());
					}
				});
			}
			// Reuse existing row view
			else {
				// Because we use a ViewHolder, we avoid having to call
				// findViewById().
				PlanetViewHolder viewHolder = (PlanetViewHolder) convertView
						.getTag();
				checkBox = viewHolder.getCheckBox();
				textView = viewHolder.getTextView();
			}

			// Tag the CheckBox with the Planet it is displaying, so that we can
			// access the planet in onClick() when the CheckBox is toggled.
			checkBox.setTag(planet);

			// Display planet data
			checkBox.setChecked(planet.isChecked());
			textView.setText(planet.getName());

			return convertView;
		}

	}

	public Object onRetainNonConfigurationInstance() {
		return planets;
	}

	@Override
	public void onClick(DialogInterface arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}
}