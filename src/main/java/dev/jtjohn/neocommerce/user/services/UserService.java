package dev.jtjohn.neocommerce.user.services;

import dev.jtjohn.neocommerce.utils.exceptions.ResourceNotFoundException;
import dev.jtjohn.neocommerce.user.dtos.CreateUserRequest;
import dev.jtjohn.neocommerce.user.dtos.ResetPasswordRequestDto;
import dev.jtjohn.neocommerce.user.dtos.UserDto;
import dev.jtjohn.neocommerce.user.dtos.UserUpdateRequest;
import dev.jtjohn.neocommerce.user.exceptions.AlreadyExistException;
import dev.jtjohn.neocommerce.user.models.User;
import dev.jtjohn.neocommerce.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    final UserRepository userRepository;
    final ModelMapper modelMapper;
    final PasswordEncoder passwordEncoder;

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public User createUser(CreateUserRequest request) {
        return Optional.of(request)
                .filter(user -> !userRepository.existsByEmail(request.getEmail()))
                .map(userRequest -> {
                    User user = new User();
                    user.setEmail(userRequest.getEmail());
                    user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
                    user.setFirstName(userRequest.getFirstName());
                    user.setLastName(userRequest.getLastName());
                    user.setRoles(userRequest.getUserRole());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new AlreadyExistException("Exception: " + request.getEmail() + " already exists"));
    }

    @Override
    public User updateUser(UserUpdateRequest request, Long userId) {
        return userRepository.findById(userId)
                .map(user -> {
                    user.setFirstName(request.getFirstName());
                    user.setLastName(request.getLastName());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public void deleteUser(Long userId) {
        // if user present delete it, else throw exception
        userRepository.findById(userId)
                .ifPresentOrElse(userRepository::delete, () -> {
                    throw new ResourceNotFoundException("User not found");
                });
    }

    @Override
    public UserDto convertUserToDto(User user) {
        Hibernate.initialize(user.getOrders()); // Ensure orders are loaded
        Hibernate.initialize(user.getRoles());  // Ensure roles are loaded
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public User getAuthenticatedUser() {
        Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public void resetPassword(ResetPasswordRequestDto resetPasswordRequestDto) {
        if (!resetPasswordRequestDto.getNewPassword().equals(resetPasswordRequestDto.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        User user = userRepository.findByEmail(resetPasswordRequestDto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        user.setPassword(passwordEncoder.encode(resetPasswordRequestDto.getNewPassword()));
        userRepository.save(user);
    }
}