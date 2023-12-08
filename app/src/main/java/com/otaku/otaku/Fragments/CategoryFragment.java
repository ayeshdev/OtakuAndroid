package com.otaku.otaku.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.material.tabs.TabLayout;
import com.otaku.otaku.Adapters.ProductAdapter;
import com.otaku.otaku.Adapters.ProductsAdapter;
import com.otaku.otaku.R;
import com.otaku.otaku.api.ApiClient;
import com.otaku.otaku.api.ApiService;
import com.otaku.otaku.model.Attributes;
import com.otaku.otaku.model.DataResponse;
import com.otaku.otaku.model.Products;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Products> productList;

    TabLayout tabLayout;

    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecyclerView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewProducts);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setNestedScrollingEnabled(false);
        fetchData();

        return view;
    }

    private void fetchData() {
        ApiService apiService = ApiClient.ApiConnection().create(ApiService.class);
        Call<DataResponse> call = apiService.getProducts();

        call.enqueue(new Callback<DataResponse>() {

            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                if (response.isSuccessful()) {
                    DataResponse dataResponse = response.body();
                    if (dataResponse != null) {
                        productList = dataResponse.getData();
                        setupRecyclerView();
                    } else {
                        Log.e("API Error", "Null response body");
                    }
                } else {
                    Log.e("API Error", "Unsuccessful response: " + response.message());
                }

                String responseData = response.body()
                        .getData()
                        .get(0)
                        .getAttribute()
                        .getCategoryResponse()
                        .getData()
                        .getCategoryAttributes()
                        .getName();

                System.out.println("PRODUCT DATA: "+responseData);
            }

            @Override
            public void onFailure(@NonNull Call<DataResponse> call, @NonNull Throwable t) {
                Log.e("API Error", "Failed to fetch products: " + t.getMessage());
            }
        });
    }

    // Implement the interface method to handle clicks


    private void setupRecyclerView() {
        if (productList != null) {
            productAdapter = new ProductAdapter(getContext(), productList);
            recyclerView.setAdapter(productAdapter);
        }
    }

    public void imageClick (){
        Log.i("KOSKCOS","kocksocksco");
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}