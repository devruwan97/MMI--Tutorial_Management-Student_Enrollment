package com.mathsmastery.platform.controller;

import com.mathsmastery.platform.model.User;
import com.mathsmastery.platform.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @Operation(summary = "Bulk upload users via Excel")
    @PostMapping(value = "/bulk", consumes = "multipart/form-data")
    public List<User> bulkUpload(@RequestParam("file") MultipartFile file) {
        return userService.bulkCreateFromExcel(file);
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Integer userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/{userId}")
    public User updateUser(
            @PathVariable Integer userId,
            @RequestBody User updatedUser
    ) {
        return userService.updateUser(userId, updatedUser);
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return "User deleted successfully";
    }
}