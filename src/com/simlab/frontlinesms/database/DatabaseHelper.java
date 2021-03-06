package com.simlab.frontlinesms.database;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.simlab.frontlinesms.domains.Autoforward;
import com.simlab.frontlinesms.domains.Autoreply;
import com.simlab.frontlinesms.domains.Factivity;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String DATABASE_NAME = "frontlinesms.db";
	private static final int DATABASE_VERSION = 1;
	private final String LOG_NAME = getClass().getName();

	private Dao<Autoreply, Integer> autoreplyDao;
	private Dao<Autoforward, Integer> autoforwardDao;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, Factivity.class);
			TableUtils.createTable(connectionSource, Autoreply.class);
			TableUtils.createTable(connectionSource, Autoforward.class);
		} catch (SQLException e) {
			Log.e(LOG_NAME, "Could not create new table ", e);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			TableUtils.dropTable(connectionSource, Factivity.class, true);
			TableUtils.dropTable(connectionSource, Autoreply.class, true);
			TableUtils.dropTable(connectionSource, Autoforward.class, true);
			onCreate(sqLiteDatabase, connectionSource);
		} catch (SQLException e) {
			Log.e(LOG_NAME, "Could not upgrade the table for Thing", e);
		}
	}

	public Dao<Autoreply, Integer> getAutoreplyDao() throws SQLException {
		if (autoreplyDao == null) { autoreplyDao = getDao(Autoreply.class);}
		return autoreplyDao;
	}
	
	public Dao<Autoforward, Integer> getAutoforwardDao() throws SQLException {
		if (autoforwardDao == null) { autoforwardDao = getDao(Autoforward.class); }
		return autoforwardDao;
	}
}
