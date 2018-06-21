package com.example.boylc.assmob403.rest;

import com.example.boylc.assmob403.model.AboutUsResponse;
import com.example.boylc.assmob403.model.CategoryResponse;
import com.example.boylc.assmob403.model.GifsResponse;
import com.example.boylc.assmob403.model.LastestResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("api.php?latest")
    Call<LastestResponse> getLastest();
    @GET("api.php?cat_list")
    Call<CategoryResponse> getCategory();
    @GET("api.php")
    Call<LastestResponse> getAllPhotoOfCategory(@Query("cat_id")String id);
    @GET("api.php")
    Call<AboutUsResponse> getAboutUs();
    @GET("api.php?gif_list")
    Call<GifsResponse> getAllGift();
}
