package dev.jtjohn.neocommerce.cartAndCheckout.repositories;

import dev.jtjohn.neocommerce.cartAndCheckout.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    void deleteAllByCartId(Long cartId);
}