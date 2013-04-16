package com.simlabs.frontlinesms.listeners;

import java.util.ArrayList;

import com.simlab.frontlinesms.MainActivity;
import com.simlab.frontlinesms.domains.Factivity;
import com.simlab.frontlinesms.domains.Fmessage;
import com.simlab.frontlinesms.helpers.KeywordProcessor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SmsListener extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		MainActivity.context = context;
		if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
			Bundle bundle = intent.getExtras();
			SmsMessage[] msgs = null;
			String msgSource;
			if (bundle != null) {
				try {
					Object[] pdus = (Object[]) bundle.get("pdus");
					msgs = new SmsMessage[pdus.length];
					for (int i = 0; i < msgs.length; i++) {
						msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
						msgSource = msgs[i].getOriginatingAddress();
						String msgBody = msgs[i].getMessageBody();
						// Get the matched Activities
						ArrayList<Factivity> activityList = KeywordProcessor.getMatchedActivities(msgBody);
						Fmessage fm = new Fmessage();
						fm.setSource(msgSource);
						fm.setText(msgBody);
						fm.setInbound(true);
						for (Factivity activity : activityList) {
							activity.process(fm);
						}
						Toast.makeText(context, msgBody, Toast.LENGTH_LONG)
								.show();
						Log.d("SEMEV", "INCOMING MESSAGE # " + msgBody);
					}
				} catch (Exception e) {
					Log.e("SEMEV", "### OOPS SOMETHING BAD HAS HAPPENED");
					e.printStackTrace();
				}
			}
		}
	}
}
