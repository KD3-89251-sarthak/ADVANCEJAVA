package com.example.model;

import java.util.HashMap;
import java.util.Map;

public class SessionManager {
    private static Long nextCategoryId = 1L;
    private static Long nextProductId = 1L;
    private Map<Long, Category> categories = new HashMap<>();
    private Map<Long, Product> products = new HashMap<>();

    public void persist(Category category) {
        if (category.getId() == null) {
            category.setId(nextCategoryId++);
        }
        categories.put(category.getId(), category);
        System.out.println("Category persisted: " + category.getName() + " with ID: " + category.getId());
    }

    public void persist(Product product) {
        if (product.getId() == null) {
            product.setId(nextProductId++);
        }
        products.put(product.getId(), product);
        System.out.println("Product persisted: " + product.getName() + " with ID: " + product.getId());
    }

    public Category getCategory(Long id) {
        return categories.get(id);
    }

    public Product getProduct(Long id) {
        return products.get(id);
    }

    public void addProductToCategory(Long categoryId, Product product) {
        Category category = getCategory(categoryId);
        if (category != null) {
            category.addProduct(product);
            persist(product); // Persist the product after associating it
            System.out.println("Product '" + product.getName() + "' added to category '" + category.getName() + "'");
        } else {
            System.out.println("Category with ID " + categoryId + " not found.");
        }
    }

    public void displayAllCategoriesAndProducts() {
        System.out.println("\n--- All Categories and Products ---");
        if (categories.isEmpty()) {
            System.out.println("No categories found.");
        } else {
            for (Category category : categories.values()) {
                System.out.println("Category: " + category.getName() + " (ID: " + category.getId() + ")");
                if (category.getProducts().isEmpty()) {
                    System.out.println("  No products in this category.");
                } else {
                    for (Product product : category.getProducts()) {
                        System.out.println("    Product: " + product.getName() + " (ID: " + product.getId() + ", Price: " + product.getPrice() + ")");
                    }
                }
            }
        }
        System.out.println("-----------------------------------");
    }
}

