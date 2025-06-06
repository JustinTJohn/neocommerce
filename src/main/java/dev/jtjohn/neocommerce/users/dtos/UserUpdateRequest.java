package dev.jtjohn.neocommerce.users.dtos;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private String firstName;
    private String lastName;
}