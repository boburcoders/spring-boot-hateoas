package com.company.SpiringBootDataJpa.controller;

import com.company.SpiringBootDataJpa.config.JwtTokenUtil;
import com.company.SpiringBootDataJpa.config.TokenRequest;
import com.company.SpiringBootDataJpa.models.Users;
import com.company.SpiringBootDataJpa.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;


    @PostMapping("/token")
    public String token(@RequestBody TokenRequest tokenRequest) {
        String password = tokenRequest.getPassword();
        String username = tokenRequest.getUsername();
        UsernamePasswordAuthenticationToken authenticate = new UsernamePasswordAuthenticationToken(username, password);
        authenticationManager.authenticate(authenticate);
        return jwtTokenUtil.generateToken(username);
    }

    @PostMapping("/register")
    public Users createUser(@RequestBody Users user) {
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        return userRepo.save(user);
    }
}
