package com.catalog.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "CategoryAttributes")
public class CategoryAttributes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "attributeId")
    private long attributeId;

    @Column(name = "attributeName")
    private String attributeName;

    @JsonIgnore
    @Column(name = "categoryId")
    private long categoryId;

    public long getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(long attributeId) {
        this.attributeId = attributeId;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public CategoryAttributes() {
    }
}
