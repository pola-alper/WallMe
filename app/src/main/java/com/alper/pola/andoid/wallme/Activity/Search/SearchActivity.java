package com.alper.pola.andoid.wallme.Activity.Search;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alper.pola.andoid.wallme.Adapter.ImageAdapter;
import com.alper.pola.andoid.wallme.R;
import com.alper.pola.andoid.wallme.Storage.MySharedPref;
import com.alper.pola.andoid.wallme.model.ImageResponse;
import com.alper.pola.andoid.wallme.util.Constants;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity implements SearchModule.searchview{
    @BindView(R.id.Recyclerview)
    RecyclerView recyclerView;
    AlertDialog.Builder dialogBuilder;
    @BindView(R.id.searchedt)
    EditText editText;
    ArrayList<ImageResponse> imageResponsef = new ArrayList<>();
    String source;
    MySharedPref mySharedPref;
    StringBuffer searchlist;
    @BindView(R.id.searchtext)
    TextView searchtext;
    @BindView(R.id.rl_dailyWallpaper)
    RelativeLayout dailyWallpaper;
    @BindView(R.id.adView)
    AdView mAdView;
    SearchModule.searchpresenter searchPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        setSharedPref();
searchPresenter = SearchPresenter.getpresenter(this);

        final ImageAdapter adapter = new ImageAdapter(imageResponsef, this);
        recyclerView.setAdapter(adapter);
        runOnUiThread(new Runnable() {
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
        source = Constants.search;
        searchPresenter.setcardlist();
        Bundle bundle = getIntent().getExtras();
        String link = bundle.getString("search", null);
        searchlist = new StringBuffer();
        searchlist.append(editText.getText().toString());
        Constants.search = Constants.search + link;
        searchPresenter.search();
        if (imageResponsef.isEmpty()) {
            searchtext.setText("There Is No Search Under: " + link);

        }
        dailyWallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchPresenter.dailywallpaper(dialogBuilder);


            }
        });
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    private void setSharedPref() {
        mySharedPref = MySharedPref.getInstance();
        mySharedPref.setSharedPreferences(SearchActivity.this);
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
    public MySharedPref mySharedPref() {
        return mySharedPref;
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
    public SearchActivity searchactivity() {
        return SearchActivity.this;
    }

    @Override
    public MySharedPref myshredpref() {
        return mySharedPref;
    }
}
