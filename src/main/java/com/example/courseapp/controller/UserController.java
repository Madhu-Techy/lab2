package com.example.courseapp.controller;

import com.example.courseapp.dto.UserDto;
import com.example.courseapp.model.User;
import com.example.courseapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.registerUser(userDto, false));
    }

    @PostMapping("/register/instructor")
    public ResponseEntity<User> registerInstructor(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.registerUser(userDto, true));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}