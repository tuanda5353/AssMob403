package com.example.boylc.assmob403.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Gifs {

    @SerializedName("gif_image")
    @Expose
    private String gifImage;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("total_views")
    @Expose
    private String totalViews;

    public String getGifImage() {
        return gifImage;
    }

    public void setGifImage(String gifImage) {
        this.gifImage = gifImage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTotalViews() {
        return totalViews;
    }

    public void setTotalViews(String totalViews) {
        this.totalViews = totalViews;
    }

}
