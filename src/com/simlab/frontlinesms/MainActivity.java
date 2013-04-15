package com.simlab.frontlinesms;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.simlab.frontlinesms.fragments.ActivitiesFragment;
import com.simlab.frontlinesms.fragments.MainSectionFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

public class MainActivity extends SherlockFragmentActivity {
	
	public static Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        Tab homeTab = actionBar.newTab().setText("Home").setTabListener(new MyTabListener(new MainSectionFragment()));    
        Tab activityTab = actionBar.newTab().setText("Activities").setTabListener(new MyTabListener(new ActivitiesFragment()));

        actionBar.addTab(homeTab);
        actionBar.addTab(activityTab);
        
        if(savedInstanceState != null) {
        	actionBar.setSelectedNavigationItem(savedInstanceState.getInt("tab"));
        }
        
        MainActivity.context = getApplicationContext();
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

class MyTabListener implements ActionBar.TabListener{
    private SherlockFragment fragment;

    public MyTabListener(SherlockFragment fragment){
        this.fragment = fragment;
    }
    public void onTabSelected(Tab tab, FragmentTransaction ft){
        ft.add(android.R.id.content, fragment, null);
    }
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
    }
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        ft.remove(fragment);
    }
}