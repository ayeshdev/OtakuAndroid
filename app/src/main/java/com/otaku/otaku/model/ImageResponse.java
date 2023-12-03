package com.otaku.otaku.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImageResponse {
    @SerializedName("data")
    private List<Image> data;

    public List<Image> getData() {
        return data;
    }

    public void setData(List<Image> data) {
        this.data = data;
    }
}
