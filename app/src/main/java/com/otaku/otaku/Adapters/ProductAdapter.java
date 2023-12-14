package com.otaku.otaku.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.otaku.otaku.Fragments.SingleProductFragment;
import com.otaku.otaku.MainActivity;
import com.otaku.otaku.R;
import com.otaku.otaku.api.ApiClient;
import com.otaku.otaku.api.ApiService;
import com.otaku.otaku.model.Category;
import com.otaku.otaku.model.DataRequest;
import com.otaku.otaku.model.ImageResponse;
import com.otaku.otaku.model.Products;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder>{

    private static final String base_url = "http://192.168.13.210:1337";
    Context context;
    ApiService apiService;
    List<Products> productsList;
    String stringId;
    String stringTitle;
    String stringDescription;
    String doublePrice;
    ImageResponse image;
    String img_url;

    public ProductAdapter() {
    }

    public ProductAdapter(Context context, List<Products> productsList) {
        this.productsList = productsList;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.product_item, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Products product = productsList.get(position);

        stringId = String.valueOf(product.getId());
        stringTitle = product.getAttribute().getTitle();
        stringDescription = product.getAttribute().getDescription();
        doublePrice = context.getString(R.string.rs) + String.valueOf(product.getAttribute().getPrice());
        String imgUrl = product.getAttribute().getImageResponse().getData().get(0).getImageAttributes().getUrl();
        String category = product.getAttribute().getCategoryResponse().getData().getCategoryAttributes().getName();

        holder.id.setText(stringId);
        holder.title.setText(stringTitle);
        holder.price.setText(doublePrice);
        holder.img_url.setText(imgUrl);
        holder.description.setText(stringDescription);

        Picasso.get()
                .load(base_url+imgUrl)
                .resize(200, 200)
                .into(holder.imageView);


        //SINGLE PRODUCT VIEW
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("title", String.valueOf(holder.title.getText()));
                bundle.putString("price", String.valueOf(holder.price.getText()));
                bundle.putString("id", String.valueOf(holder.id.getText()));
                bundle.putString("img_url", String.valueOf(holder.img_url.getText()));
                bundle.putString("description", String.valueOf(holder.description.getText()));

                Log.i("Title",String.valueOf(holder.title.getText()));
                Log.i("Price",String.valueOf(holder.price.getText()));
                Log.i("ID", String.valueOf(holder.id.getText()));
                Log.i("IMG_URL", String.valueOf(holder.img_url.getText()));
                Log.i("DESCRIPTION", String.valueOf(holder.description.getText()));

                SingleProductFragment singleProductFragment = new SingleProductFragment();
                singleProductFragment.setArguments(bundle);

                FragmentManager fragmentManager = ((FragmentActivity)context).getSupportFragmentManager();

                FragmentTransaction transaction = fragmentManager.beginTransaction();

                transaction.replace(R.id.fragmentContainer,singleProductFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }

        });

    }

    @Override
    public int getItemCount() {
        return productsList != null ? productsList.size() : 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id;
        TextView title;
        TextView img_url;
        TextView description;
        TextView price;
        CardView card;

        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.pid);
            title = itemView.findViewById(R.id.textViewProductName);
            img_url = itemView.findViewById(R.id.img_url);
            description = itemView.findViewById(R.id.description_text);
            price = itemView.findViewById(R.id.textViewProductPrice);
            imageView = itemView.findViewById(R.id.productView); // Initialize the ImageView
//            card = itemView.findViewById(R.id.card);
        }
    }
}
