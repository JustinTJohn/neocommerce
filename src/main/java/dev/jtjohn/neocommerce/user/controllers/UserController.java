package dev.jtjohn.neocommerce.user.controllers;

import dev.jtjohn.neocommerce.utils.dtos.ApiResponse;
import dev.jtjohn.neocommerce.utils.exceptions.ResourceNotFoundException;
import dev.jtjohn.neocommerce.user.dtos.CreateUserRequest;
import dev.jtjohn.neocommerce.user.dtos.ResetPasswordRequestDto;
import dev.jtjohn.neocommerce.user.dtos.UserDto;
import dev.jtjohn.neocommerce.user.dtos.UserUpdateRequest;
import dev.jtjohn.neocommerce.user.exceptions.AlreadyExistException;
import dev.jtjohn.neocommerce.user.models.User;
import dev.jtjohn.neocommerce.user.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api_prefix}/users")
public class UserController {
    private final IUserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/{userId}/user")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable Long userId) {
        try {
            User user = userService.getUserById(userId);
            UserDto userDto = userService.convertUserToDto(user);
            logger.info("User fetched successfully for ID: {}", userId);
            return ResponseEntity.ok(new ApiResponse("User fetched successfully", userDto));
        } catch (ResourceNotFoundException e) {
            logger.error("User not found: {}", e.getMessage());
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createUser(@RequestBody CreateUserRequest request) {
        try {
            User user = userService.createUser(request);
            UserDto userDto = userService.convertUserToDto(user);
            return ResponseEntity.ok(new ApiResponse("User created successfully", userDto));
        } catch (AlreadyExistException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/{userId}/update")
    public ResponseEntity<ApiResponse> updateUser(@RequestBody UserUpdateRequest request, @PathVariable Long userId) {
        try {
            User user = userService.updateUser(request, userId);
            UserDto userDto = userService.convertUserToDto(user);
            return ResponseEntity.ok(new ApiResponse("User updated successfully", userDto));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{userId}/delete")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok(new ApiResponse("User deleted successfully", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequestDto resetPasswordRequestDto) {
        userService.resetPassword(resetPasswordRequestDto);
        return ResponseEntity.ok("Password reset successfully");
    }
}