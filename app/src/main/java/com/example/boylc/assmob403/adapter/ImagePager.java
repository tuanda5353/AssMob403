package com.example.boylc.assmob403.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import com.example.boylc.assmob403.model.HDWALLPAPER;
import com.example.boylc.assmob403.uis.fragments.ShowImageFragment;
import java.util.ArrayList;

public class ImagePager extends FragmentPagerAdapter {

    private ArrayList<HDWALLPAPER> mHdwallpapers;

    public ImagePager(FragmentManager fm, ArrayList<HDWALLPAPER> mHdwallpapers) {
        super(fm);
        this.mHdwallpapers = mHdwallpapers;
    }

    @Override
    public int getCount() {
        return mHdwallpapers.size();
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("getData", "getItem: " + position);
        HDWALLPAPER hdwallpaper = mHdwallpapers.get(position);
        Log.d("getData", "getItem: " + hdwallpaper.getWallpaperImage());
        return ShowImageFragment.newInstance(hdwallpaper.getWallpaperImage());
    }
}
