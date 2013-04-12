package com.simlab.frontlinesms.domains;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "autoreply")
public class Autoreply extends Factivity {
	@DatabaseField(canBeNull = false)
	private String replyText;
	
	public Autoreply() { }
	
	//SETTERS
	public void setReplyText(String replyText) {
		this.replyText = replyText;
	}
	
	//GETTERS
	public String getReplyText() {
		return this.replyText;
	}
}
