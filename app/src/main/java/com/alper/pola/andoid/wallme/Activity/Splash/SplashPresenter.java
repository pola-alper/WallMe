package com.alper.pola.andoid.wallme.Activity.Splash;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.alper.pola.andoid.wallme.logic.Logic;
import com.facebook.network.connectionclass.ConnectionQuality;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by shashank on 16-Oct-17.
 */

public class SplashPresenter implements SplashModule.SplashPresenter{

    SplashModule.SplashView splashView;
    public SplashPresenter(SplashModule.SplashView splashView) {
        this.splashView = splashView;
    }
    public static SplashModule.SplashPresenter getpresenter (SplashModule.SplashView view){
        return  new SplashPresenter(view);    }
    @Override
    public void connection() {

        if (splashView.connectionDetector().isConnected()) {
           testPhoto();
        } else {
            Toast.makeText(splashView.context(), "There Is No Internet Connection", Toast.LENGTH_SHORT).show();


        }
    }

    @Override
    public void testPhoto() {
        class DownloadImage extends AsyncTask<String, Void, Void> {

            @Override
            protected void onPreExecute() {
                splashView.mDeviceBandwidthSampler().startSampling();
            }

            @Override
            protected Void doInBackground(String... url) {
                String imageURL = url[0];
                try {
                    // Open a stream to download the image from our URL.
                    URLConnection connection = new URL(imageURL).openConnection();
                    connection.setUseCaches(false);
                    connection.connect();
                    InputStream input = connection.getInputStream();
                    try {
                        byte[] buffer = new byte[1024];

                        // Do some busy waiting while the stream is open.
                        while (input.read(buffer) != -1) {
                        }
                    } finally {
                        input.close();
                    }
                } catch (IOException e) {
                    Log.e("tah", "Error while downloading image.");
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void v) {
                splashView.mDeviceBandwidthSampler().stopSampling();
                // Retry for up to 10 times until we find a ConnectionClass.
                if (splashView.mConnectionClass() == ConnectionQuality.UNKNOWN && splashView.mtries() < 10) {
                    splashView.mtries();
                    new DownloadImage().execute(splashView.mURL());


                }

            }

        }
        new DownloadImage().execute(splashView.mURL());
    }
}
