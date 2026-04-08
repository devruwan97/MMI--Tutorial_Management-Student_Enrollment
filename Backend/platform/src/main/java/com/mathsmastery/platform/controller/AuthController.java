package com.mathsmastery.platform.controller;

import com.mathsmastery.platform.dto.LoginRequest;
import com.mathsmastery.platform.dto.LoginResponse;
import com.mathsmastery.platform.model.User;
import com.mathsmastery.platform.service.AuthService;
import com.mathsmastery.platform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    private AuthService authService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
