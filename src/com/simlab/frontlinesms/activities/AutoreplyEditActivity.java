package com.simlab.frontlinesms.activities;

import java.sql.SQLException;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.simlab.frontlinesms.R;
import com.simlab.frontlinesms.database.DatabaseHelper;
import com.simlab.frontlinesms.domains.Autoreply;

public class AutoreplyEditActivity extends SherlockActivity implements OnClickListener {
	String autoreplyId, name, replyText, keywords;
	View saveAutoreplyButton;
	TextView nameTextField, replyTextField, keywordsTextField;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.edit_autoreply);
        
        nameTextField = (TextView) findViewById(R.id.autoreply_name);
        keywordsTextField = (TextView) findViewById(R.id.autoreply_keywords);
        replyTextField = (TextView) findViewById(R.id.autoreply_replytext);
        saveAutoreplyButton = findViewById(R.id.autoreply_save_button);
        
        saveAutoreplyButton.setOnClickListener(this);
    }

	public void onClick(View v) {
		switch(v.getId()){
			case R.id.autoreply_save_button: {
				Autoreply autoreply = new Autoreply();
				autoreply.setName(nameTextField.getText().toString());
				autoreply.setReplyText(replyTextField.getText().toString());
				try {
					new DatabaseHelper(getApplicationContext()).getAutoreplyDao().create(autoreply);
					Toast.makeText(getApplicationContext(), "Autoreply Saved :)", Toast.LENGTH_LONG).show();
					super.onBackPressed();
				} catch (SQLException e) {
					Log.e("SEMEV", "### Could not save autoreply");
					e.printStackTrace();
				}
			}
			default:
				//do nothing
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
