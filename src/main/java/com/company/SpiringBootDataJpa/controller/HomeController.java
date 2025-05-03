package com.company.SpiringBootDataJpa.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@EnableMethodSecurity
public class HomeController {

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public String home() {
        return "Hello World";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin() {
        return "/admin";
    }

    @GetMapping("/manager")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public String manager() {
        return "/admin";
    }

    @GetMapping("/user")
    @PreAuthorize("isAuthenticated()")
    public String user() {
        return "/user";
    }
}
