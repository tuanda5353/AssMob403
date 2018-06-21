package com.example.boylc.assmob403.uis.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.boylc.assmob403.R;
import com.example.boylc.assmob403.adapter.LastestAdapter;
import com.example.boylc.assmob403.common.Constant;
import com.example.boylc.assmob403.model.HDWALLPAPER;
import com.example.boylc.assmob403.model.LastestResponse;
import com.example.boylc.assmob403.rest.ApiClient;
import com.example.boylc.assmob403.rest.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LastestFragment extends Fragment {
    private RecyclerView rvLastest;
    private LastestAdapter adapter;
    private ArrayList<HDWALLPAPER> lastests;

    public LastestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lastest, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {


        rvLastest = view.findViewById(R.id.rvLastest);
        lastests = new ArrayList<>();
        // Create adapter passing in the sample user data
        adapter = new LastestAdapter(getActivity(), lastests);
        // Attach the adapter to the recyclerview to populate items
        rvLastest.setAdapter(adapter);
        // Set layout manager to position the items
        rvLastest.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getLastestImageFromApi();
    }

    private void getLastestImageFromApi() {
        Log.d("getData", "getLastestImageFromApi: ");
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<LastestResponse> call = apiService.getLastest();
        call.enqueue(new Callback<LastestResponse>() {
            @Override
            public void onResponse(Call<LastestResponse> call, Response<LastestResponse> response) {
                if (Constant.isDebug)
                    Log.d("Data", "onResponse: " + response.body());
                Log.d("Data", "onResponse: " + response.body().getHDWALLPAPER().get(0).getTotalViews());
                lastests.addAll(response.body().getHDWALLPAPER());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<LastestResponse> call, Throwable t) {

            }
        });
    }

}
