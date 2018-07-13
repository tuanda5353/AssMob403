package com.example.boylc.assmob403.uis.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.boylc.assmob403.R;
import com.example.boylc.assmob403.common.Constant;
import com.example.boylc.assmob403.model.AboutUs;
import com.example.boylc.assmob403.model.AboutUsResponse;
import com.example.boylc.assmob403.model.LastestResponse;
import com.example.boylc.assmob403.rest.ApiClient;
import com.example.boylc.assmob403.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUsFragment extends Fragment {
    private TextView txtAppVersion;
    private TextView txtAppAuthor;
    private TextView txtAppEmail;
    private TextView txtAppWebsite;
    private TextView txtAppContact;
    private TextView txtAppDesc;


    public AboutUsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initVariables();
    }

    private void initVariables() {
        getAboutUs();
    }

    private void getAboutUs() {
        if (Constant.isDebug)
            Log.d("getData", "getAboutUs: ");
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<AboutUsResponse> call = apiService.getAboutUs();
        call.enqueue(new Callback<AboutUsResponse>() {
            @Override
            public void onResponse(Call<AboutUsResponse> call, Response<AboutUsResponse> response) {
                if (Constant.isDebug)
                    Log.d("getAboutUs", "onResponse: " + response.body());

                AboutUs aboutUs = response.body().getHDWALLPAPER().get(0);
                txtAppVersion.setText(aboutUs.getAppVersion());
                txtAppAuthor.setText(aboutUs.getAppAuthor());
                txtAppEmail.setText(aboutUs.getAppEmail());
                txtAppWebsite.setText(aboutUs.getAppWebsite());
                txtAppContact.setText(aboutUs.getAppContact());
                txtAppDesc.setText(Html.fromHtml(aboutUs.getAppDescription()));

            }

            @Override
            public void onFailure(Call<AboutUsResponse> call, Throwable t) {

            }
        });
    }

    private void initViews(View view) {
        txtAppVersion = view.findViewById(R.id.txt_app_version);
        txtAppAuthor = view.findViewById(R.id.txt_app_author);
        txtAppEmail = view.findViewById(R.id.txt_app_email);
        txtAppWebsite = view.findViewById(R.id.txt_app_website);
        txtAppContact = view.findViewById(R.id.txt_app_contact);
        txtAppDesc = view.findViewById(R.id.txt_app_desc);

    }

}
