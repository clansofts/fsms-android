package com.simlab.frontlinesms.fragments;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.actionbarsherlock.app.SherlockFragment;
import com.j256.ormlite.dao.Dao;
import com.simlab.frontlinesms.R;
import com.simlab.frontlinesms.database.DatabaseHelper;
import com.simlab.frontlinesms.domains.Autoforward;
import com.simlab.frontlinesms.domains.Autoreply;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ActivitiesFragment extends SherlockFragment {
	ListView activityListView;
	public ArrayAdapter<FactivityItem> aa;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activities_list, container, false);
    }

    @Override
    public void onActivityCreated (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			Dao<Autoreply, Integer> autoreplyDao = new DatabaseHelper(getActivity().getApplicationContext()).getAutoreplyDao();
			Dao<Autoforward, Integer> autoforwardDao = new DatabaseHelper(getActivity().getApplicationContext()).getAutoforwardDao();
			
			List<Autoreply> autoreplyActivityList = autoreplyDao.queryForAll();
			List<Autoforward> autoforwardActivityList = autoforwardDao.queryForAll();
			ArrayList<FactivityItem> allActivities = new ArrayList<FactivityItem>();
			
			for(Autoreply a : autoreplyActivityList) {
				allActivities.add(new FactivityItem(a.name, a.getType(), a.keywords));
			}
			for(Autoforward a : autoforwardActivityList) {
				allActivities.add(new FactivityItem(a.name, a.getType(), a.keywords));
			}
			
			activityListView = (ListView) getView().findViewById(R.id.all_activities);
			aa = new ArrayAdapter<FactivityItem>(this.getActivity(), android.R.layout.simple_list_item_1, allActivities);
			activityListView.setAdapter(aa);
			aa.notifyDataSetChanged();

		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}

class FactivityItem {
	String name;
	String type;
	String keywords;
	
	public FactivityItem(String name, String type, String keywords) {
		this.name = name;
		this.type = type;
		this.keywords = keywords;
	}
	
	@Override
	public String toString() {
		return this.name + " (" + this.type + ")";
	}
}