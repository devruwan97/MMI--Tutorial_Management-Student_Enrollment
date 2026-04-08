package com.mathsmastery.platform.controller;

import com.mathsmastery.platform.model.User;
import com.mathsmastery.platform.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAllUsers();
    }
}
