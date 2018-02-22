package com.alper.pola.andoid.wallme.Activity.Search;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.alper.pola.andoid.wallme.Activity.Preview.PreviewActivity;
import com.alper.pola.andoid.wallme.Adapter.ImageAdapter;
import com.alper.pola.andoid.wallme.R;
import com.alper.pola.andoid.wallme.Service.UpdateReceiver;
import com.alper.pola.andoid.wallme.model.ImageResponse;
import com.alper.pola.andoid.wallme.model.Wallme;
import com.alper.pola.andoid.wallme.util.Constants;
import com.alper.pola.andoid.wallme.util.EndLessScroll;
import com.alper.pola.andoid.wallme.util.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by shashank on 16-Oct-17.
 */

public class SearchPresenter implements SearchModule.searchpresenter {
    SearchModule.searchview searchview;
    public  SearchPresenter (SearchModule.searchview searchview){
        this.searchview = searchview;

    }
    public static SearchModule.searchpresenter getpresenter (SearchModule.searchview searchview){
        return new SearchPresenter(searchview);
    }

    @Override
    public void setcardlist() {
        searchview.recyclerView().setHasFixedSize(true);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(searchview.searchactivity(), 1);

        searchview.recyclerView().setLayoutManager(gridLayoutManager);
        searchview.recyclerView().setOnScrollListener(new EndLessScroll(gridLayoutManager) {
            @Override
            protected void loadMore(int current_page) {


            }
        });
        searchview.editText().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    searchview.searchtext().setText("There Is Search Under: " + searchview.editText().getText().toString());
                    StringBuffer  searchlist = new StringBuffer();

                    searchlist.append(searchview.editText().getText().toString());
                    Constants.search = Constants.search + searchlist;


                    search();


                    return true;
                }
                return false;
            }

        });

    }

    @Override
    public void search() {
        Wallme wallme = Wallme.getInstance(searchview.searchactivity());
        wallme.getSearch(searchview.searchactivity(), new Wallme.GetSearchListCallBack() {
            @Override
            public void onSuccess(final ArrayList<ImageResponse> imageResponses) {
                if (imageResponses.isEmpty()) {
                    searchview.searchtext().setVisibility(View.VISIBLE);
                    searchview.recyclerView().setVisibility(View.GONE);

                    Constants.search = searchview.source();

                } else {
                    searchview.searchtext().setVisibility(View.GONE);

                    searchview.recyclerView().setVisibility(View.VISIBLE);
                    searchview.imageResponsef().clear();
                    searchview.imageResponsef().addAll(imageResponses);
                    Constants.search = searchview.source();
                    Log.d("Constatn", Constants.search);
                    ImageAdapter adapter = new ImageAdapter(searchview.imageResponsef(), searchview.searchactivity());
                    searchview.recyclerView().setAdapter(adapter);
                    searchview.recyclerView().addOnItemTouchListener(
                            new RecyclerItemClickListener( searchview.searchactivity(), new RecyclerItemClickListener.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, final int position) {
                                    ImageResponse imageResponse = searchview.imageResponsef().get(position);
                                    Intent intent = new Intent(searchview.searchactivity(), PreviewActivity.class);
                                    intent.putExtra("preview", imageResponse);
                                    searchview.searchactivity().startActivity(intent);

                                }
                            })
                    );
                    Log.d("welcome", imageResponses.toString());
                }


            }

            @Override
            public void onFail(String message) {

            }
        });
    }

    @Override
    public void setalarm(Context context) {
        AlarmManager alarmMgr = (AlarmManager) searchview.searchactivity().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(searchview.searchactivity(), UpdateReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(searchview.searchactivity(), 0, intent, 0);


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
    public void dailywallpaper(AlertDialog.Builder dialogBuilder) {
        Log.d("dailywallpaper", "clicked");

        dialogBuilder = new AlertDialog.Builder(searchview.searchactivity(), R.style.MyAlertDialogStyle);
        dialogBuilder.setTitle("Daily Wallpaper");
        dialogBuilder.setMessage("Daily Wallpaper Is Going To Change Your Wallpaper Every New Day");
        dialogBuilder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                searchview.myshredpref().savename(true);
                Log.d(String.valueOf(searchview.myshredpref().getname()), "value");
                setalarm(searchview.searchactivity());
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
}

