package dev.jtjohn.neocommerce.cartAndCheckout.service;

import dev.jtjohn.neocommerce.cartAndCheckout.models.CartItem;
import dev.jtjohn.neocommerce.product.exceptions.ProductNotPresentException;

public interface ICartItemService {
    void addCartItem(Long cartId, Long productId, int quantity) throws ProductNotPresentException;
    void removeCartItem(Long cartId, Long productId) throws ProductNotPresentException;
    void updateItemQuantity(Long cartId, Long productId, int quantity) throws ProductNotPresentException;

    CartItem getCartItem(Long cartId, Long productId);
}