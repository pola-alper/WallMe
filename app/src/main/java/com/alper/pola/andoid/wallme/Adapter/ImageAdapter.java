package com.alper.pola.andoid.wallme.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alper.pola.andoid.wallme.Activity.Splash.SplashActivity;
import com.alper.pola.andoid.wallme.R;
import com.alper.pola.andoid.wallme.model.ImageResponse;
import com.bumptech.glide.Glide;
import com.facebook.network.connectionclass.ConnectionQuality;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by shashank on 02-Aug-17.
 */


public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ContactViewHolder>  {
    Context context;
    private List<ImageResponse> imageResponses = new ArrayList<>() ;
    public void addItems(List<ImageResponse> items) {
        this.imageResponses.addAll(items);
    }

    public ImageAdapter(ArrayList<ImageResponse> images, Context context) {
        this.context = context;
        this.imageResponses = images;

    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {

        protected ImageView img;


        public ContactViewHolder(View v) {
            super(v);
            img =  v.findViewById(R.id.wallpaper);

        }
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.row, parent, false);

        return new ContactViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final ContactViewHolder holder, int position) {





        final ImageResponse imageResponse = imageResponses.get(position);


        if (SplashActivity.mConnectionClass == ConnectionQuality.POOR){
          Glide.with(context).load(imageResponse.getPreviewURL()).into(holder.img);
             Log.d("internt spped low", "onBindViewHolder: low ");


        }else {
            Glide.with(context).load(imageResponse.getWebformatURL()).into(holder.img);
            Log.d("internt speed high", "onBindViewHolder: normal ");
        }

    }

    @Override
    public int getItemCount() {
        return imageResponses.size();
    }
}
