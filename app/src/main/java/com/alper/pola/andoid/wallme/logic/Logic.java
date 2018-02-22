package com.alper.pola.andoid.wallme.logic;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.alper.pola.andoid.wallme.Activity.Main.MainActivity;
import com.alper.pola.andoid.wallme.Activity.Splash.SplashActivity;
import com.alper.pola.andoid.wallme.model.ImageResponse;
import com.alper.pola.andoid.wallme.model.Wallme;
import com.alper.pola.andoid.wallme.util.Constants;

import java.util.ArrayList;

public class Logic {
    String url1 = Constants.Editor_choice;
    public static int pagenumber = 1;
    public static int pagenumber2 = 2;

    public void mainscreen(int pageNumber, final Context context) {

        Constants.Editor_choice = Constants.Editor_choice + pageNumber;
        Log.d("editorchoicemain", Constants.Editor_choice);

        Wallme wallme = Wallme.getInstance(context);
        wallme.getSearchLists(context, new Wallme.GetSearchListCallBack() {
            @Override
            public void onSuccess(final ArrayList<ImageResponse> imageResponses) {
                Constants.Editor_choice = url1;

                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("List",imageResponses);
                context.startActivity(intent);

            }


            @Override
            public void onFail(String message) {

            }
        });

    }


}