package com.alper.pola.andoid.wallme.Activity.Preview;

import android.app.ProgressDialog;
import android.widget.ImageView;

import com.alper.pola.andoid.wallme.model.ImageResponse;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.concurrent.ExecutionException;

/**
 * Created by shashank on 16-Oct-17.
 */

public interface PreviewModule {
     interface previewView{
         ImageResponse imageResponse();
         ProgressDialog progressDialog();
          InterstitialAd mInterstitialAd();
         PreviewActivity previewActivity();
         ImageView imageview();

         StringBuffer searchlist();
    }
   interface previewPresenter{
       void loadPhotos();
       void showAd();
       void download() throws ExecutionException, InterruptedException;

       void setView(PreviewModule.previewView view);


   }
}
