package com.catalog.service.repository;

import com.catalog.service.model.CategoryAttributes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryAttributesRepository extends JpaRepository<CategoryAttributes, Long> {

    List<CategoryAttributes> findCategoryAttributesByCategoryId(long CategoryId);
}
