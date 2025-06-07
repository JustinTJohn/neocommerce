package dev.jtjohn.neocommerce.user.dtos;

import dev.jtjohn.neocommerce.user.models.Role;
import lombok.Data;

import java.util.Collection;

@Data
public class CreateUserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Collection<Role> userRole;
}