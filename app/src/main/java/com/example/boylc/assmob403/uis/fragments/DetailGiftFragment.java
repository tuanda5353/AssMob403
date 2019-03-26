package com.example.boylc.assmob403.uis.fragments;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.ToxicBakery.viewpager.transforms.ZoomOutSlideTransformer;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.boylc.assmob403.R;
import com.example.boylc.assmob403.adapter.GifsPager;
import com.example.boylc.assmob403.database.HdwallPaperDatabaseHelper;
import com.example.boylc.assmob403.model.Gifs;
import com.example.boylc.assmob403.witget.CustomViewPager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class DetailGiftFragment extends Fragment {

    private GifsPager adapter;

    private ArrayList<Gifs> mGifs;

    private CustomViewPager imgPagerLastest;

    private FloatingActionButton fbtnMore;

    private int position;

    private com.github.clans.fab.FloatingActionMenu menuRed;

    private com.github.clans.fab.FloatingActionButton fabFavorite;

    private com.github.clans.fab.FloatingActionButton fabShare;

    private com.github.clans.fab.FloatingActionButton fabSave;

    private com.github.clans.fab.FloatingActionButton fabSetAsWallpaper;

    private HdwallPaperDatabaseHelper databaseHelper;

    public DetailGiftFragment() {
    }

    public DetailGiftFragment(ArrayList<Gifs> mGifs, int position) {
        // Required empty public constructor
        this.mGifs = mGifs;
        this.position = position;
        Log.d("getData", "DetailImageFragment: " + position);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_gift, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initVariables();

    }

    private void initVariables() {
        imgPagerLastest.setAdapter(adapter);
        imgPagerLastest.setCurrentItem(position);
        imgPagerLastest.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        fabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(getActivity())
                        .asBitmap()
                        .load(mGifs.get(imgPagerLastest.getCurrentItem()).getGifImage())
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(@NonNull Bitmap resource,
                                    @Nullable Transition<? super Bitmap> transition) {
                                Log.d("fabShare",
                                        "onClick: " + mGifs.get(imgPagerLastest.getCurrentItem()).getGifImage());
                                if (isStoragePermissionGranted()) {
                                    saveImage(resource);
                                }
                            }
                        });
            }
        });

        fabShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(getActivity())
                        .asBitmap()
                        .load(mGifs.get(imgPagerLastest.getCurrentItem()).getGifImage())
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(@NonNull Bitmap resource,
                                    @Nullable Transition<? super Bitmap> transition) {
                                Log.d("fabShare",
                                        "onClick: " + mGifs.get(imgPagerLastest.getCurrentItem()).getGifImage());
                                shareImage(resource);
                            }
                        });
            }
        });
        fabSetAsWallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(getActivity())
                        .asBitmap()
                        .load(mGifs.get(imgPagerLastest.getCurrentItem()).getGifImage())
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(@NonNull Bitmap resource,
                                    @Nullable Transition<? super Bitmap> transition) {
                                Log.d("fabShare",
                                        "onClick: " + mGifs.get(imgPagerLastest.getCurrentItem()).getGifImage());
                                //todo
//                                setImageAsWallpaper(resource);
                            }
                        });
            }
        });
        fabFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("getGifs", "onClick: " + mGifs.get(imgPagerLastest.getCurrentItem()));
                databaseHelper.addOrUpdateGift(mGifs.get(imgPagerLastest.getCurrentItem()));
            }
        });
    }

    private void initViews(View view) {
        imgPagerLastest = view.findViewById(R.id.img_pager_lastest);
        adapter = new GifsPager(getChildFragmentManager(), mGifs);
        imgPagerLastest.setPageTransformer(true, new ZoomOutSlideTransformer());
        menuRed = view.findViewById(R.id.menu_red);
        fabFavorite = view.findViewById(R.id.fab_favorite);
        fabShare = view.findViewById(R.id.fab_share);
        fabSave = view.findViewById(R.id.fab_save);
        fabSetAsWallpaper = view.findViewById(R.id.fab_set_as_wallpaper);
        databaseHelper = HdwallPaperDatabaseHelper.getInstance(getActivity());
    }

    private void shareImage(Bitmap resource) {

        Uri bmpUri = getBitmapFromDrawable(resource);
        if (bmpUri != null) {
            // Construct a ShareIntent with link to image
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
            shareIntent.setType("image/*");
            // Launch sharing dialog for image
            startActivity(Intent.createChooser(shareIntent, "Share Image"));
        } else {
            // ...sharing failed, handle error
        }
    }

    //    private void setImageAsWallpaper(Bitmap resource) {
//
//        Uri bmpUri = getBitmapFromDrawable(resource);
//        if (bmpUri != null) {
//            Intent intent = new Intent(Intent.ACTION_ATTACH_DATA);
//            intent.setDataAndType(bmpUri, "image/*");
//            intent.putExtra("jpg", "image/*");
//            startActivityForResult(Intent.createChooser(intent,
////                    getString(R.string.set_as)), REQUEST_ID_SET_AS_WALLPAPER);
//        } else {
//            // ...sharing failed, handle error
//        }
//    }
    public Uri getBitmapFromDrawable(Bitmap bmp) {

        // Store image to default external storage directory
        Uri bmpUri = null;
        try {
            // Use methods on Context to access package-specific directories on external storage.
            // This way, you don't need to request external read/write permission.
            // See https://youtu.be/5xVh-7ywKpE?t=25m25s
            File file = new File(getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                    "share_image_" + System.currentTimeMillis() + ".png");
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();

            // wrap File object into a content provider. NOTE: authority here should match authority in manifest declaration
            bmpUri = FileProvider.getUriForFile(getActivity(), "com.example.boylc.assmob403",
                    file);  // use this version for API >= 24

            // **Note:** For API < 24, you may use bmpUri = Uri.fromFile(file);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }


    private String saveImage(Bitmap image) {
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String savedImagePath = null;

        String imageFileName = "JPEG_" + n + ".jpg";
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                + "/Gifs");
        boolean success = true;
        if (!storageDir.exists()) {
            success = storageDir.mkdirs();
        }
        if (success) {
            File imageFile = new File(storageDir, imageFileName);
            savedImagePath = imageFile.getAbsolutePath();
            try {
                OutputStream fOut = new FileOutputStream(imageFile);
                image.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
                fOut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Add the image to the system gallery
            galleryAddPic(savedImagePath);
            Toast.makeText(getActivity(), "IMAGE SAVED", Toast.LENGTH_LONG).show();
        }
        return savedImagePath;
    }

    private void galleryAddPic(String imagePath) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(imagePath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        getActivity().sendBroadcast(mediaScanIntent);
    }

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (getActivity().checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.d("fabSave", "Permission is granted");
                return true;
            } else {

                Log.d("fabSave", "Permission is revoked");
                ActivityCompat
                        .requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.d("fabSave", "Permission is granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.v("fabShare", "Permission: " + permissions[0] + "was " + grantResults[0]);
            //resume tasks needing this permission
        }
    }


}
