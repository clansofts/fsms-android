package com.simlab.frontlinesms;

import net.simonvt.menudrawer.MenuDrawer;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.simlab.frontlinesms.activities.AutoforwardEditActivity;
import com.simlab.frontlinesms.activities.AutoreplyEditActivity;
import com.simlab.frontlinesms.activities.FactivitiesListActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends SherlockActivity implements OnClickListener {
	public static Context context;

	View menuOptionHome, menuOptionActivities, menuOptionSettings;
	View autoreplyOption;
	View autoforwardOption;
	MenuDrawer mDrawer;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MainActivity.context = getApplicationContext();
		setContentView(R.layout.main_sections);

		mDrawer = MenuDrawer.attach(this);
		mDrawer.setContentView(R.layout.main_sections);
		mDrawer.setMenuView(R.layout.main_slider_menu);

		menuOptionHome = findViewById(R.id.menu_option_home);
		menuOptionActivities = findViewById(R.id.menu_option_activities);
		menuOptionSettings = findViewById(R.id.menu_option_settings);

		autoreplyOption = findViewById(R.id.autoreply_option);
		autoforwardOption = findViewById(R.id.autoforward_option);

		autoreplyOption.setOnClickListener(this);
		autoforwardOption.setOnClickListener(this);
		menuOptionHome.setOnClickListener(this);
		menuOptionActivities.setOnClickListener(this);
		menuOptionSettings.setOnClickListener(this);
	}

	public void onClick(View v) {
		Context currentContext = getApplicationContext();
		switch (v.getId()) {
		case R.id.autoreply_option: {
			Toast.makeText(currentContext, "Opening Autoreply",
					Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(getApplicationContext(),
					AutoreplyEditActivity.class);
			startActivity(intent);
			break;
		}

		case R.id.autoforward_option: {
			Toast.makeText(currentContext, "Opening Autoforward",
					Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(getApplicationContext(),
					AutoforwardEditActivity.class);
			startActivity(intent);
			break;
		}

		case R.id.poll_option: {
			Toast.makeText(currentContext, "Opening Poll", Toast.LENGTH_SHORT)
					.show();
			break;
		}
		// Handling for slide menu options
		case R.id.menu_option_activities: {
			Intent intent = new Intent(getApplicationContext(),
					FactivitiesListActivity.class);
			startActivity(intent);
			break;
		}

		default:
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.main_section_menu, menu);
		return true;
	}
}