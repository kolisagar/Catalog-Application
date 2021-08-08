package com.catalog.service.service.impl;

import com.catalog.service.model.CategoryAttributes;
import com.catalog.service.model.Product;
import com.catalog.service.repository.CategoryAttributesRepository;
import com.catalog.service.repository.ProductRepository;
import com.catalog.service.service.ProductService;
import com.catalog.service.utils.ResourceMissingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryAttributesRepository categoryAttributesRepository;


    @Override
    public void createProduct(Product product) throws Exception {
        List<CategoryAttributes> databaseAttributes = categoryAttributesRepository.findCategoryAttributesByCategoryId(product.getCategoryId());
        if (!databaseAttributes.isEmpty()) {
            List<Long> objectAttributeIds = product.getProductAttributes().stream().map(e -> e.getAttributeId()).collect(Collectors.toList());
            Collections.sort(objectAttributeIds);
            List<Long> databaseAttributeIds = databaseAttributes.stream().map(e -> e.getAttributeId()).collect(Collectors.toList());
            Collections.sort(databaseAttributeIds);
            if (databaseAttributeIds.equals(objectAttributeIds)) {
                LOG.info("Product : " + product.getProductId() + " is added in database");
                productRepository.save(product);
            } else {
                LOG.error("Provided attributeIds are not valid for productId: " + product.getProductId());
                throw new Exception("Provided attributeIds are not valid for productId: " + product.getProductId());
            }
        } else {
            LOG.error("Provided categoryId is not valid for productId: " + product.getCategoryId());
            throw new Exception("Provided categoryId is not valid for productId: " + product.getCategoryId());
        }
    }

    @Override
    public Product getProduct(String productId) {
        Product product = productRepository.getProductByProductId(productId);
        if (product == null) {
            LOG.warn("No data found for the Product: ", product.getProductId());
            throw new ResourceMissingException("No data found for the Product: " + product.getProductId());
        }
        return product;
    }
}
