package com.android.slider.utils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.android.slider.receivers.ApplicationReceiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class AlarmUtil {
	
	private Context context;
	
	public AlarmUtil(Context context){
		this.context = context;
	}
	
	public void setupLaunchSliding(String time) {
		AlarmManager am = (AlarmManager) context
	            .getSystemService(Context.ALARM_SERVICE);

	    Date future = null;
		try {
			future = parseSlideShowTime(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Date dat = new Date();

		Calendar cal_alarm = Calendar.getInstance();
		Calendar cal_now = Calendar.getInstance();
		cal_alarm.setTime(dat);
		cal_alarm.set(Calendar.HOUR_OF_DAY, future.getHours());// set the alarm time
		cal_alarm.set(Calendar.MINUTE, future.getMinutes());
		cal_alarm.set(Calendar.SECOND, future.getSeconds());
		cal_alarm.set(Calendar.MILLISECOND, 0);
		if (cal_alarm.before(cal_now)) {// if its in the past increment
		    cal_alarm.add(Calendar.DATE, 1);
		}
		Long tmemills = cal_alarm.getTimeInMillis();

	    Intent intent = new Intent(context, ApplicationReceiver.class);
	    intent.setAction("com.android.slider.START");

	    PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent,
	            PendingIntent.FLAG_UPDATE_CURRENT);

	    am.setRepeating(AlarmManager.RTC_WAKEUP, tmemills, AlarmManager.INTERVAL_DAY, sender); 
	}
	
	public void setupStopSliding(String time) {
		AlarmManager am = (AlarmManager) context
	            .getSystemService(Context.ALARM_SERVICE);

	    Date future = null;
		try {
			future = parseSlideShowTime(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Date dat = new Date();

		Calendar cal_alarm = Calendar.getInstance();
		Calendar cal_now = Calendar.getInstance();
		cal_alarm.setTime(dat);
		cal_alarm.set(Calendar.HOUR_OF_DAY, future.getHours());// set the alarm time
		cal_alarm.set(Calendar.MINUTE, future.getMinutes());
		cal_alarm.set(Calendar.SECOND, future.getSeconds());
		cal_alarm.set(Calendar.MILLISECOND, 0);
		if (cal_alarm.before(cal_now)) {// if its in the past increment
		    cal_alarm.add(Calendar.DATE, 1);
		}
		Long tmemills = cal_alarm.getTimeInMillis();

	    Intent intent = new Intent(context, ApplicationReceiver.class);
	    intent.setAction("com.android.slider.STOP");

	    PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent,
	            PendingIntent.FLAG_UPDATE_CURRENT);

	    am.setRepeating(AlarmManager.RTC_WAKEUP, tmemills, AlarmManager.INTERVAL_DAY, sender); 
	}
	private Date parseSlideShowTime(String str) throws ParseException {
        DateFormat df = new SimpleDateFormat("kk:mm:ss");
        Date result =  df.parse(str);
        return result;
	}

}
