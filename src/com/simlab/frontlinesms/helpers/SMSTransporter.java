package com.simlab.frontlinesms.helpers;

import java.util.ArrayList;
import java.util.List;

import com.simlab.frontlinesms.MainActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsManager;

public class SMSTransporter {
	public static void send(String msg, List<String> recipients) {
		ArrayList<PendingIntent> sentIntents = new ArrayList<PendingIntent>();
		ArrayList<PendingIntent> deliveryIntents = new ArrayList<PendingIntent>();

		SmsManager sms = SmsManager.getDefault();
		ArrayList<String> parts = sms.divideMessage(msg);
		for (int i = 0; i < parts.size(); i++) {
			PendingIntent sentIntent = PendingIntent.getBroadcast(MainActivity.context, 0, new Intent("SMS_SENT"), 0);
			PendingIntent deliveryIntent = PendingIntent.getBroadcast(MainActivity.context, 0, new Intent("SMS_DELIVERED"), 0);
			sentIntents.add(sentIntent);
			deliveryIntents.add(deliveryIntent);
		}
		for(String sendTo : recipients)
			if (PhoneNumberUtils.isGlobalPhoneNumber(sendTo))
				sms.sendMultipartTextMessage(sendTo, null, parts, sentIntents, deliveryIntents);
	}
}
