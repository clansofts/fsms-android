package com.simlab.frontlinesms.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.simlab.frontlinesms.R;

public class AutoreplyViewActivity extends SherlockActivity {
	TextView nameTextView, replyTextTextView, keywordsTextView;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.view_autoreply);
        
        nameTextView = (TextView) findViewById(R.id.autoreply_name);
        replyTextTextView = (TextView) findViewById(R.id.autoreplyText);
        keywordsTextView = (TextView) findViewById(R.id.autoreplyKeywords);
        
        Bundle bundle = this.getIntent().getExtras();
        String name = bundle.getString("name");
        String replyText = bundle.getString("replyText");
        String keywords = bundle.getString("keywords");
        
        nameTextView.append(" : "+name);
        replyTextTextView.append(replyText);
        keywordsTextView.append(keywords);
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
