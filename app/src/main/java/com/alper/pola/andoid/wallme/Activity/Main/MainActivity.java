package com.alper.pola.andoid.wallme.Activity.Main;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alper.pola.andoid.wallme.Activity.Splash.SplashActivity;
import com.alper.pola.andoid.wallme.Adapter.ImageAdapter;
import com.alper.pola.andoid.wallme.App.WallMeApplication;
import com.alper.pola.andoid.wallme.R;
import com.alper.pola.andoid.wallme.Service.UpdateReceiver;
import com.alper.pola.andoid.wallme.Storage.MySharedPref;
import com.alper.pola.andoid.wallme.model.ImageResponse;
import com.alper.pola.andoid.wallme.model.RequestInterface;
import com.alper.pola.andoid.wallme.util.Constants;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.processors.PublishProcessor;


public class MainActivity extends Activity implements MainModule.mainview {
    @BindView(R.id.Recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.searchedt)
    EditText editText;
    ArrayList<ImageResponse> imageResponsef = new ArrayList<>();
    String source;
    MySharedPref mySharedPref;
    @BindView(R.id.searchtext)
    TextView searchtext;
    @BindView(R.id.rl_dailyWallpaper)
    RelativeLayout dailyWallpaper;
    AlertDialog.Builder dialogBuilder;
    PublishProcessor<Integer> paginator = PublishProcessor.create();
    ImageAdapter imageAdapter = new ImageAdapter(imageResponsef, this);

    @BindView(R.id.adView)
    AdView mAdView;
    private InterstitialAd mInterstitialAd;
    RequestInterface requestInterface;

    @Inject
    MainPresenter mainpresenter;


    @TargetApi(Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ((WallMeApplication) getApplication()).getAppComponent().inject(this);

        ButterKnife.bind(this);
        SplashActivity.fa.finish();
        dialogBuilder = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);
        recyclerView.setAdapter(imageAdapter);
        source = Constants.search;

        setsharedprefrence();

        mainpresenter.setView(this);

        mInterstitialAd = new InterstitialAd(this);
        mainpresenter.setcardlist();
        mainpresenter.retrofit();
        mainpresenter.mainscreen();

        //mainpresenter.search();
        mainpresenter.showad();


        dailyWallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainpresenter.dailywallpaper(dialogBuilder);
                UpdateReceiver updateReceiver = new UpdateReceiver();
                updateReceiver.change(MainActivity.this, mInterstitialAd);
            }
        });

    }


    @Override
    public RecyclerView recyclerView() {
        return recyclerView;
    }

    @Override
    public EditText editText() {
        return editText;
    }

    @Override
    public ArrayList<ImageResponse> imageResponsef() {
        return imageResponsef;
    }

    @Override
    public String source() {
        return source;
    }

    @Override
    public TextView searchtext() {
        return searchtext;
    }

    @Override
    public RelativeLayout dailyWallpaper() {
        return dailyWallpaper;
    }

    @Override
    public AdView mAdView() {
        return mAdView;
    }

    @Override
    public InterstitialAd mInterstitialAd() {
        return mInterstitialAd;
    }


    @Override
    public MainActivity mainactivity() {
        return MainActivity.this;
    }


    @Override
    public AlertDialog.Builder dialogBuilder() {
        return dialogBuilder;
    }

    @Override
    public MySharedPref myshredpref() {
        return mySharedPref;
    }

    @Override
    public Context context() {
        return MainActivity.this;
    }

    @Override
    public RequestInterface requestinterface() {
        return requestInterface;
    }

    @Override
    public ImageAdapter imageAdapter() {
        return imageAdapter;
    }

    @Override
    public PublishProcessor<Integer> paginator() {
        return paginator;
    }

    public void setsharedprefrence() {
        mySharedPref = MySharedPref.getInstance();
        mySharedPref.setSharedPreferences(MainActivity.this);
    }
}

