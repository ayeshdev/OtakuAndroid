package com.otaku.otaku.model;

import com.google.gson.annotations.SerializedName;

public class Sizes {
    @SerializedName("id")
    private final int id;

    @SerializedName("attributes")
    private final Attributes attributes;

    public Sizes(int id, Attributes attributes) {
        this.id = id;
        this.attributes = attributes;
    }

    public int getId() {
        return id;
    }

    public Attributes getAttributes() {
        return attributes;
    }
}
