package com.example.boylc.assmob403.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.boylc.assmob403.R;
import com.example.boylc.assmob403.model.HDWALLPAPER;
import com.example.boylc.assmob403.uis.fragments.DetailImageFragment;

import java.util.ArrayList;


public class LastestAdapter extends RecyclerView.Adapter<LastestAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<HDWALLPAPER> mHdWallPapers;
    private  DetailImageFragment detailImageFragment;
    //todo interface
    public LastestAdapter(Context mContext, ArrayList<HDWALLPAPER> mHdWallPapers) {
        this.mContext = mContext;
        this.mHdWallPapers = mHdWallPapers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);

        // Inflate the custom layout
        View imageView = inflater.inflate(R.layout.item_image, parent, false);

        // Return a new holder instance
        return new ViewHolder(imageView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HDWALLPAPER hdWallPaper = mHdWallPapers.get(position);
        Glide.with(mContext)
                .load(hdWallPaper.getWallpaperImageThumb())
                .into(holder.imgThumb);
        holder.txtTotalView.setText(hdWallPaper.getTotalViews());
        holder.txtTotalView.setText(hdWallPaper.getTotalViews());
        if (position%5==0){
            holder.txtTotalView.setTextColor(Color.RED);
        }
    }

    @Override
    public int getItemCount() {
        return mHdWallPapers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imgThumb;
        private TextView txtTotalView;
        private TextView txtTotalLike;



        public ViewHolder(View itemView) {
            super(itemView);
            imgThumb = itemView.findViewById(R.id.img_thumb);
            txtTotalView = itemView.findViewById(R.id.txt_total_view);
            txtTotalLike = itemView.findViewById(R.id.txt_total_like);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d("getData", "onClick: "+getAdapterPosition());
            detailImageFragment= new DetailImageFragment(mHdWallPapers,getAdapterPosition());
            ((FragmentActivity) v.getContext()).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.flContent, detailImageFragment)
                    .addToBackStack("Detail")
                    .commit();
        }
    }
}
