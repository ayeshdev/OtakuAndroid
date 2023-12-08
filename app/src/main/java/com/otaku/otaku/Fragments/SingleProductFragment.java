package com.otaku.otaku.Fragments;

import android.graphics.drawable.Drawable;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.otaku.otaku.MainActivity;
import com.otaku.otaku.R;
import com.otaku.otaku.api.ApiClient;
import com.otaku.otaku.api.ApiService;
import com.otaku.otaku.dao.CartDao;
import com.otaku.otaku.entity.Cart;
import com.otaku.otaku.model.DataResponse;
import com.otaku.otaku.model.SizeResponse;
import com.otaku.otaku.util.AppDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleProductFragment extends Fragment {

    private static final String TAG = SingleProductFragment.class.getName();
    private static final String base_url = "http://192.168.1.101:1337";

    private String product_title;
    private String product_img_url;
    private String product_price;
    private String product_description;

    private int count = 0;


    ImageView imageView;

    TextView productTitleView;
    TextView productPriceView;
    TextView productDescriptionView;
    TextView countTextView;
    public SingleProductFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            product_title = getArguments().getString("title");
            assert product_title != null;
            Log.i("SPV - TITLE", product_title);
            String id = getArguments().getString("id");
            assert id != null;
            Log.i("SPV - ID",id);
            product_img_url = getArguments().getString("img_url");
            assert product_img_url != null;
            Log.i("SPV - IMG_URL", product_img_url);
            product_price = getArguments().getString("price");
            assert product_price != null;
            Log.i("SPV - PRICE", product_price);
            product_description = getArguments().getString("description");
            assert product_description != null;
            Log.i("SPV - DESCRIPTION", product_description);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_single_product, container, false);
        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageView = view.findViewById(R.id.product_image);
        productTitleView = view.findViewById(R.id.product_title);
        productPriceView = view.findViewById(R.id.product_price);
        productDescriptionView = view.findViewById(R.id.product_description_text);

        Picasso.get()
                .load(base_url+product_img_url)
                .resize(1000, 1000)
                .into(imageView);

        productTitleView.setText(product_title);
        productPriceView.setText(product_price);
        productDescriptionView.setText(product_description);


        Button sizeSButton = view.findViewById(R.id.sizeSButton);
        Button sizeMButton = view.findViewById(R.id.sizeMButton);
        Button sizeLButton = view.findViewById(R.id.sizeLButton);
        Button sizeXLButton = view.findViewById(R.id.sizeXLButton);
        Button sizeXXLButton = view.findViewById(R.id.sizeXXLButton);
        Button sizeXXXLButton = view.findViewById(R.id.sizeXXXLButton);

        ArrayList<String> sizeList = new ArrayList<>();
        sizeSButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sizeSButton.setSelected(!sizeSButton.isSelected());

                if (sizeSButton.isSelected()){
                    Log.i("SPV", "SMALL BUTTON SELECTED");
                    sizeList.add("S");
                }else{
                    Log.i("SPV", "SMALL BUTTON NOT SELECTED");
                    sizeList.remove("S");
                }
                handleSizeSelection("S");
            }
            private void handleSizeSelection(String size) {
                Toast.makeText(getContext(), "Selected size: " + size, Toast.LENGTH_SHORT).show();
            }
        });

        sizeMButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sizeMButton.setSelected(!sizeMButton.isSelected());
                if (sizeMButton.isSelected()){
                    Log.i("SPV", "MEDIUM BUTTON SELECTED");
                    sizeList.add("M");
                }else{
                    Log.i("SPV", "MEDIUM BUTTON NOT SELECTED");
                    sizeList.remove("M");
                }
                handleSizeSelection("M");
            }
            private void handleSizeSelection(String size) {
                Toast.makeText(getContext(), "Selected size: " + size, Toast.LENGTH_SHORT).show();
            }
        });

        sizeLButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sizeLButton.setSelected(!sizeLButton.isSelected());
                if (sizeLButton.isSelected()){
                    Log.i("SPV", "LARGE BUTTON SELECTED");
                    sizeList.add("L");
                }else{
                    Log.i("SPV", "LARGE BUTTON NOT SELECTED");
                    sizeList.remove("L");
                }
                handleSizeSelection("L");
            }
            private void handleSizeSelection(String size) {
                Toast.makeText(getContext(), "Selected size: " + size, Toast.LENGTH_SHORT).show();
            }
        });

        sizeXLButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sizeXLButton.setSelected(!sizeXLButton.isSelected());
                if (sizeXLButton.isSelected()){
                    Log.i("SPV", "XL BUTTON SELECTED");
                    sizeList.add("XL");
                }else{
                    Log.i("SPV", "XL BUTTON NOT SELECTED");
                    sizeList.remove("XL");
                }
                handleSizeSelection("XL");
            }
            private void handleSizeSelection(String size) {
                Toast.makeText(getContext(), "Selected size: " + size, Toast.LENGTH_SHORT).show();
            }
        });

        sizeXXLButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sizeXXLButton.setSelected(!sizeXXLButton.isSelected());
                if (sizeXXLButton.isSelected()){
                    Log.i("SPV", "XXL BUTTON SELECTED");
                    sizeList.add("XXL");
                }else{
                    Log.i("SPV", "XXL BUTTON NOT SELECTED");
                    sizeList.remove("XXL");
                }
                handleSizeSelection("XXL");
            }
            private void handleSizeSelection(String size) {
                Toast.makeText(getContext(), "Selected size: " + size, Toast.LENGTH_SHORT).show();
            }
        });
        sizeXXXLButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sizeXXXLButton.setSelected(!sizeXXXLButton.isSelected());
                if (sizeXXXLButton.isSelected()){
                    Log.i("SPV", "XXXL BUTTON SELECTED");
                    sizeList.add("XXXL");
                }else{
                    Log.i("SPV", "XXXL BUTTON NOT SELECTED");
                    sizeList.remove("XXXL");
                }
                handleSizeSelection("XXXL");
            }
            private void handleSizeSelection(String size) {
                Toast.makeText(getContext(), "Selected size: " + size, Toast.LENGTH_SHORT).show();
            }
        });


        countTextView = view.findViewById(R.id.countTextView);
        Button decreaseButton = view.findViewById(R.id.decreaseButton);
        Button increaseButton = view.findViewById(R.id.increaseButton);

        decreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decreaseCount();
            }
        });

        increaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseCount();
            }
        });



        view.findViewById(R.id.btn_add_to_cart).setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Log.i("SPV","ADD TO CART BUTTON WORKS");


                RadioGroup radioGroup = view.findViewById(R.id.color_group);
                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                String selectedText;

                Log.i(TAG, "onClick: "+sizeList);
                Log.i(TAG, "onClick: "+count);
                Log.i(TAG, "onClick: COLOR " + selectedRadioButtonId);


                if (selectedRadioButtonId != -1){
                    RadioButton selectedRadioButton = view.findViewById(selectedRadioButtonId);
                    selectedText = selectedRadioButton.getText().toString();
                    Log.i(TAG, "onClick: " + selectedText);
                }else {
                    Log.i(TAG, "onClick: SELECT COLOR");
                }

                Bundle bundle = new Bundle();
                bundle.putString("title", product_title);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        AppDatabase db = Room.databaseBuilder(requireContext().getApplicationContext(),
                                AppDatabase.class, "otaku").build();

                        CartDao cartDao = db.cartDao();

                        Cart cart = new Cart();
                        cart.setTitle(product_title);
                        cart.setDescription(product_description);


                        String clean_product_price = product_price.replaceAll("[^\\d.]", "");
                        cart.setPrice(Double.valueOf(clean_product_price));


                        cartDao.insert(cart);

                        List<Cart> all = cartDao.getAll();
                        all.forEach(c->{
                            Log.i(TAG, "run: "+c.toString());
                        });

                    }
                }).start();

            }
        });




    }


    public void increaseCount() {
        count++;
        updateCount();
    }

    public void decreaseCount() {
        if (count > 0) {
            count--;
            updateCount();
        }
    }

    private void updateCount() {
        countTextView.setText(String.valueOf(count));
    }


}