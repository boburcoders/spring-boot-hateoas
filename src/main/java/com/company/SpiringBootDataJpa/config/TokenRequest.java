package com.company.SpiringBootDataJpa.config;

import lombok.Getter;

@Getter
public class TokenRequest {
    private String username;
    private String password;

    public TokenRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
