package com.alper.pola.andoid.wallme.Activity.Splash;

import com.alper.pola.andoid.wallme.logic.Logic;
import com.alper.pola.andoid.wallme.util.ConnectionDetector;
import com.facebook.network.connectionclass.ConnectionQuality;
import com.facebook.network.connectionclass.DeviceBandwidthSampler;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Created by shashank on 16-Oct-17.
 */

public interface SplashModule {
    public interface SplashView {
        ConnectionQuality mConnectionClass();
        DeviceBandwidthSampler mDeviceBandwidthSampler();
        String mURL();
        Logic logic();
        ConnectionDetector connectionDetector();
        SplashActivity context();
        int mtries();




    }
    public interface SplashPresenter{
    void connection();
        void testPhoto();



    }
}
