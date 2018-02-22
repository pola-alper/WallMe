package com.alper.pola.andoid.wallme.util;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class EndLessScroll extends RecyclerView.OnScrollListener {
    public static String TAG = EndLessScroll.class.getSimpleName();

    private int prevTotal = 0;
    public static boolean isLoading = true;
    private int visibleThresold = 20;
    int firstVisisbleItem,visibleItemCount,totalItemCount;

    private int current_page=1;
    private LinearLayoutManager linearLayoutManager;

    public EndLessScroll(LinearLayoutManager linearLayoutManager){
        this.linearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = linearLayoutManager.getItemCount();
        firstVisisbleItem = linearLayoutManager.findFirstVisibleItemPosition();

        if(isLoading){
            if(totalItemCount>prevTotal){
                isLoading=false;
                prevTotal = totalItemCount;
            }

        }else if(!isLoading && (totalItemCount-visibleItemCount)<= (firstVisisbleItem+visibleThresold)) {
            //end reached

            current_page++;
            loadMore(current_page);
            isLoading = true;
        }
    }

    public static void setLoaded(){
        isLoading=false;
    }
    protected abstract void loadMore(int current_page);
}