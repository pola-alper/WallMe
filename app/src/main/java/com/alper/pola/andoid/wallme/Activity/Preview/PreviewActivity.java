package com.alper.pola.andoid.wallme.Activity.Preview;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import com.alper.pola.andoid.wallme.App.WallMeApplication;
import com.alper.pola.andoid.wallme.R;
import com.alper.pola.andoid.wallme.model.ImageResponse;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PreviewActivity extends AppCompatActivity implements PreviewModule.previewView {
    @BindView(R.id.previewimg)
    ImageView imageView;
    @BindView(R.id.adView)
    AdView mAdView;
    private ImageResponse imageResponse;
    private ProgressDialog progressDialog;
    private InterstitialAd mInterstitialAd;
    @Inject
  PreviewPresenter previewPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_);
        ((WallMeApplication) getApplication()).getAppComponent().inject(this);
        ButterKnife.bind(this);
        setProgressDialog();
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        Intent intent = getIntent();
        //previewPresenter = new PreviewPresenter(this);
        imageResponse = (ImageResponse) intent.getSerializableExtra("preview");
        mInterstitialAd = new InterstitialAd(this);
        previewPresenter.setView(this);
        previewPresenter.showAd();
        previewPresenter.loadPhotos();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.set:
                progressDialog.show();
                previewPresenter.download();

                return true;
            case R.id.reject:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public ImageResponse imageResponse() {
        return imageResponse;
    }

    @Override
    public ProgressDialog progressDialog() {
        return progressDialog;
    }

    @Override
    public InterstitialAd mInterstitialAd() {
        return mInterstitialAd;
    }

    @Override
    public PreviewActivity previewActivity() {
        return PreviewActivity.this;
    }

    @Override
    public ImageView imageview() {
        return imageView;
    }


    @Override
    public StringBuffer searchlist() {
        return searchlist();
    }

    public void setProgressDialog() {
        progressDialog = new ProgressDialog(PreviewActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Setting New Wallpaper");

    }
}








