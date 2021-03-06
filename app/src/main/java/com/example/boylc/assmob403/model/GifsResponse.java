package com.example.boylc.assmob403.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class GifsResponse {

    @SerializedName("HD_WALLPAPER")
    @Expose
    private List<Gifs> hDWALLPAPER = null;

    public List<Gifs> getHDWALLPAPER() {
        return hDWALLPAPER;
    }

    public void setHDWALLPAPER(List<Gifs> hDWALLPAPER) {
        this.hDWALLPAPER = hDWALLPAPER;
    }

}
