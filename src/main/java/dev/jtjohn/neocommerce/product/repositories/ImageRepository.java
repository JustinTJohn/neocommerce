package dev.jtjohn.neocommerce.product.repositories;

import dev.jtjohn.neocommerce.product.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByProductId(Long id);
}