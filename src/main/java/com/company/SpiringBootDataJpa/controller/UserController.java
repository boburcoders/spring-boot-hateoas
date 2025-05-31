package com.company.SpiringBootDataJpa.controller;

import com.company.SpiringBootDataJpa.dto.UsersDto;
import com.company.SpiringBootDataJpa.service.MailService;
import com.company.SpiringBootDataJpa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final MailService mailService;

    @PostMapping
    public ResponseEntity<UsersDto> createUser(@RequestBody UsersDto dto) {
        return ResponseEntity.status(201).body(userService.createUser(dto));
    }

    @GetMapping
    public ResponseEntity<Boolean> isSMTPServerOn() {
        return ResponseEntity.status(200).body(mailService.getSmtpStatus());
    }

    @PostMapping("/smtp/onOff")
    public ResponseEntity<Void> smtpOnOff() {
        mailService.smtpOnOff();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("cache")
    public ResponseEntity<ConcurrentHashMap<Object, Map<Object, Object>>> getCache() {

        return ResponseEntity.status(200).body(mailService.getCache());
    }
}
