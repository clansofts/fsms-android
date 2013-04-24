package com.simlab.frontlinesms.activities;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockPreferenceActivity;
import com.actionbarsherlock.view.MenuItem;
import com.simlab.frontlinesms.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;

public class SettingsActivity extends SherlockPreferenceActivity implements
		OnSharedPreferenceChangeListener {
	public static final String RUN_BACKGROUND = "pref_running";
	static final int RUNNING_NOTIFICATION_ID = 1001;

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preference);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		getPreferenceScreen().getSharedPreferences()
				.registerOnSharedPreferenceChangeListener(this);
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

	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		if (key.equals(RUN_BACKGROUND)) {
			if (sharedPreferences.getBoolean(key, false)) {
				addRunningNotification("FrontlineSMS is running", "All received messages will be analysed by FrontlineSMS");
			} else {
				removeRunningNotification();
			}
		}
	}

	public void showRunningNotification() {
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		boolean runBackgroundPref = sharedPref.getBoolean(SettingsActivity.RUN_BACKGROUND, false);
		if(runBackgroundPref) {
			addRunningNotification("FrontlineSMS is running", "All received messages will be analysed by FrontlineSMS");
		} else {
			removeRunningNotification();
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onResume() {
		super.onResume();
		getPreferenceScreen().getSharedPreferences()
				.registerOnSharedPreferenceChangeListener(this);
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onPause() {
		super.onPause();
		getPreferenceScreen().getSharedPreferences()
				.unregisterOnSharedPreferenceChangeListener(this);
	}
	
	private void removeRunningNotification(){
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.cancel(RUNNING_NOTIFICATION_ID);
	}

	private void addRunningNotification(String title, String content) {
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this).setSmallIcon(R.drawable.ic_launcher).setContentTitle(title).setContentText(content);
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		Notification n = mBuilder.build();
		n.flags = Notification.FLAG_ONGOING_EVENT;
		mNotificationManager.notify(RUNNING_NOTIFICATION_ID, n);
	}
}