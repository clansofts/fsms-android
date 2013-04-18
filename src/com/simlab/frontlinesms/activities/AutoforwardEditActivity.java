package com.simlab.frontlinesms.activities;

import java.sql.SQLException;
import java.util.ArrayList;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.simlab.frontlinesms.R;
import com.simlab.frontlinesms.database.DatabaseHelper;
import com.simlab.frontlinesms.domains.Autoforward;

public class AutoforwardEditActivity extends SherlockActivity implements OnClickListener {
	final int CONTACT_PICKER_RESULT = 1001;
	
	String autoforwardId, name, forwardText, keywords, recipients;
	TextView nameTextField, forwardTextField, keywordsTextField, tempRecipients;
	View selectRecipientsButton;
	Button saveAutoforwardButton;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.edit_autoforward);
		
		selectRecipientsButton = findViewById(R.id.autoforward_recipients);
		saveAutoforwardButton = (Button) findViewById(R.id.autoforward_save_button);
		nameTextField = (TextView) findViewById(R.id.autoforward_name);
		forwardTextField = (TextView) findViewById(R.id.autoforward_forwardtext);
		keywordsTextField = (TextView) findViewById(R.id.autoforward_keywords);
		tempRecipients = (TextView) findViewById(R.id.temp_recipients);

		selectRecipientsButton.setOnClickListener(this);
		saveAutoforwardButton.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.autoforward_recipients: {
			Intent contactPickerIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
			startActivityForResult(contactPickerIntent, CONTACT_PICKER_RESULT);
			break;
		}
		case R.id.autoforward_save_button: {
			Autoforward autoforward = new Autoforward();
			autoforward.name = nameTextField.getText().toString();
			autoforward.forwardText = forwardTextField.getText().toString();
			autoforward.keywords = keywordsTextField.getText().toString();
			autoforward.recipents = this.recipients;
			try {
				new DatabaseHelper(getApplicationContext()).getAutoforwardDao().create(autoforward);
				Toast.makeText(getApplicationContext(), "Autoforward Saved :)", Toast.LENGTH_LONG).show();
				super.onBackPressed();
			} catch (SQLException e) {
				Log.e("SEMEV", "### Could not save autoforward");
				e.printStackTrace();
			}
		}
		default:
			break;
		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case CONTACT_PICKER_RESULT:
				Uri result = data.getData();
				String id = result.getLastPathSegment();
				Cursor cursor = getContentResolver().query(  
				        Phone.CONTENT_URI, null,  
				        Phone.CONTACT_ID + "=?", 
				        new String[]{id}, null);
				if (cursor.moveToFirst()) {
					int phoneIdx = cursor.getColumnIndex(Phone.DATA); 
					String mobileNumber = cursor.getString(phoneIdx);
					mobileNumber = mobileNumber.replaceAll("-", "");
					this.recipients += ","+mobileNumber;
					String b = tempRecipients.getText().toString();
					if(!b.contains(mobileNumber)) {
						tempRecipients.setText(b + "," + mobileNumber);
					}
					Log.v("SEMEV", "Got Phone Number # " + mobileNumber);
				} else {
					Toast.makeText(getApplicationContext(), "No Contact has been picked :(", Toast.LENGTH_LONG).show();
					Log.v("SEMEV", "No Contact has been picked :(");
				}
				Log.v("SEMEV", "Got a result: " + result.toString());
				break;
			}
		} else {
			Log.w("SEMEV", "Warning: activity result not ok");
		}
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			super.onBackPressed();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
