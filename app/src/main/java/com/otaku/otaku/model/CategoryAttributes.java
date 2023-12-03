package com.otaku.otaku.model;

import com.google.gson.annotations.SerializedName;

import java.time.OffsetDateTime;

public class CategoryAttributes {

    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
