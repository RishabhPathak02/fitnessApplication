package com.project.fitness.service;

import com.project.fitness.dto.RegisterRequest;
import com.project.fitness.dto.UserResponse;
import com.project.fitness.model.User;
import com.project.fitness.model.UserRole;
import com.project.fitness.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse register(RegisterRequest registerRequest) {
        User user = User.builder()
                .email(registerRequest.getEmail())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .role(registerRequest.getRole() != null ? registerRequest.getRole() : UserRole.USER)
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();

        User savedUser =  userRepository.save(user);
        return new UserResponse(savedUser);
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}

