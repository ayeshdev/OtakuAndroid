package com.otaku.otaku.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryResponse {
    @SerializedName("data")
    private Category data;

    public Category getData() {
        return data;
    }

    public void setData(Category data) {
        this.data = data;
    }
}
