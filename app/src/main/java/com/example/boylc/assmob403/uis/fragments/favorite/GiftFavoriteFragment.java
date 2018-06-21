package com.example.boylc.assmob403.uis.fragments.favorite;


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
import com.example.boylc.assmob403.adapter.GifsAdapter;
import com.example.boylc.assmob403.common.Constant;
import com.example.boylc.assmob403.database.HdwallPaperDatabaseHelper;
import com.example.boylc.assmob403.model.Gifs;
import com.example.boylc.assmob403.model.GifsResponse;
import com.example.boylc.assmob403.model.HDWALLPAPER;
import com.example.boylc.assmob403.rest.ApiClient;
import com.example.boylc.assmob403.rest.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class GiftFavoriteFragment extends Fragment {
    private RecyclerView rvGift;
    private GifsAdapter adapter;
    private ArrayList<Gifs> mGifs;
    private HdwallPaperDatabaseHelper databaseHelper;

    public GiftFavoriteFragment() {
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
        getAllGiftFromDatabase();
    }

    private void getAllGiftFromDatabase() {
        databaseHelper = HdwallPaperDatabaseHelper.getInstance(getActivity());
        mGifs.addAll(databaseHelper.getAllGifs());
        adapter.notifyDataSetChanged();
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
