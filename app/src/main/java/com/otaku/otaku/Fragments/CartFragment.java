package com.otaku.otaku.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.otaku.otaku.Adapters.CartAdapter;
import com.otaku.otaku.Adapters.ProductAdapter;
import com.otaku.otaku.R;
import com.otaku.otaku.api.ApiClient;
import com.otaku.otaku.api.ApiService;
import com.otaku.otaku.dao.CartDao;
import com.otaku.otaku.entity.Cart;
import com.otaku.otaku.model.DataResponse;
import com.otaku.otaku.model.Products;
import com.otaku.otaku.util.AppDatabase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartFragment extends Fragment {

    private static String TAG = CartFragment.class.getName();
    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private List<Cart> cartList;


    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        recyclerView = view.findViewById(R.id.recyclerCartViewProducts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        fetchData();
        return view;
    }


    private void fetchData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase db = Room.databaseBuilder(requireContext().getApplicationContext(),
                        AppDatabase.class, "otaku").build();

                CartDao cartDao = db.cartDao();

                cartList = cartDao.getAll();

                // Update UI on the main thread using requireActivity().runOnUiThread
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setupRecyclerView(cartList);
                    }
                });


            }
        }).start();
    }

    private void setupRecyclerView(List<Cart> cartList) {
        if (cartList != null) {
            cartAdapter = new CartAdapter(getContext(), cartList);
            recyclerView.setAdapter(cartAdapter);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}