package com.practice.rk2.controller;

import com.practice.rk2.dto.UserRequestDto;
import com.practice.rk2.model.User;
import com.practice.rk2.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody UserRequestDto user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserRequestDto user) {
        return userService.verify(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        User user = userService.getUser(username);
        return ResponseEntity.ok(user);
    }

    @GetMapping("{userId}")
    public User getUserByUserId(@PathVariable Long userId) {
        return userService.getUserByUserId(userId);
    }

    @GetMapping("/{username}/stats")
    public ResponseEntity<String> getUserStats(@PathVariable String username) {
        return ResponseEntity.ok(userService.displayStats(username));
    }
}