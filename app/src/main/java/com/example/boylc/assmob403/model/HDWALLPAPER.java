package com.example.boylc.assmob403.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HDWALLPAPER {

    @SerializedName("cat_id")
    @Expose
    private String catId;

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

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("total_views")
    @Expose
    private String totalViews;

    @SerializedName("wallpaper_image")
    @Expose
    private String wallpaperImage;

    @SerializedName("wallpaper_image_thumb")
    @Expose
    private String wallpaperImageThumb;

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

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

    public String getWallpaperImage() {
        return wallpaperImage;
    }

    public void setWallpaperImage(String wallpaperImage) {
        this.wallpaperImage = wallpaperImage;
    }

    public String getWallpaperImageThumb() {
        return wallpaperImageThumb;
    }

    public void setWallpaperImageThumb(String wallpaperImageThumb) {
        this.wallpaperImageThumb = wallpaperImageThumb;
    }

}
