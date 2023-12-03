package com.otaku.otaku.model;

import com.google.gson.annotations.SerializedName;

public class CategoryData {
@SerializedName("data")
    private CategoryAttributes attributes;

    public CategoryAttributes getAttributes() {
        return attributes;
    }

    public void setData(CategoryAttributes attributes) {
        this.attributes = attributes;
    }
}
