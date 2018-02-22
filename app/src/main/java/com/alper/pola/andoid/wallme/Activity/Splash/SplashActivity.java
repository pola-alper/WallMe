package com.alper.pola.andoid.wallme.Activity.Splash;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.alper.pola.andoid.wallme.R;
import com.alper.pola.andoid.wallme.logic.Logic;
import com.alper.pola.andoid.wallme.util.ConnectionDetector;
import com.facebook.network.connectionclass.ConnectionClassManager;
import com.facebook.network.connectionclass.ConnectionQuality;
import com.facebook.network.connectionclass.DeviceBandwidthSampler;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class SplashActivity extends AppCompatActivity implements SplashModule.SplashView {
    public static ConnectionQuality mConnectionClass = ConnectionQuality.UNKNOWN;
    public static Activity fa;
    int mTries = 0;
    private ConnectionClassManager mConnectionClassManager;
    private DeviceBandwidthSampler mDeviceBandwidthSampler;
    private ConnectionChangedListener mListener;
    private InterstitialAd mInterstitialAd;
    Logic logic;
    ConnectionDetector connectionDetector;
    private String mURL = "https://www.compareprix.in/images/resizeimages/product/50/hero-next-26t-18-speed-hi-sprint-bicycle-right.jpg?v=73";
    SplashModule.SplashPresenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mConnectionClassManager = ConnectionClassManager.getInstance();
        mDeviceBandwidthSampler = DeviceBandwidthSampler.getInstance();
        mListener = new ConnectionChangedListener();
        fa = this;
        MobileAds.initialize(this, "ca-app-pub-7864537676903385~4733735674");
        connectionDetector = new ConnectionDetector(this);
        logic = new Logic();
        splashPresenter = SplashPresenter.getpresenter(this);
        splashPresenter.connection();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mConnectionClassManager.remove(mListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mConnectionClassManager.register(mListener);
    }

    @Override
    public ConnectionQuality mConnectionClass() {
        return mConnectionClass;
    }

    @Override
    public DeviceBandwidthSampler mDeviceBandwidthSampler() {
        return mDeviceBandwidthSampler;
    }


    @Override
    public String mURL() {
        return mURL;
    }

    @Override
    public Logic logic() {
        return logic;
    }

    @Override
    public ConnectionDetector connectionDetector() {
        return connectionDetector;
    }

    @Override
    public SplashActivity context() {
        return SplashActivity.this;
    }

    @Override
    public int mtries() {
        return mTries;
    }


    private class ConnectionChangedListener
            implements ConnectionClassManager.ConnectionClassStateChangeListener {

        @Override
        public void onBandwidthStateChange(ConnectionQuality bandwidthState) {
            mConnectionClass = bandwidthState;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.d("connectionclass", mConnectionClass.toString());
                    logic.mainscreen(Logic.pagenumber,SplashActivity.this);

                }
            });
        }
    }


}
