package com.simlab.frontlinesms;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.simlab.frontlinesms.activities.AutoforwardEditActivity;
import com.simlab.frontlinesms.activities.AutoreplyEditActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends SherlockActivity implements OnClickListener  {	
	public static Context context;
    
	View autoreplyOption;
	View autoforwardOption;
	
	@Override
    public void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MainActivity.context = getApplicationContext();
		setContentView(R.layout.main_sections);
		
		autoreplyOption = findViewById(R.id.autoreply_option);
		autoforwardOption = findViewById(R.id.autoforward_option);
		
		autoreplyOption.setOnClickListener(this);
		autoforwardOption.setOnClickListener(this);
    }
    
    public void onClick (View v) {
    	Context currentContext = getApplicationContext();
    	
		if(v == findViewById(R.id.autoreply_option)){
			Toast.makeText(currentContext, "Opening Autoreply", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(getApplicationContext(), AutoreplyEditActivity.class);
			startActivity(intent);
		}
		if(v == findViewById(R.id.autoforward_option)){
			Toast.makeText(currentContext, "Opening Autoforward", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(getApplicationContext(), AutoforwardEditActivity.class);
			startActivity(intent);
		}
		if(v == findViewById(R.id.poll_option)){
			Toast.makeText(currentContext, "Opening Poll", Toast.LENGTH_SHORT).show();
		}
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.main_section_menu, menu);
        return true;
    }
}