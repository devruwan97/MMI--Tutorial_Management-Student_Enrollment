package com.mathsmastery.platform.controller;

import com.mathsmastery.platform.model.User;
import com.mathsmastery.platform.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User API", description = "Operations related to users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Create a new user")
    @PostMapping
    public User create(@RequestBody User user) {
        return userService.createUser(user);
    }

    @Operation(summary = "Get all users")
    @GetMapping
    public List<User> getAll() {
        return userService.getAllUsers();
    }
}
