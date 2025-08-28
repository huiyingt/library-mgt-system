package com.example.librarymgt.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.librarymgt.dto.LoginRequestDTO;
import com.example.librarymgt.model.User;
import com.example.librarymgt.services.UserServices;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private UserServices userServices;

    // Login → return JWT token
    @PostMapping("/login")
    public Map<String, String> login(@Valid @RequestBody LoginRequestDTO request) {
        String token = userServices.loginUser(request.getUsername(), request.getPassword());
        Optional<User> user = userServices.getUserByUsername(request.getUsername());
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("username", user.get().getUsername());
        response.put("role", user.get().getRole().getRoleType().name());
        return response;
    }

    // Logout → just respond (JWT is stateless)
    @PostMapping("/logout")
    public String logout(@RequestParam String username) {
        return userServices.logoutUser(username);
    }
}