package dev.jtjohn.neocommerce.cartAndCheckout.repositories;

import dev.jtjohn.neocommerce.cartAndCheckout.models.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentDetails,Long> {
    Optional<PaymentDetails> findByOrderId(long orderId);
}