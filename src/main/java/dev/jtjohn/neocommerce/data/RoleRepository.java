package dev.jtjohn.neocommerce.data;

import dev.jtjohn.neocommerce.user.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String role);
}