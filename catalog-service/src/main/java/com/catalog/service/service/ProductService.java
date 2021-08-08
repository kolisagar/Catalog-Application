package com.catalog.service.service;

import com.catalog.service.model.Product;

public interface ProductService {
    void createProduct(Product product) throws Exception;
    Product getProduct(String productId);
}
