package com.otaku.otaku.model;

import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("id")
    private int id;

    @SerializedName("attributes")
    private ImageAttributes imageAttributes;

    public Image(int id, ImageAttributes imageAttributes) {
        this.id = id;
        this.imageAttributes = imageAttributes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ImageAttributes getImageAttributes() {
        return imageAttributes;
    }

    public void setImageAttributes(ImageAttributes imageAttributes) {
        this.imageAttributes = imageAttributes;
    }
}
