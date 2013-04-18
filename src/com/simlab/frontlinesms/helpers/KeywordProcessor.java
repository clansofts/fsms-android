package com.simlab.frontlinesms.helpers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.simlab.frontlinesms.MainActivity;
import com.simlab.frontlinesms.database.DatabaseHelper;
import com.simlab.frontlinesms.domains.Autoforward;
import com.simlab.frontlinesms.domains.Autoreply;
import com.simlab.frontlinesms.domains.Factivity;

public class KeywordProcessor {
	public static ArrayList<Factivity> getMatchedActivities(String messageBody) {
		ArrayList<Factivity> matchedActivities = new ArrayList<Factivity>();
		Dao<Autoreply, ?> autoreplyDao = null;
		List<Autoreply> autoreplyList = null;
		
		Dao<Autoforward, ?> autoforwardDao = null;
		List<Autoforward> autoforwardList = null;
		
		try {
			autoreplyDao = new DatabaseHelper(MainActivity.context).getAutoreplyDao();
			autoforwardDao = new DatabaseHelper(MainActivity.context).getAutoforwardDao();
			autoreplyList = autoreplyDao.queryForAll();
			autoforwardList =  autoforwardDao.queryForAll();
		} catch (SQLException e) {
			Log.e("SEMEV", "Could not read the autoreply from database");
			e.printStackTrace();
		}
		
		for(Autoreply a : autoreplyList) {
			for(String k : a.keywords.toUpperCase().split(",")) {
				if(messageBody.toUpperCase().startsWith(k.trim().toUpperCase())) {
					matchedActivities.add(a);
				}
			}
		}
		
		for(Autoforward a : autoforwardList) {
			for(String k : a.keywords.toUpperCase().split(",")) {
				if(messageBody.toUpperCase().startsWith(k.trim().toUpperCase())) {
					matchedActivities.add(a);
				}
			}
		}
		Log.i("SEMEV", "Just matched activities # "+matchedActivities.size());
		return matchedActivities;
	}
}
