package com.simlab.frontlinesms.domains;

import java.util.ArrayList;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.simlab.frontlinesms.helpers.SMSTransporter;

@DatabaseTable(tableName = "autoreply")
public class Autoreply extends Factivity {
	@DatabaseField(canBeNull = false)
	public String replyText;

	public Autoreply() { }

	public String getType() {
		return "autoreply";
	}

	public void process(Fmessage message) {
		ArrayList<String> recipient = new ArrayList<String>();
		recipient.add(message.getSource());

		SMSTransporter.send(this.replyText, recipient);
	}
}