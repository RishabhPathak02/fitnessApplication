package com.project.fitness.controller;

import com.project.fitness.dto.LoginRequest;
import com.project.fitness.dto.LoginResponse;
import com.project.fitness.dto.RegisterRequest;
import com.project.fitness.dto.UserResponse;
import com.project.fitness.model.User;
import com.project.fitness.repository.UserRepository;
import com.project.fitness.security.JwtUtils;
import com.project.fitness.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(
            @RequestBody RegisterRequest registerRequest
    ) {
        return ResponseEntity.ok(userService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest loginRequest
    ) {
        Authentication authentication ;

        try {
            User user = userRepository.findByEmail(loginRequest.getEmail());
            if(user == null)return ResponseEntity.status(403).build();

            if(!passwordEncoder.matches(loginRequest.getPassword() , user.getPassword())){
                return ResponseEntity.status(403).build();
            }
            String token = jwtUtils.generateToken(user.getId() , user.getRole().name());
            return ResponseEntity.ok(new LoginResponse(token , new UserResponse(user)));

        } catch (Exception ex) {
            return ResponseEntity.status(401).body(new LoginResponse("Invalid credentials", null));
        }
    }
}


