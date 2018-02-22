package com.alper.pola.andoid.wallme.model;

/**
 * Created by shashank on 01-Dec-17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Example {

    @SerializedName("totalHits")
    @Expose
    private Integer totalHits;
    @SerializedName("hits")
    @Expose
    private List<ImageResponse> hits = null;
    @SerializedName("total")
    @Expose
    private Integer total;

    public Integer getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(Integer totalHits) {
        this.totalHits = totalHits;
    }

    public List<ImageResponse> getHits() {
        return hits;
    }

    public void setHits(List<ImageResponse> hits) {
        this.hits = hits;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

}