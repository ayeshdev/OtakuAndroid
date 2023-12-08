package com.otaku.otaku.model;

import com.google.gson.annotations.SerializedName;

public class SizeRequest {
    @SerializedName("data")
    SizeAttributes attributes;

    public SizeRequest(String name) {
        this.attributes = new SizeAttributes(name);
    }
}