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

    @SerializedName("category")
    @Expose
    private CategoryData categoryData;

    public Attributes() {
    }

    public Attributes(String title,String description) {
        this.title = title;
        this.description = description;
    }

    public Attributes(String title, String description, ImageResponse imageResponse, CategoryData categoryData) {
        this.title = title;
        this.description = description;
        this.imageResponse = imageResponse;
        this.categoryData = categoryData;
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

    public CategoryData getCategoryData() {
        return categoryData;
    }

    public void setCategoryData(CategoryData categoryData) {
        this.categoryData = categoryData;
    }
}
