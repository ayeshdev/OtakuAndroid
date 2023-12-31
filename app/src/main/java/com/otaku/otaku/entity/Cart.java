package com.otaku.otaku.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.otaku.otaku.model.Sizes;

import java.util.List;

@Entity
public class Cart {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "qty")
    public int qty;

    @ColumnInfo(name = "price")
    public Double price;

    @ColumnInfo(name = "color")
    public String color;

    @ColumnInfo(name = "size")
    public List<String> sizes;

    @ColumnInfo(name = "img_url")
    public String img_url;

    public Cart() {
    }

    public Cart(int id, String title, String description, int qty, Double price, String color) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.qty = qty;
        this.price = price;
        this.color = color;
    }

    public Cart(int id, String title, String description, int qty, Double price, String color, List<String> sizes) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.qty = qty;
        this.price = price;
        this.color = color;
        this.sizes = sizes;
    }

    public Cart(int id, String title, String description, int qty, Double price, String color, List<String> sizes, String img_url) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.qty = qty;
        this.price = price;
        this.color = color;
        this.sizes = sizes;
        this.img_url = img_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<String> getSizes() {
        return sizes;
    }

    public void setSizes(List<String> sizes) {
        this.sizes = sizes;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                ", color='" + color + '\'' +
                ", sizes=" + sizes +
                ", img_url='" + img_url + '\'' +
                '}';
    }
}
