package com.example.boylc.assmob403.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.boylc.assmob403.R;
import com.example.boylc.assmob403.model.HDWALLPAPER;
import com.example.boylc.assmob403.uis.fragments.DetailImageFragment;
import java.util.ArrayList;


public class LastestAdapter extends RecyclerView.Adapter<LastestAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imgThumb;

        private ProgressBar mProgressBar;

        private TextView txtTotalLike;

        private TextView txtTotalView;

        public ViewHolder(View itemView) {
            super(itemView);
            imgThumb = itemView.findViewById(R.id.img_thumb);
            txtTotalView = itemView.findViewById(R.id.txt_total_view);
            txtTotalLike = itemView.findViewById(R.id.txt_total_like);
            mProgressBar = itemView.findViewById(R.id.progress_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            detailImageFragment = new DetailImageFragment(mHdWallPapers, getAdapterPosition());
            ((FragmentActivity) v.getContext()).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.flContent, detailImageFragment)
                    .addToBackStack("Detail")
                    .commit();
        }
    }

    private DetailImageFragment detailImageFragment;

    private Context mContext;

    private ArrayList<HDWALLPAPER> mHdWallPapers;

    public LastestAdapter(Context mContext, ArrayList<HDWALLPAPER> mHdWallPapers) {
        this.mContext = mContext;
        this.mHdWallPapers = mHdWallPapers;
    }

    @Override
    public int getItemCount() {
        return mHdWallPapers.size();
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        HDWALLPAPER hdWallPaper = mHdWallPapers.get(position);
        Glide.with(mContext)
                .load(hdWallPaper.getWallpaperImageThumb())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable final GlideException e, final Object model,
                            final Target<Drawable> target,
                            final boolean isFirstResource) {
                        holder.mProgressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(final Drawable resource, final Object model,
                            final Target<Drawable> target,
                            final DataSource dataSource, final boolean isFirstResource) {
                        holder.mProgressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(holder.imgThumb);
        holder.txtTotalView.setText(hdWallPaper.getTotalViews());
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
}
