package com.otaku.otaku.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    Context context;
    ImageSlider imageSlider;

    ApiService apiService;
    RecyclerView recyclerView;

    TabLayout tabLayout;
    ViewPager2 viewPager2;

    List<Products> productsList;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageSlider = view.findViewById(R.id.imageSlider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.eren1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.eren2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.eren3, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.eren4, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.eren5, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels,ScaleTypes.FIT);

        tabLayout = view.findViewById(R.id.category_tabs);
//        viewPager2 = view.findViewById(R.id.viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager2.setCurrentItem(tab.getPosition());
                Log.d("TAB",String.valueOf(tab.getPosition()));

                if (tab.getPosition()==0){
                    Attributes product = new Attributes();
                    product.setTitle("Product 1");
                    product.setDescription("Product Description");

                    System.out.println(product.getTitle());

                }else if(tab.getPosition() == 1){

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
//        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageSelected(int position) {
//                super.onPageSelected(position);
//                tabLayout.getTabAt(position).select();
//            }
//        });
    }



    public void getAll(){
        //Call the interface
        apiService = ApiClient.ApiConnection().create(ApiService.class);

        Call<DataResponse> call = apiService.getProducts();
        call.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                DataResponse dataResponse = response.body();
                assert dataResponse != null;
                productsList = new ArrayList<>(dataResponse.getData());
                dataView(productsList);
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                Toast.makeText(context.getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void dataView(List<Products> products){
        ProductAdapter productAdapter = new ProductAdapter(context,products);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(productAdapter);
    }
}