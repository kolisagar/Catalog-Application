package com.catalog.service.model;

import java.util.List;

public class CategoryAttributesRequest {

    private long categoryId;
    private List<String> attributes;

    public long getCategoryId() {
        return categoryId;
    }

    public List<String> getAttributes() {
        return attributes;
    }

}
