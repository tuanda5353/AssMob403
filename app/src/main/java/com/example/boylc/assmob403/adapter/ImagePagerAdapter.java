package com.example.boylc.assmob403.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.boylc.assmob403.GlideApp;
import com.example.boylc.assmob403.R;
import com.example.boylc.assmob403.model.HDWALLPAPER;
import java.util.ArrayList;

public class ImagePagerAdapter extends PagerAdapter {

    private LayoutInflater layoutInflater;

    private Context mContext;

    private ArrayList<HDWALLPAPER> mHdwallpapers;


    public ImagePagerAdapter(ArrayList<HDWALLPAPER> mHdwallpapers, Context mContext) {
        this.mHdwallpapers = mHdwallpapers;
        this.mContext = mContext;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return mHdwallpapers.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_image_pager, container, false);

        ImageView imgDetail = view.findViewById(R.id.img_detail);

        HDWALLPAPER hdwallpaper = mHdwallpapers.get(position);

        GlideApp.with(mContext)
                .load(mHdwallpapers.get(5).getCategoryImage())
//                .placeholder(placeholder)
                .into(imgDetail);
        container.addView(view);

        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((View) object);
    }
}
