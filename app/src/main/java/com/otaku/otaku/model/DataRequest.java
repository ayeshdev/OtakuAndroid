package com.otaku.otaku.model;
import com.google.gson.annotations.SerializedName;

public class DataRequest {
    @SerializedName("data")
    Attributes attributes;
    public DataRequest(String title, String description, ImageResponse image, Double price, CategoryResponse category) {
        this.attributes = new Attributes(title,description,image,price,category);
    }
}