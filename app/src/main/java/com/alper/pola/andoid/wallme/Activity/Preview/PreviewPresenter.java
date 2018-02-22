package com.alper.pola.andoid.wallme.Activity.Preview;

import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Looper;
import android.util.Log;

import com.alper.pola.andoid.wallme.Activity.Splash.SplashActivity;
import com.bumptech.glide.Glide;
import com.facebook.network.connectionclass.ConnectionQuality;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by shashank on 16-Oct-17.
 */

public class PreviewPresenter implements PreviewModule.previewPresenter {
    PreviewModule.previewView previewView;
    Bitmap bitmap1;


    public PreviewPresenter(Context context) {

    }

    @Override
    public void loadPhotos() {
        if (SplashActivity.mConnectionClass == ConnectionQuality.POOR) {
            Glide.with(previewView.previewActivity()).load(previewView.imageResponse().getPreviewURL()).into(previewView.imageview());
            Log.d("internt spped low", "onBindViewHolder: low ");


        } else {
            Glide.with(previewView.previewActivity()).load(previewView.imageResponse().getWebformatURL()).into(previewView.imageview());
            Log.d("internt speed high", "onBindViewHolder: normal ");
        }
    }

    @Override
    public void showAd() {
        previewView.mInterstitialAd().setAdUnitId("ca-app-pub-7864537676903385/4938228737");
        previewView.mInterstitialAd().loadAd(new AdRequest.Builder().build());
        previewView.mInterstitialAd().setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Log.i("Ads", "onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                previewView.mInterstitialAd().loadAd(new AdRequest.Builder().build());
                previewView.mInterstitialAd().show();

                //  logic.mainscreen(Logic.pagenumber, SplashActivity.this);
                // Code to be executed when an ad request fails.
                Log.i("Ads", "onAdFailedToLoad");
            }

            @Override
            public void onAdOpened() {
                previewView.mInterstitialAd().show();
                // Code to be executed when the ad is displayed.
                Log.i("Ads", "onAdOpened");
            }

            @Override
            public void onAdLeftApplication() {
                // logic.mainscreen(Logic.pagenumber, SplashActivity.this);
                // Code to be executed when the user has left the app.
                Log.i("Ads", "onAdLeftApplication");
            }

            @Override
            public void onAdClosed() {
                previewView.previewActivity().finish();


                // Code to be executed when when the interstitial ad is closed.
                Log.i("Ads", "onAdClosed");
            }
        });

    }


    @SuppressLint("StaticFieldLeak")
    @Override
    public void download() {
        io.reactivex.Observable.fromCallable(() -> {
            Looper.prepare();

            try {
                bitmap1 = Glide.
                        with(previewView.previewActivity()).
                        load(previewView.imageResponse().getFullHDURL()).
                        asBitmap().
                        into(previewView.imageResponse().getImageWidth(), previewView.imageResponse().getImageHeight()).
                        get();
            } catch (final ExecutionException | InterruptedException e) {
                Log.e("this", e.getMessage());
            }
            return false;
        }).subscribeOn(Schedulers.io())

                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result) -> {
                    if (null != bitmap1) {
                        // The full bitmap should be available here


                        WallpaperManager myWallpaperManager
                                = WallpaperManager.getInstance(previewView.previewActivity());
                        try {
                            myWallpaperManager.setBitmap(bitmap1);

                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }


                    }

                    previewView.progressDialog().dismiss();
                    previewView.mInterstitialAd().show();
                });

    }


    @Override
    public void setView(PreviewModule.previewView view) {
        this.previewView = view;

    }


}
