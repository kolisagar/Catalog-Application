package com.catalog.service.service;

import com.catalog.service.model.CategoryAttributesResponse;

import java.util.List;

public interface CategoryAttributesService {
    void createCategoryAttributes(List<String> categoryAttributes, Long categoryId) throws Exception;
    CategoryAttributesResponse getCategoryAttributes(long categoryId);
}
