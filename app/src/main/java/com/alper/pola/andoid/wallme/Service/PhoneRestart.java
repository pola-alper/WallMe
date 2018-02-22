package com.alper.pola.andoid.wallme.Service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.alper.pola.andoid.wallme.Storage.MySharedPref;

import java.util.Calendar;

/**
 * Created by shashank on 09-Oct-17.
 */

public class PhoneRestart extends BroadcastReceiver {

    MySharedPref mySharedPref;


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("restart", "done");
        setSharedPref(context);


        Log.d(String.valueOf(mySharedPref.getname()), "valuephonerestart");

        if (mySharedPref.getname()) {
            setAlarm(context);

        }


    }


    private void setSharedPref(Context context) {
        mySharedPref = MySharedPref.getInstance();
        mySharedPref.setSharedPreferences(context);
    }

    public void setAlarm(Context context) {
        AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, UpdateReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);


        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 23
        );
        calendar.set(Calendar.MINUTE, 59);

        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);

    }


}
