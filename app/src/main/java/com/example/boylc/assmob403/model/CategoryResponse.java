
package com.example.boylc.assmob403.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryResponse {

    @SerializedName("HD_WALLPAPER")
    @Expose
    private List<Category> categories = null;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

}
