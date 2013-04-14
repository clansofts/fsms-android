package com.simlab.frontlinesms.fragments;

import java.sql.SQLException;
import java.util.List;

import com.actionbarsherlock.app.SherlockFragment;
import com.j256.ormlite.dao.Dao;
import com.simlab.frontlinesms.R;
import com.simlab.frontlinesms.database.DatabaseHelper;
import com.simlab.frontlinesms.domains.Autoreply;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ActivitiesFragment extends SherlockFragment {
	ListView activityListView;
	public ArrayAdapter<Autoreply> aa;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activities_list, container, false);
    }

    @Override
    public void onActivityCreated (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			Dao autoreplyDao = new DatabaseHelper(getActivity().getApplicationContext()).getAutoreplyDao();
			List<Autoreply> autoreplyActivityList = autoreplyDao.queryForAll();
			activityListView = (ListView) getView().findViewById(R.id.all_activities);
			aa = new ArrayAdapter<Autoreply>(this.getActivity(), android.R.layout.simple_list_item_1, autoreplyActivityList);
			activityListView.setAdapter(aa);
			aa.notifyDataSetChanged();

		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}