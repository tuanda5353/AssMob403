package com.example.boylc.assmob403.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.boylc.assmob403.GlideApp;
import com.example.boylc.assmob403.R;
import com.example.boylc.assmob403.model.Category;
import com.example.boylc.assmob403.uis.fragments.AllPhotoOfCategoryFragment;
import java.util.ArrayList;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imgCategory;

        private ProgressBar mProgressBar;

        private TextView txtNameCategory;

        public ViewHolder(View itemView) {
            super(itemView);
            imgCategory = itemView.findViewById(R.id.img_category);
            txtNameCategory = itemView.findViewById(R.id.txt_name_category);
            mProgressBar = itemView.findViewById(R.id.progress_category);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String id = mCategories.get(getAdapterPosition()).getCid();
            String name = mCategories.get(getAdapterPosition()).getCategoryName();
            ((AppCompatActivity) mContext).getSupportActionBar().setTitle(name);
            ((AppCompatActivity) mContext).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((FragmentActivity) v.getContext()).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.flContent, AllPhotoOfCategoryFragment.newInstance(id, name))
                    .addToBackStack("AllPhotoOfCategory")
                    .commit();
        }
    }

    private ArrayList<Category> mCategories;

    private Context mContext;

    public CategoryAdapter(Context mContext, ArrayList<Category> mCategories) {
        this.mContext = mContext;
        this.mCategories = mCategories;
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Category category = mCategories.get(position);
        GlideApp.with(mContext)
                .load(category.getCategoryImage())
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
                .centerCrop()
                .into(holder.imgCategory);
        holder.txtNameCategory.setText(category.getCategoryName() + " ( " + category.getTotalWallpaper() + " )");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View imageView = inflater.inflate(R.layout.item_category, parent, false);
        return new ViewHolder(imageView);

    }
}
