package com.example.boylc.assmob403.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class AboutUsResponse {

    @SerializedName("HD_WALLPAPER")
    @Expose
    private List<AboutUs> hDWALLPAPER = null;

    public List<AboutUs> getHDWALLPAPER() {
        return hDWALLPAPER;
    }

    public void setHDWALLPAPER(List<AboutUs> hDWALLPAPER) {
        this.hDWALLPAPER = hDWALLPAPER;
    }

}
