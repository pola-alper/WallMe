package com.alper.pola.andoid.wallme.Service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.alper.pola.andoid.wallme.R;

/**
 * Created by shashank on 05-Oct-17.
 */

 class NotificationHelper extends ContextWrapper {
    private NotificationManager notifManager;
    public static final String CHANNEL_ONE_ID = "com.alper.pola.andoid.wallme";
    public static final String CHANNEL_ONE_NAME = "Channel One";


//Create your notification channels//

    @RequiresApi(api = Build.VERSION_CODES.O)
    public NotificationHelper(Context base) {
        super(base);
        createChannels();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createChannels() {

        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ONE_ID,
                CHANNEL_ONE_NAME, notifManager.IMPORTANCE_HIGH);
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.RED);
        notificationChannel.setShowBadge(true);
        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        getManager().createNotificationChannel(notificationChannel);



    }

//Create the notification that’ll be posted to Channel One//

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Notification.Builder getNotification1(PendingIntent contentIntent, Bitmap bitmap) {

        return new Notification.Builder(getApplicationContext(), CHANNEL_ONE_ID)
                .setSmallIcon(R.drawable.logonotifi)
                .setContentTitle("Wallpaper has changed")
                .setContentIntent(contentIntent)
                .setStyle(new Notification.BigPictureStyle().bigPicture(bitmap))

                .setContentText("Daily Wallpaper")
                .setAutoCancel(true);
    }





    public void notify(int id, Notification.Builder notification) {
        getManager().notify(id, notification.build());
    }


    private NotificationManager getManager() {
        if (notifManager == null) {
            notifManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return notifManager;
    }
}