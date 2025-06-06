package dev.jtjohn.neocommerce.cartAndCheckout.service;

import dev.jtjohn.neocommerce.cartAndCheckout.models.Cart;
import dev.jtjohn.neocommerce.user.models.User;

import java.math.BigDecimal;

public interface ICartService {
    Cart getCart(Long cartId);
    void clearCart(Long cartId);
    BigDecimal getTotalPrice(Long cartId);
    Cart initializeNewCart(User user);
    Cart getCartByUserId(Long userId);
}
