package com.example.model;

import java.util.HashSet;
import java.util.Set;

public class Category {
    private Long id;
    private String name;
    private Set<Product> products = new HashSet<>();

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    // Helper methods for bidirectional association
    public void addProduct(Product product) {
        this.products.add(product);
        product.setCategory(this);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
        product.setCategory(null);
    }
}

