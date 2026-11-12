package com.example.maven_project;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    static class Product {
        String name;
        int quantity;
        double price;

        Product(String name, int quantity, double price) {
            this.name = name;
            this.quantity = quantity;
            this.price = price;
        }

        double getTotal() {
            return quantity * price;
        }
    }

    private List<Product> products = new ArrayList<>();

    // Add product
    public void addProduct(String name, int quantity, double price) {

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        products.add(new Product(name, quantity, price));
    }

    // Remove product
    public void removeProduct(String name) {

        boolean removed = products.removeIf(product -> product.name.equals(name));

        if (!removed) {
            throw new IllegalArgumentException("Product not found");
        }
    }

    // Calculate total
    public double calculateTotal() {

        double total = 0;

        for (Product product : products) {
            total += product.getTotal();
        }

        return total;
    }

    // Apply percentage discount
    public double applyDiscount(double percentage) {

        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException(
                    "Discount must be between 0 and 100"
            );
        }

        double total = calculateTotal();
        double discountAmount = total * percentage / 100;

        return total - discountAmount;
    }

    // Apply coupon
    public double applyCoupon(String coupon) {

        double total = calculateTotal();

        if (coupon == null || coupon.isEmpty()) {
            throw new IllegalArgumentException("Invalid coupon");
        }

        if (coupon.equals("SAVE10")) {
            return total * 0.90;
        }

        if (coupon.equals("SAVE20")) {
            return total * 0.80;
        }

        throw new IllegalArgumentException("Invalid coupon");
    }

    // Get product count
    public int getProductCount() {
        return products.size();
    }

    // Main method
    public static void main(String[] args) {

        ShoppingCart cart = new ShoppingCart();

        System.out.println("SHOPPING CART APPLICATION");
        System.out.println("-------------------------");

        // Add products
        cart.addProduct("Laptop", 1, 50000);
        cart.addProduct("Mouse", 2, 500);

        System.out.println("Products added successfully.");
        System.out.println("Number of products: " + cart.getProductCount());

        // Calculate total
        double total = cart.calculateTotal();

        System.out.println("Cart Total: Rs. " + total);

        // Apply discount
        double discountedPrice = cart.applyDiscount(10);

        System.out.println("After 10% Discount: Rs. " + discountedPrice);

        // Apply coupon
        double couponPrice = cart.applyCoupon("SAVE10");

        System.out.println("After SAVE10 Coupon: Rs. " + couponPrice);

        // Remove product
        cart.removeProduct("Mouse");

        System.out.println("Mouse removed successfully.");
        System.out.println("New Cart Total: Rs. " + cart.calculateTotal());
    }
}