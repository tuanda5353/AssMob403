package com.example.boylc.assmob403.uis.fragments;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ToxicBakery.viewpager.transforms.ZoomOutSlideTransformer;
import com.example.boylc.assmob403.R;
import com.example.boylc.assmob403.adapter.GifsAdapter;
import com.example.boylc.assmob403.adapter.ImagePager;
import com.example.boylc.assmob403.common.Constant;
import com.example.boylc.assmob403.database.HdwallPaperDatabaseHelper;
import com.example.boylc.assmob403.model.Gifs;
import com.example.boylc.assmob403.model.GifsResponse;
import com.example.boylc.assmob403.rest.ApiClient;
import com.example.boylc.assmob403.rest.ApiInterface;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class GifsFragment extends Fragment {
    private RecyclerView rvGift;
    private GifsAdapter adapter;
    private ArrayList<Gifs> mGifs;

    public GifsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gift, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getAllGiftFromApi();
    }

    private void getAllGiftFromApi() {
        Log.d("getData", "getLastestImageFromApi: ");
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<GifsResponse> call = apiService.getAllGift();
        call.enqueue(new Callback<GifsResponse>() {
            @Override
            public void onResponse(Call<GifsResponse> call, Response<GifsResponse> response) {
                if (Constant.isDebug)
                    Log.d("Data", "onResponse: " + response.body());
                Log.d("Data", "onResponse: " + response.body().getHDWALLPAPER());
                mGifs.addAll(response.body().getHDWALLPAPER());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<GifsResponse> call, Throwable t) {

            }
        });

    }

    private void initViews(View view) {

        rvGift = view.findViewById(R.id.rvGift);
        mGifs = new ArrayList<>();
        // Create adapter passing in the sample user data
        adapter = new GifsAdapter(getActivity(), mGifs);
        // Attach the adapter to the recyclerview to populate items
        rvGift.setAdapter(adapter);
        // Set layout manager to position the items
        rvGift.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    }


}
