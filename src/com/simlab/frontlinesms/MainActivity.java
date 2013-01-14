package com.simlab.frontlinesms;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.simlab.frontlinesms.fragments.ActivitiesFragment;
import com.simlab.frontlinesms.fragments.MainSectionFragment;
import com.simlab.frontlinesms.fragments.TabListener;

import android.os.Bundle;

public class MainActivity extends SherlockFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        //actionBar.setDisplayShowTitleEnabled(false);

        Tab tab = actionBar.newTab()
                .setText("Home")
                .setTabListener(new TabListener<MainSectionFragment>(this, "home_tab", MainSectionFragment.class));
        actionBar.addTab(tab);
        
        tab = actionBar.newTab()
                .setText("Activities")
                .setTabListener(new TabListener<ActivitiesFragment>(this, "activities_tab", ActivitiesFragment.class));
        actionBar.addTab(tab);
        
        tab = actionBar.newTab()
                .setText("Folders")
                .setTabListener(new TabListener<ActivitiesFragment>(this, "archive_tab", ActivitiesFragment.class));
        actionBar.addTab(tab);
        
        if(savedInstanceState != null) {
        	actionBar.setSelectedNavigationItem(savedInstanceState.getInt("tab"));
        }
    }
    
    protected void onSaveInstanceState(Bundle outState) {
    	super.onSaveInstanceState(outState);
    	outState.putInt("tab", getSupportActionBar().getSelectedNavigationIndex());
    }
    
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.activity_main, menu);
        return true;
    }
}	
