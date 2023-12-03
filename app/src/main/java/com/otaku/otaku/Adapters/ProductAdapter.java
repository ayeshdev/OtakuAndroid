package com.otaku.otaku.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

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

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    private static final String base_url = "http://192.168.1.101:1337";
    Context context;
    ApiService apiService;
    List<Products> productsList;
    String stringTitle;
    String stringDes;
    Double doublePrice;

    ImageResponse image;


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
        stringTitle = product.getAttribute().getTitle();
        stringDes = product.getAttribute().getDescription();
        String imgUrl = product.getAttribute().getImageResponse().getData().get(0).getImageAttributes().getUrl();
        String category = product.getAttribute().getCategoryResponse().getData().getCategoryAttributes().getName();


        Log.i("Category", category);
        Log.i("Product Title", stringTitle);
        Log.i("Product Description", stringDes);
        Log.i("Image URL",imgUrl);
//        Log.i("Category", category);

        holder.title.setText(product.getAttribute().getTitle());
//        holder.description.setText(product.getAttribute().getDescription());
        Picasso.get()
                .load(base_url+imgUrl)
                .resize(400, 400)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return productsList != null ? productsList.size() : 0;
    }

    public static class MyViewHolder extends
            RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        TextView price;
        CardView card;

        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textViewProductName);
//            description = itemView.findViewById(R.id.description);
//            price = itemView.findViewById(R.id.price);
            imageView = itemView.findViewById(R.id.productView); // Initialize the ImageView
//            card = itemView.findViewById(R.id.card);
        }
    }


}
