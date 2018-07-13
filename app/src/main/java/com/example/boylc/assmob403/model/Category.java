package com.example.boylc.assmob403.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("category_image")
    @Expose
    private String categoryImage;

    @SerializedName("category_image_thumb")
    @Expose
    private String categoryImageThumb;

    @SerializedName("category_name")
    @Expose
    private String categoryName;

    @SerializedName("cid")
    @Expose
    private String cid;

    @SerializedName("total_wallpaper")
    @Expose
    private String totalWallpaper;

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    public String getCategoryImageThumb() {
        return categoryImageThumb;
    }

    public void setCategoryImageThumb(String categoryImageThumb) {
        this.categoryImageThumb = categoryImageThumb;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getTotalWallpaper() {
        return totalWallpaper;
    }

    public void setTotalWallpaper(String totalWallpaper) {
        this.totalWallpaper = totalWallpaper;
    }

}
