package com.example.boylc.assmob403.uis.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ToxicBakery.viewpager.transforms.ZoomOutSlideTransformer;
import com.example.boylc.assmob403.R;
import com.example.boylc.assmob403.adapter.FavoritePagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesFragment extends Fragment {
    private TabLayout tabLayout;
    private FavoritePagerAdapter adapter;
    private ViewPager viewPager;

    public FavoritesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initViews(View view) {
        // Give the TabLayout the ViewPager
        viewPager = view.findViewById(R.id.viewpager);
        adapter = new FavoritePagerAdapter(getChildFragmentManager(),
                getActivity());
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(true, new ZoomOutSlideTransformer());
        tabLayout = view.findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

}
