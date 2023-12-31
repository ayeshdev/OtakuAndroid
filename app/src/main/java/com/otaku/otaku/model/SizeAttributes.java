package com.otaku.otaku.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SizeAttributes {
    @SerializedName("name")
    @Expose
    private String name;

    public SizeAttributes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
