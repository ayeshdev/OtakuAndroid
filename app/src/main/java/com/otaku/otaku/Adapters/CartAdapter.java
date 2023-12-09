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

import org.checkerframework.checker.units.qual.C;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{
    private static String TAG = CartAdapter.class.getName();

    Context context;
    String id;
    String title;
    String description;
    Integer qty;
    Double price;
    String color;
    List<Cart> cartList;

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


        holder.title.setText(title);
        holder.price.setText(String.valueOf(price));
    }

    @Override
    public int getItemCount() {
        return cartList != null ? cartList.size() : 0;
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {

        TextView id;
        TextView title;
        TextView img_url;
        TextView description;
        TextView price;
        CardView card;

        ImageView imageView;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.cart_product_title);
            price = itemView.findViewById(R.id.cart_product_price);
        }
    }



}
