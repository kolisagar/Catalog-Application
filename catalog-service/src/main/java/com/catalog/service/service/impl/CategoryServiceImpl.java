package com.catalog.service.service.impl;

import com.catalog.service.model.Category;
import com.catalog.service.repository.CategoryRepository;
import com.catalog.service.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {
        LOG.info("Category: " + category.getCategoryName() + "is added in database.");
        Category cat = categoryRepository.findByCategoryName(category.getCategoryName());
        if (cat != null) {
            LOG.warn("Category with name: " + category.getCategoryName() + " already exists");
            return cat;
        }
        return categoryRepository.save(category);
    }
}
