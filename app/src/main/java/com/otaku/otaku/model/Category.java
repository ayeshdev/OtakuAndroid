package com.otaku.otaku.model;

import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("id")
    private int id;

    @SerializedName("attributes")
    private CategoryAttributes categoryAttributes;

    public Category(int id, CategoryAttributes categoryAttributes) {
        this.id = id;
        this.categoryAttributes = categoryAttributes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CategoryAttributes getCategoryAttributes() {
        return categoryAttributes;
    }

    public void setCategoryAttributes(CategoryAttributes categoryAttributes) {
        this.categoryAttributes = categoryAttributes;
    }
}
