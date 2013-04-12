package com.simlab.frontlinesms.activities;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.simlab.frontlinesms.R;

public class AutoreplyEditActivity extends SherlockActivity implements OnClickListener {
	String autoreplyId;
	String name;
	String replyText;
	String keywords;
	
	View saveAutoreplyButton;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_autoreply);
        
        saveAutoreplyButton = findViewById(R.id.autoreply_save_button);
        
        saveAutoreplyButton.setOnClickListener(this);
    }

	public void onClick(View v) {
		if(v == findViewById(R.id.autoreply_save_button)){
			Toast.makeText(getApplicationContext(), "Saving Autoreply", Toast.LENGTH_SHORT).show();
		}
	}
}
