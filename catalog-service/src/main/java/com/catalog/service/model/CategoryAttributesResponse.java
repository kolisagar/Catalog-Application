package com.catalog.service.model;

import java.util.List;

public class CategoryAttributesResponse {

    private long categoryId;
    private List<CategoryAttributes> attributes;

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public List<CategoryAttributes> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<CategoryAttributes> attributes) {
        this.attributes = attributes;
    }
}
