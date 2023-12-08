package com.otaku.otaku.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SizeResponse {
    @SerializedName("data")
    private List<Sizes> data;

    public List<Sizes> getData() {
        return data;
    }

    public void setData(List<Sizes> data) {
        this.data = data;
    }
}
