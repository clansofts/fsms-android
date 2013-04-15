package com.simlab.frontlinesms.domains;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "factivity")
public class Factivity {
	@DatabaseField(generatedId = true)
	public int id;

	@DatabaseField(canBeNull = false)
	public String name;

	@DatabaseField(canBeNull = false)
	public String keywords;

	public Factivity() { }

	public String getType() {
		return "generic_activity";
	}

	public void process(Fmessage message) { }

	@Override
	public String toString() {
		return this.name + " (" + this.getType() + ")";
	}
}
