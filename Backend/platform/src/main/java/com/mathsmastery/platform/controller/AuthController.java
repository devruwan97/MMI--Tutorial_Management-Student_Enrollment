package com.mathsmastery.platform.controller;

import com.mathsmastery.platform.dto.LoginRequest;
import com.mathsmastery.platform.dto.LoginResponse;
import com.mathsmastery.platform.model.User;
import com.mathsmastery.platform.service.AuthService;
import com.mathsmastery.platform.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication API", description = "Login and registration")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @Operation(summary = "Register a new user")
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    @Operation(summary = "Login and get JWT token")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
