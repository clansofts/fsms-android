package com.simlab.frontlinesms.activities;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.j256.ormlite.dao.Dao;
import com.simlab.frontlinesms.MainActivity;
import com.simlab.frontlinesms.R;
import com.simlab.frontlinesms.activities.AutoreplyViewActivity;
import com.simlab.frontlinesms.database.DatabaseHelper;
import com.simlab.frontlinesms.domains.Autoforward;
import com.simlab.frontlinesms.domains.Autoreply;
import com.simlab.frontlinesms.domains.Factivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FactivitiesListActivity extends SherlockActivity implements OnItemClickListener {
	ListView activityListView;
	public FactivityItemAdapter aa;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        
		setContentView(R.layout.activities_list);
		try {
			Dao<Autoreply, Integer> autoreplyDao = new DatabaseHelper(getApplicationContext()).getAutoreplyDao();
			Dao<Autoforward, Integer> autoforwardDao = new DatabaseHelper(getApplicationContext()).getAutoforwardDao();

			List<Autoreply> autoreplyActivityList = autoreplyDao.queryForAll();
			List<Autoforward> autoforwardActivityList = autoforwardDao
					.queryForAll();
			ArrayList<FactivityItem> allActivities = new ArrayList<FactivityItem>();

			for (Autoreply a : autoreplyActivityList) {
				allActivities.add(new FactivityItem(a.id, a.name, a.getType(),
						a.keywords));
			}
			for (Autoforward a : autoforwardActivityList) {
				allActivities.add(new FactivityItem(a.id, a.name, a.getType(),
						a.keywords));
			}

			activityListView = (ListView) findViewById(
					R.id.all_activities);
			aa = new FactivityItemAdapter(this, R.layout.activity_item_row, allActivities);
			activityListView.setAdapter(aa);
			aa.notifyDataSetChanged();
			
			activityListView.setOnItemClickListener(this);
		} catch (SQLException e) {
			e.printStackTrace();
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

	/*
	 * Handling for activity selected
	 */
	public void onItemClick(AdapterView<?> arg0, View view, int arg2, long arg3) {
		Toast.makeText(this, "Selected activity ", Toast.LENGTH_SHORT).show();
		String id = ((TextView) view.findViewById(R.id.activityId)).getText().toString();
		String type = ((TextView) view.findViewById(R.id.activityType)).getText().toString();
		Factivity selectedActivity = getFactivity(type,id);
		if(selectedActivity instanceof Autoreply) {
			Intent i = new Intent(this, AutoreplyViewActivity.class);
			i.putExtra("name", selectedActivity.name);
			i.putExtra("replyText", ((Autoreply) selectedActivity).replyText);
			i.putExtra("keywords", selectedActivity.keywords);
			startActivity(i);
			
		} else if (selectedActivity instanceof Autoforward) {
			
		}
	}
	
	private Factivity getFactivity(String type, String id) {
		Dao<Autoreply, ?> autoreplyDao = null;
		Dao<Autoforward, ?> autoforwardDao =  null;
		try {
			autoreplyDao = new DatabaseHelper(MainActivity.context).getAutoreplyDao();
			autoforwardDao = new DatabaseHelper(MainActivity.context).getAutoforwardDao();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		Factivity f = null;
		
		if(type == "autoreply") {
			Autoreply a = new Autoreply();
			a.id = Integer.parseInt(id);
			try {
				f = (Factivity) autoreplyDao.queryForSameId(a);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(type == "autoforward") {
			Autoforward a = new Autoforward();
			a.id = Integer.parseInt(id);
			try {
				f = (Factivity) autoforwardDao.queryForSameId(a);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return f;
	}
}

class FactivityItem {
	int id;
	String name;
	String type;
	String keywords;

	public FactivityItem(int id, String name, String type, String keywords) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.keywords = keywords;
	}

	@Override
	public String toString() {
		return this.name + " (" + this.type + ")";
	}
}

class FactivityItemAdapter extends ArrayAdapter<FactivityItem> {
	Context context;
	int layoutResourceId;
	ArrayList<FactivityItem> data = null;

	public FactivityItemAdapter(Context context, int layoutResourceId, ArrayList<FactivityItem> data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		FactivityRowHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);

			holder = new FactivityRowHolder();
			holder.activityId = (TextView) row.findViewById(R.id.activityId);
			holder.activityType = (TextView) row.findViewById(R.id.activityType);
			holder.activityName = (TextView) row.findViewById(R.id.activityName);
			row.setTag(holder);
		} else {
			holder = (FactivityRowHolder) row.getTag();
		}

		FactivityItem a = data.get(position);
		holder.activityId.setText(Integer.toString(a.id));
		holder.activityType.setText(a.type);
		holder.activityName.setText(a.name);

		return row;
	}
	
	static class FactivityRowHolder {
		TextView activityId, activityType, activityName;
	}
}