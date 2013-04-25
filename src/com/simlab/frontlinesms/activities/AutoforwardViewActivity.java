package com.simlab.frontlinesms.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.simlab.frontlinesms.R;

public class AutoforwardViewActivity extends SherlockActivity {
	TextView nameTextView, forwardTextTextView, keywordsTextView, recipientsTextView;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.view_autoforward);
        
        nameTextView = (TextView) findViewById(R.id.autoforward_name);
        forwardTextTextView = (TextView) findViewById(R.id.forward_text);
        keywordsTextView = (TextView) findViewById(R.id.autoforward_keywords);
        recipientsTextView = (TextView) findViewById(R.id.autoforward_recipients);
        
        Bundle bundle = this.getIntent().getExtras();
        String name = bundle.getString("name");
        String forwardText = bundle.getString("forwardText");
        String keywords = bundle.getString("keywords");
        String recipients = bundle.getString("recipients");
        
        nameTextView.append(" : " + name);
        forwardTextTextView.append(" : " + forwardText);
        keywordsTextView.append(" : " + keywords);
        recipientsTextView.append(" : " + recipients);
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
