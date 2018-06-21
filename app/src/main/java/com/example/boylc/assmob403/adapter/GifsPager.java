package com.example.boylc.assmob403.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.boylc.assmob403.model.Gifs;
import com.example.boylc.assmob403.uis.fragments.ShowImageFragment;

import java.util.ArrayList;

public class GifsPager extends FragmentPagerAdapter {
    private ArrayList<Gifs> mGifs;

    public GifsPager(FragmentManager fm, ArrayList<Gifs> mGifs) {
        super(fm);
        this.mGifs = mGifs;
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("getData", "getItem: "+position);
        Gifs gifs = mGifs.get(position);
        Log.d("getData", "getItem: "+ gifs.getGifImage());
        return  ShowImageFragment.newInstance(gifs.getGifImage());
    }

    @Override
    public int getCount() {
        return mGifs.size();
    }
}
