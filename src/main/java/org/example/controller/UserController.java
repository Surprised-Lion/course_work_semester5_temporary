package org.example.controller;


import org.example.dto.LoginDto;
import org.example.dto.UserDto;
import org.example.entities.Author;
import org.example.entities.Poem;
import org.example.entities.User;
import org.example.exceptions.AlreadyExistsException;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        userService.login(loginDto.getUsername(), loginDto.getPassword());
        return ResponseEntity.ok("Welcome back, " + loginDto.getUsername() + "!");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<User> getUserInfo(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getById(userId));
    }

    @PostMapping("/sign_up")
    public ResponseEntity<User> signUp(@RequestBody UserDto userDto, HttpServletResponse response) {
        if (userService.loadUserByUsername(userDto.getUsername()) != null) {
            throw new AlreadyExistsException("User " + userDto.getUsername() + " already exists!");
        }
        User user = userService.addUser(userDto);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/user/{userId}/likedAuthors")
    public ResponseEntity<List<Author>> getLikedAuthors(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getLikedAuthorsByUserId(userId));
    }

    @GetMapping("/user/{userId}/likedPoems")
    public ResponseEntity<List<Poem>> getLikedPoems(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getLikedPoemsByUserId(userId));
    }

    private String getAuthenticationName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
