package com.simlab.frontlinesms.fragments;

import com.actionbarsherlock.app.SherlockFragment;
import com.simlab.frontlinesms.R;
import com.simlab.frontlinesms.activities.AutoreplyEditActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

public class MainSectionFragment extends SherlockFragment implements OnClickListener {
	
	View autoreplyOption;
	View autoforwardOption;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_sections, container, false);
    }

    @Override
    public void onActivityCreated (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		autoreplyOption = getView().findViewById(R.id.autoreply_option);
		autoforwardOption = getView().findViewById(R.id.autoforward_option);
		
		autoreplyOption.setOnClickListener(this);
		autoforwardOption.setOnClickListener(this);
    }
    
    public void onClick (View v) {
    	Context currentContext = getActivity().getApplicationContext();
    	
		if(v == getView().findViewById(R.id.autoreply_option)){
			Toast.makeText(currentContext, "Opening Autoreply", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(getActivity().getApplicationContext(), AutoreplyEditActivity.class);
			startActivity(intent);
		}
		if(v == getView().findViewById(R.id.autoforward_option)){
			Toast.makeText(currentContext, "Opening Autoforward", Toast.LENGTH_SHORT).show();
		}
		if(v == getView().findViewById(R.id.poll_option)){
			Toast.makeText(currentContext, "Opening Poll", Toast.LENGTH_SHORT).show();
		}
    }
}