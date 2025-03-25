package com.example.courseapp.service;

import com.example.courseapp.dto.UserDto;
import com.example.courseapp.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;

public interface UserService extends UserDetailsService {  // Extend UserDetailsService
    User registerUser(UserDto userDto, boolean isInstructor);
    User getUserById(String id);
    List<User> getAllUsers();
    User updateUser(String id, UserDto userDto);
    void deleteUser(String id);
    User getCurrentUser();
}