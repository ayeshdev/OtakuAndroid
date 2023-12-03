package com.otaku.otaku.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataResponse {

    @SerializedName("data")
    @Expose
    private List<Products> data = null;

    public List<Products> getData() {
        return data;
    }
}
