
package com.example.model;

public class Main {
    public static void main(String[] args) {
        SessionManager session = new SessionManager();

        // 1. Add Category
        System.out.println("\n--- Adding Categories ---");
        Category electronics = new Category("Electronics");
        session.persist(electronics);

        Category books = new Category("Books");
        session.persist(books);

        // 2. Add Product to Category
        System.out.println("\n--- Adding Products to Categories ---");
        Product laptop = new Product("Laptop", 1200.00);
        session.addProductToCategory(electronics.getId(), laptop);

        Product smartphone = new Product("Smartphone", 800.00);
        session.addProductToCategory(electronics.getId(), smartphone);

        Product javaBook = new Product("Java Programming", 45.50);
        session.addProductToCategory(books.getId(), javaBook);

        // Display all categories and products
        session.displayAllCategoriesAndProducts();

        // Demonstrate removing a product (optional, for testing helper method)
        System.out.println("\n--- Demonstrating Product Removal ---");
        if (electronics.getProducts().contains(laptop)) {
            electronics.removeProduct(laptop);
            System.out.println("Removed Laptop from Electronics category.");
        }
        session.displayAllCategoriesAndProducts();
    }
}

