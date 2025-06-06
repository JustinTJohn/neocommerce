package dev.jtjohn.neocommerce.product.repositories;

import dev.jtjohn.neocommerce.product.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
    boolean existsByName(String name);
}
