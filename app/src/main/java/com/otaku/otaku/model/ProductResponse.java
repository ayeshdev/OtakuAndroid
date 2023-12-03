package com.otaku.otaku.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductResponse {
    @SerializedName("data")
    private List<Products> data;

    public List<Products> getData() {
        return data;
    }


    public void setData(List<Products> data) {
        this.data = data;
    }
}

