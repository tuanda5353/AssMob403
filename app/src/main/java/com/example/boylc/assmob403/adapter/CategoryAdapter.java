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
import com.example.boylc.assmob403.GlideApp;
import com.example.boylc.assmob403.R;
import com.example.boylc.assmob403.common.Constant;
import com.example.boylc.assmob403.model.Category;
import com.example.boylc.assmob403.uis.fragments.AllPhotoOfCategoryFragment;

import java.util.ArrayList;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Category> mCategories;

    public CategoryAdapter(Context mContext, ArrayList<Category> mCategories) {
        this.mContext = mContext;
        this.mCategories = mCategories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);

        // Inflate the custom layout
        View imageView = inflater.inflate(R.layout.item_category, parent, false);

        // Return a new holder instance
        return new ViewHolder(imageView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = mCategories.get(position);
        GlideApp.with(mContext)
                .load(category.getCategoryImage())
//                .placeholder(placeholder)
                .centerCrop()
                .into(holder.imgCategory);
        holder.txtNameCategory.setText(category.getCategoryName()+" ( "+category.getTotalWallpaper()+" )");
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imgCategory;
        private TextView txtNameCategory;
//        private TextView txtTotalImage;



        public ViewHolder(View itemView) {
            super(itemView);
            imgCategory = itemView.findViewById(R.id.img_category);
            txtNameCategory = itemView.findViewById(R.id.txt_name_category);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //todo
            String id = mCategories.get(getAdapterPosition()).getCid();
            String name = mCategories.get(getAdapterPosition()).getCategoryName();
            if (Constant.isDebug)
            Log.d("getAllPhoto", "onClick: ");
            ((FragmentActivity) v.getContext()).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.flContent, AllPhotoOfCategoryFragment.newInstance(id,name))
                    .addToBackStack("AllPhotoOfCategory")
                    .commit();
        }
    }
}
