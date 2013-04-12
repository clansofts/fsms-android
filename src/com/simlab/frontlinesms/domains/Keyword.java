package com.simlab.frontlinesms.domains;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "keywords")
public class Keyword {
	@DatabaseField(generatedId = true)
	private int id;
	
	@DatabaseField()
	private String value;
	
	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "factivity_id")
	private Factivity activity;
	
	public Keyword() { }
	
	//SETTERS
	public void setValue(String value) {
		this.value = value;
	}
	
	//GETTERS
	public int getId() {
		return this.id;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public Factivity getActivity() {
		return this.activity;
	}
}
