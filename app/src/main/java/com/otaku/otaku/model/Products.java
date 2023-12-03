package com.otaku.otaku.model;

import com.google.gson.annotations.SerializedName;

public class Products {
    @SerializedName("id")
    private final int id;

    @SerializedName("attributes")
    private final Attributes attributes;

    public Products(int id, Attributes attributes) {
        this.id = id;
        this.attributes = attributes;
    }
    public int getId() {
        return id;
    }
    public Attributes getAttribute() {
        return attributes;
    }

}