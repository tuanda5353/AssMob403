package com.example.boylc.assmob403.uis.fragments;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.boylc.assmob403.GlideApp;
import com.example.boylc.assmob403.R;
import com.github.chrisbanes.photoview.PhotoView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowImageFragment extends Fragment {

    private String url;

    private ProgressBar mProgressBar;

    public ShowImageFragment() {
        // Required empty public constructor
    }

    public static ShowImageFragment newInstance(String url) {

        Bundle args = new Bundle();
        args.putString("CategoryImage", url);
        ShowImageFragment fragment = new ShowImageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_image, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initVariables(view);
    }

    private void initVariables(View view) {
        PhotoView imgDetail = view.findViewById(R.id.img_detail);
        mProgressBar = view.findViewById(R.id.progress_image);
        GlideApp.with(getActivity())
                .load(url)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable final GlideException e, final Object model,
                            final Target<Drawable> target,
                            final boolean isFirstResource) {
                        mProgressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(final Drawable resource, final Object model,
                            final Target<Drawable> target,
                            final DataSource dataSource, final boolean isFirstResource) {
                        mProgressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(imgDetail);
    }

    private void initViews(View view) {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        page = getArguments().getInt("someInt", 0);
        url = getArguments().getString("CategoryImage");
    }

}
