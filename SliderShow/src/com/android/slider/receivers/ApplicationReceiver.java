package com.android.slider.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.slider.activities.SlidingActivity;
import com.android.slider.utils.Prefs;

public class ApplicationReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i(getClass().getSimpleName(), "onReceive !!!");
		if (intent.getAction().equals("com.android.slider.START")) {
			Log.i(getClass().getSimpleName(),
					"com.android.slider.START onReceive !!!");
			Intent intentSliding = new Intent(context, SlidingActivity.class);
			intentSliding.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intentSliding);
		}
		if (intent.getAction().equals("com.android.slider.STOP")) {
			if (SlidingActivity.slidingActivityIsOpen()) {
				System.exit(0);
			}
		}
		if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
			Prefs prefs = new Prefs(context);
			if (prefs.retrieveRebootSlidingPref()) {
				Intent intentSliding = new Intent(context,
						SlidingActivity.class);
				intentSliding.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intentSliding);
			}
		}
		if (intent.getAction().equals(
				"android.intent.action.ACTION_POWER_CONNECTED")) {
			Prefs prefs = new Prefs(context);
			if (prefs.retrieveChargingSlidingPref()) {
				Log.i(getClass().getSimpleName(),
						"onReceive ACTION_POWER_CONNECTED!!!");
				Intent intentSliding = new Intent(context,
						SlidingActivity.class);
				intentSliding.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intentSliding);
			}
		}
		if (intent.getAction().equals(
				"android.intent.action.ACTION_POWER_DISCONNECTED")) {
			if (SlidingActivity.slidingActivityIsOpen()) {
				System.exit(0);
			}
		}

	}

}
