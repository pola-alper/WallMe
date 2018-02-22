package com.alper.pola.andoid.wallme.Activity.Search;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alper.pola.andoid.wallme.Storage.MySharedPref;
import com.alper.pola.andoid.wallme.model.ImageResponse;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

/**
 * Created by shashank on 16-Oct-17.
 */

public interface SearchModule {
    interface searchview{
        RecyclerView recyclerView();
        EditText editText();
        ArrayList<ImageResponse> imageResponsef();
        String source();
        MySharedPref mySharedPref();
        TextView searchtext();
        RelativeLayout dailyWallpaper();
        AdView mAdView();
        SearchActivity searchactivity();
        MySharedPref myshredpref();


    }
    interface searchpresenter{
        void setcardlist();
        void search();
        void setalarm(Context context);
        void dailywallpaper(AlertDialog.Builder dialogBuilder);




    }
}
