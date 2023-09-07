package com.tokentest.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/secure")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String secureEndpoint() {
        return "This is a secure endpoint accessible only to users with ROLE_ADMIN.";
    }
}
