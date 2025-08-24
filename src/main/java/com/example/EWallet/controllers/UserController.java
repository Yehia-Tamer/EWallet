package com.example.EWallet.controllers;

import com.example.EWallet.DTO.UserRegistrationDTO;
import com.example.EWallet.DTO.UserResponseDTO;
import com.example.EWallet.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    public final UserService userService;

    public UserController(UserService userService)
    {
        this.userService=userService;
    }

    @PostMapping("/register")

    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody UserRegistrationDTO userDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(UserService.registerUser(userDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable UUID id) {
        return ResponseEntity.ok(UserService.getUserById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(UserService.getAllUsers());
    }
}
