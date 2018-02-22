package com.alper.pola.andoid.wallme.Activity.Main;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alper.pola.andoid.wallme.Adapter.ImageAdapter;
import com.alper.pola.andoid.wallme.Storage.MySharedPref;
import com.alper.pola.andoid.wallme.model.ImageResponse;
import com.alper.pola.andoid.wallme.model.RequestInterface;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;

import io.reactivex.processors.PublishProcessor;

/**
 * Created by shashank on 16-Oct-17.
 */

public interface MainModule {
    public interface mainview {
        RecyclerView recyclerView();

        EditText editText();

        ArrayList<ImageResponse> imageResponsef();

        String source();

        TextView searchtext();

        RelativeLayout dailyWallpaper();

        AdView mAdView();
         InterstitialAd mInterstitialAd();

        MainActivity mainactivity();

        AlertDialog.Builder dialogBuilder();
        MySharedPref myshredpref();
        Context context();
        RequestInterface requestinterface();
        ImageAdapter imageAdapter();
        PublishProcessor<Integer> paginator();



    }

    public interface mainpresenter {
        void setcardlist();

        void mainscreen();

        void showad();
        void retrofit();

        void search();

        void dailywallpaper(AlertDialog.Builder dialogBuilder);
        void setalarm(Context context);
        void setView(MainModule.mainview view);
    }
}
