package com.example.groupfinder;

import java.util.Locale;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class LoginPage extends Activity {

	private TextToSpeech tts;
	Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		Button login = (Button) findViewById(R.id.button1);
		Button register = (Button) findViewById(R.id.button2);
		TextView forgot_password = (TextView) findViewById(R.id.forgot_password);
		forgot_password.setClickable(true);

		tts = new TextToSpeech(LoginPage.this,
				new TextToSpeech.OnInitListener() {

					@Override
					public void onInit(int status) {
						// TODO Auto-generated method stub
						if (status != TextToSpeech.ERROR) {
							tts.setLanguage(Locale.US);
						}
					}
				});

		login.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {

				// TODO Auto-generated method stub
				Intent i = new Intent(LoginPage.this, StudentActivity.class);
				//Intent i = new Intent(LoginPage.this, TabAdapter.class);
				startActivity(i);

				final String mess = "Welcome. Select one of your courses to view your groups ";
				tts.speak(mess, TextToSpeech.QUEUE_FLUSH, null);
			}
		});

		register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						context);
				// set title
				alertDialogBuilder.setTitle("Info");

				// set dialog message
				alertDialogBuilder
						.setMessage("Register Functionality")
						.setCancelable(false)
						.setPositiveButton("Ok",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										dialog.cancel();
									}
								}).show();
				Log.v("GroupFinder", "Button wher?");
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.student, menu);
		return true;
	}

}
