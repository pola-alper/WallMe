package com.alper.pola.andoid.wallme.Activity.Main;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import com.alper.pola.andoid.wallme.Activity.Preview.PreviewActivity;
import com.alper.pola.andoid.wallme.Activity.Search.SearchActivity;
import com.alper.pola.andoid.wallme.App.WallMeApplication;
import com.alper.pola.andoid.wallme.R;
import com.alper.pola.andoid.wallme.Service.UpdateReceiver;
import com.alper.pola.andoid.wallme.model.Example;
import com.alper.pola.andoid.wallme.model.ImageResponse;
import com.alper.pola.andoid.wallme.model.RequestInterface;
import com.alper.pola.andoid.wallme.util.Constants;
import com.alper.pola.andoid.wallme.util.RecyclerItemClickListener;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.Calendar;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shashank on 16-Oct-17.
 */

public class MainPresenter implements MainModule.mainpresenter {
    private static final String baseurl = "https://pixabay.com/api/";
    private final int VISIBLE_THRESHOLD = 1;
    private MainModule.mainview mainview;
    private ArrayList<ImageResponse> imageResponse;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Boolean loading = false;
    private int pagenumber = 1;
    private int lastVisibleItem, totalItemCount;
   MainModule.mainview mainviewView;


    public MainPresenter(Context context) {
        ((WallMeApplication) context).getAppComponent().inject(this);

    }



    @Override
    public void setcardlist() {
        mainview.recyclerView().setAdapter(mainview.imageAdapter());

        mainview.recyclerView().setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mainview.context(), 2);
        mainview.recyclerView().setLayoutManager(gridLayoutManager);
        mainview.recyclerView().addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView,
                                   int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItemCount = gridLayoutManager.getItemCount();
                lastVisibleItem = gridLayoutManager
                        .findLastVisibleItemPosition();
                if (!loading
                        && totalItemCount <= (lastVisibleItem + VISIBLE_THRESHOLD)) {
                    pagenumber++;
                    retrofit();
                    loading = true;
                }
            }
        });
    }

    @Override
    public void mainscreen() {
        mainview.recyclerView().setVisibility(View.VISIBLE);
        Log.d("editorchoice", Constants.Editor_choice);
        mainview.recyclerView().addOnItemTouchListener(
                new RecyclerItemClickListener(mainview.mainactivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        ImageResponse imageResponses = imageResponse.get(position);
                        Intent intent = new Intent(mainview.mainactivity(), PreviewActivity.class);
                        intent.putExtra("preview", imageResponses);
                        mainview.mainactivity().startActivity(intent);

                    }
                })
        );
        Log.d("welcome", mainview.imageResponsef().toString());
    }


    @Override
    public void showad() {
        AdRequest adRequest = new AdRequest.Builder().build();
        mainview.mAdView().loadAd(adRequest);
        mainview.mInterstitialAd().setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mainview.mInterstitialAd().loadAd(new AdRequest.Builder().build());
        mainview.mInterstitialAd().setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
//                  testPhoto();
                mainview.mInterstitialAd().show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                mainview.mInterstitialAd().loadAd(new AdRequest.Builder().build());
                mainview.mInterstitialAd().show();

            }

            @Override
            public void onAdOpened() {
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

                // Code to be executed when when the interstitial ad is closed.
                Log.i("Ads", "onAdClosed");
            }
        });
    }

    @Override
    public void retrofit() {

        RequestInterface requestInterface = new Retrofit.Builder()
                .baseUrl(baseurl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RequestInterface.class);
        compositeDisposable.add(requestInterface.register(pagenumber)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    private void handleError(Throwable throwable) {
    }

    private void handleResponse(Example example) {
        imageResponse = (ArrayList<ImageResponse>) example.getHits();

        Disposable disposable = Observable.fromArray(imageResponse).concatMap(Observable::just).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ArrayList<ImageResponse>>() {
            @Override
            public void accept(ArrayList<ImageResponse> movieLists) throws Exception {

                mainview.imageAdapter().addItems(movieLists);
                mainview.imageAdapter().notifyDataSetChanged();
                loading = false;
            }
        });
        compositeDisposable.add(disposable);

        mainview.paginator().onNext(pagenumber);

    }


    @Override
    public void search() {
        mainview.editText().setOnEditorActionListener((textView, i, keyEvent) -> {

            if (i == EditorInfo.IME_ACTION_SEARCH) {

                Intent intent1 = new Intent(mainview.mainactivity(), SearchActivity.class);
                intent1.putExtra("search", mainview.editText().getText().toString());

                mainview.mainactivity().startActivity(intent1);
                mainview.editText().setText("");

                return true;
            }
            return false;
        });
    }


    @Override
    public void dailywallpaper(AlertDialog.Builder dialogBuilder) {


        Log.d("dailywallpaper", "clicked");

        dialogBuilder = new AlertDialog.Builder(mainview.mainactivity(), R.style.MyAlertDialogStyle);
        dialogBuilder.setTitle("Daily Wallpaper");
        dialogBuilder.setMessage("Daily Wallpaper Is Going To Change Your Wallpaper Every New Day");
        dialogBuilder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                mainview.myshredpref().savename(true);
                Log.d(String.valueOf(mainview.myshredpref().getname()), "value");
                setalarm(mainview.mainactivity());
                Log.d("dailywallpaper", "clicked");
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });


        AlertDialog b = dialogBuilder.create();
        b.show();
    }


    @Override
    public void setalarm(Context context) {
        AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, UpdateReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);


// Set the alarm to start at 21:32 PM


        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 23
        );
        calendar.set(Calendar.MINUTE, 59);

        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);
    }

    @Override
    public void setView(MainModule.mainview view) {
        this.mainview = view;
    }


}
