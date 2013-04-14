package com.simlab.frontlinesms.domains;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "factivity")
public class Factivity {
	@DatabaseField(generatedId = true)
	private int id;

	@DatabaseField(canBeNull = false)
	private String name;

	@ForeignCollectionField
	private ForeignCollection<Keyword> keywords;

	//> SETTERS
	public void setName(String name) {
		this.name = name;
	}

	public void addKeyword(String keyword) {
		//TODO
	}

	public void removeKeyword(String keyword) {
		//TODO
	}

	//> GETTERS
	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public ForeignCollection<Keyword> getKeywords() {
		return this.keywords;
	}

	public String getType() {
		return "generic_activity";
	}

    @Override
    public String toString() {
        return this.name + " (" + this.getType() + ")";
    }
}
