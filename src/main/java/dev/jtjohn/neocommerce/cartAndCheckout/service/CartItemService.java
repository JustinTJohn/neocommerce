package dev.jtjohn.neocommerce.cartAndCheckout.service;

import dev.jtjohn.neocommerce.cartAndCheckout.models.Cart;
import dev.jtjohn.neocommerce.cartAndCheckout.models.CartItem;
import dev.jtjohn.neocommerce.cartAndCheckout.repositories.CartItemRepository;
import dev.jtjohn.neocommerce.cartAndCheckout.repositories.CartRepository;
import dev.jtjohn.neocommerce.utils.exceptions.ResourceNotFoundException;
import dev.jtjohn.neocommerce.product.exceptions.ProductNotPresentException;
import dev.jtjohn.neocommerce.product.models.Product;
import dev.jtjohn.neocommerce.product.services.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CartItemService implements ICartItemService {
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final IProductService productService;
    private final ICartService cartService;

    @Override
    public void addCartItem(Long cartId, Long productId, int quantity) throws ProductNotPresentException {
        // Steps to update the cart:
        // 1. Retrieve the user's cart
        // 2. Fetch the product to be added
        // 3. Check if the product is already in the cart
        //    - If yes, update its quantity
        //    - If no, add the product to the cart
        System.out.println("Adding item to cart " + productId + quantity);
        Cart cart = cartService.getCart(cartId);
        Product product = productService.getProductById(productId);
        CartItem cartItem = cart.getCartItems()
                .stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst().orElse(new CartItem());
        if (cartItem.getId() == null) {
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setUnitPrice(product.getPrice());
        }
        else {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }
        cartItem.setTotalPrice();
        cart.addItem(cartItem);

        cartItemRepository.save(cartItem);
        cartRepository.save(cart);
    }

    @Override
    public void removeCartItem(Long cartId, Long productId) throws ProductNotPresentException {
        /*
         * Removes a product from the user's cart:
         * 1. Retrieves the cart and the target product
         * 2. Checks if the product exists in the cart
         *    - If found, removes it
         *    - If not found, throws an exception
         */


        Cart cart = cartService.getCart(cartId);
        Product product = productService.getProductById(productId);
        CartItem cartItem = getCartItem(cartId, productId);
        cart.removeItem(cartItem);
        cartItemRepository.delete(cartItem);
        cartRepository.save(cart);
    }

    @Override
    public void updateItemQuantity(Long cartId, Long productId, int quantity) throws ProductNotPresentException {
        /*
         * Updates the quantity of a product in the user's cart:
         * 1. Retrieves the cart and the specified product
         * 2. Checks if the product exists in the cart
         *    - If found, updates its quantity
         *    - If not found, throws an exception
         */


        Cart cart = cartService.getCart(cartId);
        Product product = productService.getProductById(productId);
        cart.getCartItems()
                .stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .ifPresent(item -> {
                    item.setQuantity(quantity);
                    item.setUnitPrice(item.getProduct().getPrice());
                    item.setTotalPrice();
                });
        BigDecimal totalAmount = cart.getTotalAmount();
        cart.setTotalAmount(totalAmount);
        cartRepository.save(cart);
    }

    @Override
    public CartItem getCartItem(Long cartId, Long productId) {
        Cart cart = cartService.getCart(cartId);
        return cart.getCartItems()
                .stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Item not found"));
    }
}
