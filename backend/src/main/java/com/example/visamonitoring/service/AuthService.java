package com.example.visamonitoring.service;

import com.example.visamonitoring.entity.User;
import com.example.visamonitoring.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthService(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    public String authenticate(String username, String password) {
        boolean valid = userService.validateCredentials(username, password);
        if (!valid) {
            throw new RuntimeException("Invalid credentials");
        }

        User user = userService.getUserByUsername(username);
        return jwtUtil.generateToken(user.getUsername());
    }

    public boolean validateToken(String token) {
        return jwtUtil.validateToken(token);
    }

    public String getUsernameFromToken(String token) {
        return jwtUtil.getUsernameFromToken(token);
    }
}
