package dev.jtjohn.neocommerce.users.services;

import dev.jtjohn.neocommerce.user.dtos.CreateUserRequest;
import dev.jtjohn.neocommerce.user.dtos.ResetPasswordRequestDto;
import dev.jtjohn.neocommerce.user.dtos.UserDto;
import dev.jtjohn.neocommerce.user.dtos.UserUpdateRequest;
import dev.jtjohn.neocommerce.user.models.User;

public interface IUserService {
    User getUserById(Long userId);
    User createUser(CreateUserRequest request);
    User updateUser(UserUpdateRequest request, Long userId);
    void deleteUser(Long userId);

    UserDto convertUserToDto(User user);

    User getAuthenticatedUser();

    void resetPassword(ResetPasswordRequestDto resetPasswordRequestDto);
}