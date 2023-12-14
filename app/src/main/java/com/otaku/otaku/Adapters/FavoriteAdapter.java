package com.otaku.otaku.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.otaku.otaku.R;
import com.otaku.otaku.entity.Cart;
import com.otaku.otaku.entity.Favorites;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoritesViewHolder> {
    private static String TAG = FavoriteAdapter.class.getName();

    private static final String base_url = "http://192.168.13.210:1337";

    Context context;
    String id;
    String title;
    String description;
    Integer qty;
    Double price;
    String color;
    List<Favorites> favoritesList;
    List<String> sizeList;
    private String img_url;

    public FavoriteAdapter() {
    }

    public FavoriteAdapter(Context context, List<Favorites> favoritesList) {
        this.context = context;
        this.favoritesList = favoritesList;
    }

    @NonNull
    @Override
    public FavoriteAdapter.FavoritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.cart_product,parent,false);
        return new FavoritesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.FavoritesViewHolder holder, int position) {
        Favorites favorites = favoritesList.get(position);

        Log.i(TAG, "onBindViewHolder: "+favorites);

        id = String.valueOf(favorites.getId());
        title = favorites.getTitle();
        price = favorites.getPrice();
        color = favorites.getColor();
        img_url = favorites.getImg_url();

        holder.title.setText(title);
        holder.price.setText(String.valueOf(price));
        holder.color.setText(color);

        Picasso.get()
                .load(base_url+img_url)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return favoritesList != null ? favoritesList.size() : 0;
    }

    public static class FavoritesViewHolder extends RecyclerView.ViewHolder {

        TextView id;
        TextView title;
        TextView description;
        TextView qty;
        TextView price;
        TextView size;

        TextView color;
        CardView card;

        ImageView imageView;

        public FavoritesViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.cart_product_title);
            price = itemView.findViewById(R.id.cart_product_price);
            color = itemView.findViewById(R.id.cart_product_color);
            size = itemView.findViewById(R.id.cart_product_size);
            qty = itemView.findViewById(R.id.cart_product_qty);
            imageView = itemView.findViewById(R.id.cart_product_image);

        }
    }


}
