package com.example.boylc.assmob403.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
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
import com.example.boylc.assmob403.model.Gifs;
import com.example.boylc.assmob403.uis.fragments.DetailGiftFragment;
import java.util.ArrayList;


public class GifsAdapter extends RecyclerView.Adapter<GifsAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imgThumb;

        private TextView txtTotalLike;

        private TextView txtTotalView;


        public ViewHolder(View itemView) {
            super(itemView);
            imgThumb = itemView.findViewById(R.id.img_thumb);
            txtTotalView = itemView.findViewById(R.id.txt_total_view);
            txtTotalLike = itemView.findViewById(R.id.txt_total_like);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d("getData", "onClick: " + getAdapterPosition());
            detailImageFragment = new DetailGiftFragment(mGifs, getAdapterPosition());
            ((FragmentActivity) v.getContext()).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.flContent, detailImageFragment)
                    .addToBackStack("Detail")
                    .commit();
        }
    }

    private DetailGiftFragment detailImageFragment;

    private Context mContext;

    private ArrayList<Gifs> mGifs;

    //todo interface
    public GifsAdapter(Context mContext, ArrayList<Gifs> mGifs) {
        this.mContext = mContext;
        this.mGifs = mGifs;
    }

    @Override
    public int getItemCount() {
        return mGifs.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Gifs gifs = mGifs.get(position);
        Glide.with(mContext)
                .load(gifs.getGifImage())
                .into(holder.imgThumb);
        holder.txtTotalView.setText(gifs.getTotalViews());
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
