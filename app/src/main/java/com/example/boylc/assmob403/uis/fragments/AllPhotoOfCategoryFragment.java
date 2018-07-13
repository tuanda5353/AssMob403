package com.example.boylc.assmob403.uis.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
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
public class AllPhotoOfCategoryFragment extends Fragment {

    private RecyclerView rvAllPhotoOfCategory;

    private LastestAdapter adapter;

    private ArrayList<HDWALLPAPER> photosOfCategory;

    ActionBar actionBar;

    public static AllPhotoOfCategoryFragment newInstance(String id, String name) {
        Bundle args = new Bundle();
        args.putString("id", id);
        args.putString("nameCategory", name);
        AllPhotoOfCategoryFragment fragment = new AllPhotoOfCategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public AllPhotoOfCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_photo_of_category, container, false);
        initViews(view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getAllPhotoOfCategory();
    }

    private void getAllPhotoOfCategory() {
        String id = getArguments().getString("id", "-1");
        String name = getArguments().getString("nameCategory", "");
//        actionBar.setTitle(name);
//        actionBar.setHomeButtonEnabled(true);
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<LastestResponse> call = apiService.getAllPhotoOfCategory(id);
        call.enqueue(new Callback<LastestResponse>() {
            @Override
            public void onResponse(Call<LastestResponse> call, Response<LastestResponse> response) {
                if (Constant.isDebug) {
                    Log.d("getAllPhoto", "onResponse: " + response.body().getHDWALLPAPER());
                }
                photosOfCategory.addAll(response.body().getHDWALLPAPER());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<LastestResponse> call, Throwable t) {
                if (Constant.isDebug) {
                    Log.d("getAllPhoto", "onResponse: " + t);
                }
            }
        });
    }

    private void initViews(View view) {
        rvAllPhotoOfCategory = view.findViewById(R.id.rv_all_photo_of_category);
        photosOfCategory = new ArrayList<>();
        // Create adapter passing in the sample user data
        adapter = new LastestAdapter(getActivity(), photosOfCategory);
        // Attach the adapter to the recyclerview to populate items
        rvAllPhotoOfCategory.setAdapter(adapter);
        // Set layout manager to position the items
        rvAllPhotoOfCategory.setLayoutManager(new GridLayoutManager(getActivity(), 2));
//        actionBar = ((MainActivity) getActivity()).getSupportActionBar();

    }

}
