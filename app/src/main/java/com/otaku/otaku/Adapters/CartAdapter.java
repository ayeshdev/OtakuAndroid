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
import com.otaku.otaku.model.Sizes;
import com.squareup.picasso.Picasso;

import org.checkerframework.checker.units.qual.C;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{
    private static String TAG = CartAdapter.class.getName();

    private static final String base_url = "http://192.168.13.210:1337";

    Context context;
    String id;
    String title;
    String description;
    Integer qty;
    Double price;
    String color;
    List<Cart> cartList;
    List<String> sizeList;
    private String img_url;

    public CartAdapter() {
    }

    public CartAdapter(Context context, List<Cart> cartList) {
        this.context = context;
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.cart_product,parent,false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Cart cart = cartList.get(position);

        Log.i(TAG, "onBindViewHolder: "+cart);

        id = String.valueOf(cart.getId());
        title = cart.getTitle();
        price = cart.getPrice();
        color = cart.getColor();
        sizeList = cart.getSizes();
        qty = cart.getQty();
        img_url = cart.getImg_url();

        holder.title.setText(title);
        holder.price.setText(String.valueOf(price));
        holder.color.setText(color);

        // Convert List<String> to a formatted string before setting it to the TextView
        if (sizeList != null && !sizeList.isEmpty()) {
            StringBuilder sizesText = new StringBuilder();
            for (String size : sizeList) {
                sizesText.append(size).append(", "); // Assuming you want to separate sizes by a comma
            }
            sizesText.deleteCharAt(sizesText.length() - 2); // Remove the last comma and space
            holder.size.setText(sizesText.toString());
        }

        holder.qty.setText(String.valueOf(qty));

        Picasso.get()
                .load(base_url+img_url)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return cartList != null ? cartList.size() : 0;
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {

        TextView id;
        TextView title;
        TextView description;
        TextView qty;
        TextView price;
        TextView size;

        TextView color;
        CardView card;

        ImageView imageView;

        public CartViewHolder(@NonNull View itemView) {
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
