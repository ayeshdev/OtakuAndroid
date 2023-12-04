package com.otaku.otaku.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attributes {
    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("image")
    @Expose
    private ImageResponse imageResponse;

    @SerializedName("price")
    @Expose
    private Double price;

    @SerializedName("category")
    @Expose
    private CategoryResponse categoryResponse;

    public Attributes() {
    }

    public Attributes(String title,String description) {
        this.title = title;
        this.description = description;
    }

    public Attributes(String title, String description, ImageResponse imageResponse, Double price, CategoryResponse categoryResponse) {
        this.title = title;
        this.description = description;
        this.imageResponse = imageResponse;
        this.price = price;
        this.categoryResponse = categoryResponse;
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

    public ImageResponse getImageResponse() {
        return imageResponse;
    }

    public void setImageResponse(ImageResponse imageResponse) {
        this.imageResponse = imageResponse;
    }

    public CategoryResponse getCategoryResponse() {
        return categoryResponse;
    }

    public void setCategoryAttributes(CategoryAttributes categoryAttributes) {
        this.categoryResponse = categoryResponse;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setCategoryResponse(CategoryResponse categoryResponse) {
        this.categoryResponse = categoryResponse;
    }
}
