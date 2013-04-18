package com.simlab.frontlinesms.domains;

import java.util.ArrayList;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.simlab.frontlinesms.helpers.SMSTransporter;

@DatabaseTable(tableName = "autoforward")
public class Autoforward extends Factivity {
	@DatabaseField(canBeNull = false)
	public String forwardText;
	
	@DatabaseField(canBeNull = false)
	public String recipents; //Comma seperated mobile numbers

	public Autoforward() { }

	public String getType() {
		return "autoforward";
	}

	public void process(Fmessage message) {
		ArrayList<String> recipient = new ArrayList<String>();
		for(String r : this.recipents.split(",")) {
			r = r.trim();
			if(r != "") {
				recipient.add(r);
			}
		}
		if(recipient != null) {
			SMSTransporter.send(this.forwardText + " # " + message.getText(), recipient);
		}
	}
}