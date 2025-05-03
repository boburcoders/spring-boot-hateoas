package com.company.SpiringBootDataJpa.controller;

import com.company.SpiringBootDataJpa.models.Users;
import com.company.SpiringBootDataJpa.repo.UserRepo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
        return "/manager";
    }

    @GetMapping("/user")
    @PreAuthorize("isAuthenticated()")
    public String user() {
        return "/user";
    }


}
