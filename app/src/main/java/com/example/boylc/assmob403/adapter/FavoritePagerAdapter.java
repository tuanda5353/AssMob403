package com.example.boylc.assmob403.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.boylc.assmob403.uis.fragments.favorite.GiftFavoriteFragment;
import com.example.boylc.assmob403.uis.fragments.favorite.HdWallPaperFavoriteFragment;

public class FavoritePagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 2;

    private Context context;

    private String tabTitles[] = new String[]{"HDWALLPAPER", "GIFS"};

    public FavoritePagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        fragment = null;
        switch (position) {
            case 0:
                fragment = new HdWallPaperFavoriteFragment();
                break;
            case 1:
                fragment = new GiftFavoriteFragment();
                break;
            default:
                fragment = null;
        }
        return fragment;
    }

    @Override
    public int getItemPosition(Object object) {
// POSITION_NONE makes it possible to reload the PagerAdapter
        return POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
