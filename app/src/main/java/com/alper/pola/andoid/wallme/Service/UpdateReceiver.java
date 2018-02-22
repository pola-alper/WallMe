package com.alper.pola.andoid.wallme.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.alper.pola.andoid.wallme.Activity.Splash.SplashActivity;
import com.alper.pola.andoid.wallme.Activity.Main.MainActivity;
import com.alper.pola.andoid.wallme.R;
import com.alper.pola.andoid.wallme.Storage.MySharedPref;
import com.alper.pola.andoid.wallme.model.ImageResponse;
import com.alper.pola.andoid.wallme.model.Wallme;
import com.alper.pola.andoid.wallme.util.ConnectionDetector;
import com.alper.pola.andoid.wallme.util.Constants;
import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;

/**
 * Created by shashank on 20-Sep-17.
 */


public class UpdateReceiver extends BroadcastReceiver {
    public static final String BROADCAST = "PACKAGE_NAME.android.action.broadcast";
    private static final int notification_one = 101;
    final Random random = new Random();
    ImageResponse item;
    Bitmap bitmap;
    MySharedPref mySharedPref;
    UpdateReceiverException updateReceiverException = new UpdateReceiverException();
    com.alper.pola.andoid.wallme.util.ConnectionDetector connectionDetector;
    Random rand = new Random();
    int page = rand.nextInt(26) + 1;
    private InterstitialAd mInterstitialAd;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("reciver2", "started");
        mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdUnitId("ca-app-pub-7864537676903385/4938228737");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        setSharedPref(context);
        String action = intent.getAction();

        connectionDetector = new ConnectionDetector(context);


        Log.d(String.valueOf(mySharedPref.getname()), "valuephonerestart");

        if (connectionDetector.isConnected()) {


            change(context,mInterstitialAd);
        } else {
            IntentFilter intentFilter = new IntentFilter(BROADCAST);
            context.getApplicationContext().registerReceiver(updateReceiverException, intentFilter);
            Intent intent1 = new Intent(BROADCAST);
            context.sendBroadcast(intent1);

        }


    }

    private void setSharedPref(Context context) {
        mySharedPref = MySharedPref.getInstance();
        mySharedPref.setSharedPreferences(context);
    }

    public void change(final Context context, final InterstitialAd mInterstitialAd) {
        final String url;

        url = Constants.Daily_Wallpaber;


        Log.d("randrom value", String.valueOf(page));
        Constants.Daily_Wallpaber = Constants.Daily_Wallpaber + String.valueOf(page);

        Log.d("serviceurl", Constants.Daily_Wallpaber);
        Wallme wallme = Wallme.getInstance(context.getApplicationContext());
        wallme.getSearchLists2(context.getApplicationContext(), new Wallme.GetSearchListCallBack() {
            @Override
            public void onSuccess(ArrayList<ImageResponse> imageResponses) {


                Log.d("responsereciver", "Managers choice this week" + imageResponses.toString() + "our recommendation to you");

                int index = random.nextInt(imageResponses.size());
                item = imageResponses.get(index);
                Log.d("responsereciver", "Managers choice this week" + item.getLargeImageURL() + "our recommendation to you");
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        Looper.prepare();
                        try {
                            bitmap = Glide.
                                    with(context.getApplicationContext()).
                                    load(item.getFullHDURL()).asBitmap().
                                    into(item.getImageWidth(), item.getImageHeight()).
                                    get();
                        } catch (final ExecutionException | InterruptedException e) {
                            Log.e("this", e.getMessage());
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void dummy) {

                        if (null != bitmap) {
                            // The full bitmap should be available here


                            WallpaperManager myWallpaperManager
                                    = WallpaperManager.getInstance(context.getApplicationContext());
                            try {
                                myWallpaperManager.setBitmap(bitmap);

                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }


                        }
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                            PendingIntent contentIntent =
                                    PendingIntent.getActivity(context.getApplicationContext(), 0, new Intent(context.getApplicationContext(), SplashActivity.class), 0);
                            Notification.Builder notificationBuilder = null;
                            NotificationHelper notificationHelper = new NotificationHelper(context);
                            notificationBuilder = notificationHelper.getNotification1(contentIntent, bitmap);
                            notificationHelper.notify(notification_one, notificationBuilder);
                        } else {
                            notifi(context);
                        }
                        mInterstitialAd.show();
                        Constants.Daily_Wallpaber = url;
//

                    }
                }.execute();
            }

            @Override
            public void onFail(String message) {

            }
        });
    }

    public void notifi(Context context) {
        PendingIntent contentIntent =
                PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), 0);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_check_black_24dp)
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap))
                .setContentTitle("Wall Me")
                .setContentIntent(contentIntent);
// Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
// Builds the notification and issues it.
        mNotifyMgr.notify(0, mBuilder.build());


    }


}


