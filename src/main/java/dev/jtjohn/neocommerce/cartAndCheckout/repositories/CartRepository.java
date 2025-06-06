package dev.jtjohn.neocommerce.cartAndCheckout.repositories;

import dev.jtjohn.neocommerce.cartAndCheckout.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByUserId(Long userId);
}