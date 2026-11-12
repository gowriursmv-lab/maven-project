package com.example.maven_project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartTest {

    @Test
    void testAddProduct() {

        ShoppingCart cart = new ShoppingCart();

        cart.addProduct("Laptop", 2, 50000);

        assertEquals(1, cart.getProductCount());
    }

    @Test
    void testRemoveProduct() {

        ShoppingCart cart = new ShoppingCart();

        cart.addProduct("Laptop", 1, 50000);
        cart.addProduct("Mouse", 2, 500);

        cart.removeProduct("Mouse");

        assertEquals(1, cart.getProductCount());
    }

    @Test
    void testTotalCalculation() {

        ShoppingCart cart = new ShoppingCart();

        cart.addProduct("Laptop", 1, 50000);
        cart.addProduct("Mouse", 2, 500);

        assertEquals(51000, cart.calculateTotal(), 0.01);
    }

    @Test
    void testDiscountCalculation() {

        ShoppingCart cart = new ShoppingCart();

        cart.addProduct("Laptop", 1, 50000);

        double finalPrice = cart.applyDiscount(10);

        // INTENTIONALLY WRONG EXPECTED VALUE
        assertEquals(40000, finalPrice, 0.01);
    }

    @Test
    void testCouponCalculation() {

        ShoppingCart cart = new ShoppingCart();

        cart.addProduct("Laptop", 1, 50000);

        double finalPrice = cart.applyCoupon("SAVE10");

        assertEquals(45000, finalPrice, 0.01);
    }

    @Test
    void testInvalidQuantity() {

        ShoppingCart cart = new ShoppingCart();

        assertThrows(IllegalArgumentException.class, () -> {
            cart.addProduct("Laptop", 0, 50000);
        });
    }

    @Test
    void testInvalidPrice() {

        ShoppingCart cart = new ShoppingCart();

        assertThrows(IllegalArgumentException.class, () -> {
            cart.addProduct("Laptop", 1, -500);
        });
    }

    @Test
    void testInvalidDiscount() {

        ShoppingCart cart = new ShoppingCart();

        cart.addProduct("Laptop", 1, 50000);

        assertThrows(IllegalArgumentException.class, () -> {
            cart.applyDiscount(120);
        });
    }

    @Test
    void testInvalidCoupon() {

        ShoppingCart cart = new ShoppingCart();

        cart.addProduct("Laptop", 1, 50000);

        assertThrows(IllegalArgumentException.class, () -> {
            cart.applyCoupon("INVALID");
        });
    }
}