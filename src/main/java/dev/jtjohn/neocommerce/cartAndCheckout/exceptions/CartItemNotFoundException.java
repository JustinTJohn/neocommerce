package dev.jtjohn.neocommerce.cartAndCheckout.exceptions;

public class CartItemNotFoundException extends RuntimeException {
    public CartItemNotFoundException(String message) {
        super(message);
    }
}