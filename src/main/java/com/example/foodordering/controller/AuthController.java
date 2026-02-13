package com.example.foodordering.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.foodordering.dto.*;
import com.example.foodordering.entity.*;
import com.example.foodordering.repository.UserRepository;
import com.example.foodordering.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager; // Important for 500-error fix

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        // Check if user already exists to prevent a 500 DB error
        if(userRepository.findByEmail(request.getEmail()).isPresent()) {
            return "Error: Email is already in use!";
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER); // Default role

        userRepository.save(user);
        return "User Registered Successfully";
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        // 1. Let Spring handle the authentication (Checks email & password)
        // This will throw an exception automatically if credentials are bad (prevents 500s)
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        // 2. If we reach here, authentication was successful
        // We fetch the user from our DB to get their role for the token
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User data error"));

        // 3. Generate the token
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());

        return new AuthResponse(token);
    }
}

