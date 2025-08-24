package com.example.EWallet.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminOnly() {
        return "Hello Admin!";
    }

    @GetMapping("/user")
    @Secured("ROLE_USER")
    public String userOnly() {
        return "Hello User!";
    }

    @GetMapping("/public")
    public String publicEndpoint() {
        return "Hello World! (no auth required)";
    }
}
