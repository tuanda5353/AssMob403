package com.example.boylc.assmob403.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.boylc.assmob403.R;
import com.example.boylc.assmob403.model.HDWALLPAPER;

import java.util.ArrayList;


public class LastestAdapter extends RecyclerView.Adapter<LastestAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<HDWALLPAPER> mHdWallPapers;

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
    }

    @Override
    public int getItemCount() {
        return mHdWallPapers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgThumb;
        private TextView txtTotalView;
        private TextView txtTotalLike;



        public ViewHolder(View itemView) {
            super(itemView);
            imgThumb = itemView.findViewById(R.id.img_thumb);
            txtTotalView = itemView.findViewById(R.id.txt_total_view);
            txtTotalLike = itemView.findViewById(R.id.txt_total_like);
        }
    }
}
