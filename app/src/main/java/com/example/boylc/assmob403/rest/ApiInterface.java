package com.example.boylc.assmob403.rest;

import com.example.boylc.assmob403.model.LastestResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("api.php?latest")
    Call<LastestResponse> getLastest();
}
