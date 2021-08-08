package com.catalog.service.service.impl;

import com.catalog.service.model.CategoryAttributes;
import com.catalog.service.model.CategoryAttributesResponse;
import com.catalog.service.repository.CategoryAttributesRepository;
import com.catalog.service.repository.CategoryRepository;
import com.catalog.service.service.CategoryAttributesService;
import com.catalog.service.utils.ResourceMissingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryAttributesServiceImpl implements CategoryAttributesService {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    CategoryAttributesRepository categoryAttributesRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public void createCategoryAttributes(List<String> categoryAttributes, Long categoryId) throws Exception {

        if (categoryRepository.findById(categoryId).isPresent()) {
            if (!isDuplicateAttributeName(categoryAttributes, categoryId)) {
                for (String attribute : categoryAttributes) {
                    CategoryAttributes ca = new CategoryAttributes();
                    ca.setAttributeName(attribute);
                    ca.setCategoryId(categoryId);
                    LOG.info("category attribute: " + attribute + " added for the categoryId: " + categoryId);
                    categoryAttributesRepository.save(ca);

                }
            } else {
                LOG.error("category attributes: "+categoryAttributes+" already exists for the categoryId: " + categoryId);
                throw new Exception("category attributes already exists for the categoryId");
            }
        } else {
            LOG.error("categoryId: " + categoryId + " not found");
            throw new Exception("categoryId: " + categoryId + "not found");
        }
    }

    public boolean isDuplicateAttributeName(List<String> categoryAttributes, Long categoryId) {
        List<CategoryAttributes> databaseAttributes = categoryAttributesRepository.findCategoryAttributesByCategoryId(categoryId);
        if (databaseAttributes.isEmpty()) {
            return false;
        }
        List<String> databaseAttributesName = databaseAttributes.stream().map(e -> e.getAttributeName()).collect(Collectors.toList());
        for (String name : categoryAttributes) {
            if (databaseAttributesName.contains(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public CategoryAttributesResponse getCategoryAttributes(long categoryId) {
        List<CategoryAttributes> attributes = categoryAttributesRepository.findCategoryAttributesByCategoryId(categoryId);
        if (attributes.isEmpty()) {
            LOG.warn("No category attributes found for the categoryId: " + categoryId);
            throw new ResourceMissingException("No data found for category attributes.");
        }
        CategoryAttributesResponse categoryAttributesResponse = new CategoryAttributesResponse();
        categoryAttributesResponse.setCategoryId(categoryId);
        categoryAttributesResponse.setAttributes(attributes);
        return categoryAttributesResponse;
    }
}
