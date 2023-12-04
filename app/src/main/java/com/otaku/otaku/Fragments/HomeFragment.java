package com.otaku.otaku.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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
import java.util.Objects;

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
        getAll();

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


        // Find the RecyclerView from the inflated layout

        // Check if recyclerView is properly found

            // Proceed with other operations
            getAll();
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    String selectedCategory = "T-Shirt"; // Set the selected category based on tab position or any other identifier

                    // Switch case or if-else to determine the selected category based on tab position
                    if (tab.getPosition() == 0) {
                        selectedCategory = "T-Shirt";
                        Log.d("Selected Category", "T-Shirt");
                    } else if (tab.getPosition() == 1) {
                        selectedCategory = "Jackets";
                        Log.d("Selected Category", "Jackets");
                    } // Add else-if statements for other tabs if needed

                    // Filter products based on the selected category
                    List<Products> filteredProducts = filterProductsByCategory(selectedCategory);

                    // Log the filtered products to check if they match the expected category
                    for (Products product : filteredProducts) {
                        Log.d("Filtered Product", product.getAttribute().getCategoryResponse().getData().getCategoryAttributes().getName());
                    }

                    // Update RecyclerView with filtered products
                    if (!filteredProducts.isEmpty()) {
                        dataView(filteredProducts); // Update RecyclerView with filtered products
                    } else {
                        // Handle case where no products are found for the selected category
                        Log.d("No Products", "No products found for the selected category");
                    }
                }

                private List<Products> filterProductsByCategory(String category) {

                    List<Products> filteredProducts = new ArrayList<>();

                    // Loop through productsList and filter based on category
                    for (Products product : productsList) {
                        System.out.println("working 1");

                        // Assuming Attributes contains the category information
                        String productCategory = product.getAttribute().getCategoryResponse().getData().getCategoryAttributes().getName(); // Replace getCategory() with your actual category getter

                        // Example: If category corresponds to a specific category in your data model
                        if (productCategory != null && productCategory.equals(category)) {

                            System.out.println("working 2");
                            filteredProducts.add(product);
                        }
                    }
                    return filteredProducts;
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
//                dataView(productsList);
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                Toast.makeText(context.getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void dataView(List<Products> products){
        if (recyclerView != null) {
            ProductAdapter productAdapter = new ProductAdapter(context, products);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(productAdapter);
            getAll();
        } else {
            Log.e("HomeFragment", "RecyclerView is null. Check initialization.");
        }
    }
}