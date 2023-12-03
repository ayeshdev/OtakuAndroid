package com.otaku.otaku.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Products> productList;

    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
            }

            @Override
            public void onFailure(@NonNull Call<DataResponse> call, @NonNull Throwable t) {
                Log.e("API Error", "Failed to fetch products: " + t.getMessage());
            }
        });
    }

    private void setupRecyclerView() {
        if (productList != null) {
            productAdapter = new ProductAdapter(getContext(), productList);
            recyclerView.setAdapter(productAdapter);
        }
    }
}