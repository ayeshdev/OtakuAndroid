// Category.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package com.otaku.otaku.model;

import com.google.gson.annotations.SerializedName;

import java.time.OffsetDateTime;
import java.util.List;

public class Category {

    @SerializedName("id")
        private int id;

    @SerializedName("attributes")
    private CategoryAttributes categoryAttributes;

    public Category(int id, CategoryAttributes categoryAttributes) {
        this.id = id;
        this.categoryAttributes = categoryAttributes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CategoryAttributes getCategoryAttributes() {
        return categoryAttributes;
    }

    public void setCategoryAttributes(CategoryAttributes categoryAttributes) {
        this.categoryAttributes = categoryAttributes;
    }
}
