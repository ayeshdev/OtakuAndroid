package com.otaku.otaku.model;

import com.google.gson.annotations.SerializedName;

public class ImageAttributes {
    @SerializedName("url")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
