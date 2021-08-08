package com.catalog.service.endpoints;

import com.catalog.service.model.Category;
import com.catalog.service.model.CategoryAttributesRequest;
import com.catalog.service.model.CategoryAttributesResponse;
import com.catalog.service.model.Product;
import com.catalog.service.service.CategoryAttributesService;
import com.catalog.service.service.CategoryService;
import com.catalog.service.service.ProductService;
import com.catalog.service.utils.CatalogServiceBadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/catalog-service")
public class CatalogEndpoints {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final String CONTENT_TYPE = "Content-Type";

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryAttributesService categoryAttributesService;

    @Autowired
    ProductService productService;

    @PostMapping(value = "/category", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> storeCategory(@RequestBody Category category) {
        LOG.debug("executing storeCategory()");
        if (category == null || category.getCategoryName() == null || category.getCategoryName().isEmpty()) {
            LOG.warn("Invalid request provided while storing the Category");
            throw new CatalogServiceBadRequestException("Invalid request provided while storing the Category");
        }
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .header(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).body(categoryService.createCategory(category));
        } catch (Exception ex) {
            LOG.error("Error occurred while saving the Category");
            throw new RuntimeException("Error occurred while saving the Category");
        }
    }


    @PostMapping(value = "/categoryAttributes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> storeCategoryAttributes(@RequestBody CategoryAttributesRequest request) {
        LOG.debug("executing storeCategoryAttributes()");
        if (request == null || request.getAttributes().isEmpty() || Objects.isNull(request.getCategoryId())) {
            LOG.warn("Invalid request provided while storing the Category Attributes");
            throw new CatalogServiceBadRequestException("Invalid request provided while storing the Category Attributes");
        }
        try {
            LOG.info("Storing category attributes for the categoryId: " + request.getCategoryId());
            categoryAttributesService.createCategoryAttributes(request.getAttributes(), request.getCategoryId());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .header(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).body("Category attributes saved successfully");
        } catch (Exception ex) {
            LOG.error("Error occurred while saving the Category Attributes");
            throw new RuntimeException("Error occurred while saving the Category Attributes");
        }
    }

    @GetMapping(value = "/categoryAttributes/{categoryId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryAttributesResponse> getCategoryAttributes(@PathVariable("categoryId") Long categoryId) {
        return ResponseEntity.ok(categoryAttributesService.getCategoryAttributes(categoryId));
    }

    @PostMapping(value = "/product", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> storeProduct(@RequestBody List<Product> request) {

        if (request == null || request.isEmpty()) {
            LOG.warn("Invalid request provided while storing the Product");
            throw new CatalogServiceBadRequestException("Invalid request provided while storing the Product");
        }
        List<String> productIds = new ArrayList<>();
        for (Product product : request) {
            try {
                productService.createProduct(product);
                productIds.add(product.getProductId());
            } catch (Exception ex) {
                LOG.error("Error occurred while saving the Product", ex);
                continue;
            }
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .header(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).body("Product saved successfully for the productIds:" + productIds);
    }

    @GetMapping(value = "/product/{productId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProduct(@PathVariable("productId") String productId) {
        return ResponseEntity.ok(productService.getProduct(productId));
    }

}
