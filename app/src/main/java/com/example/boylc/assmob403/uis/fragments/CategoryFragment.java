package com.example.boylc.assmob403.uis.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.boylc.assmob403.R;
import com.example.boylc.assmob403.adapter.CategoryAdapter;
import com.example.boylc.assmob403.model.Category;
import com.example.boylc.assmob403.model.CategoryResponse;
import com.example.boylc.assmob403.rest.ApiClient;
import com.example.boylc.assmob403.rest.ApiInterface;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {

    private RecyclerView rvCategory;

    private CategoryAdapter categoryAdapter;

    private ArrayList<Category> categories;

    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getAllCategory();
    }

    private void getAllCategory() {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<CategoryResponse> call = apiService.getCategory();
        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                categories.addAll(response.body().getCategories());
                categoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {

            }
        });
    }

    private void initViews(View view) {
        rvCategory = view.findViewById(R.id.rvCategory);
        categories = new ArrayList<>();
        // Create adapter passing in the sample user data
        categoryAdapter = new CategoryAdapter(getActivity(), categories);
        // Attach the adapter to the recyclerview to populate items
        rvCategory.setAdapter(categoryAdapter);
        // Set layout manager to position the items
        rvCategory.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

}
