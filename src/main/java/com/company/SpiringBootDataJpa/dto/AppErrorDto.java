package com.company.SpiringBootDataJpa.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AppErrorDto {
    private String errorMessage;
    private int errorCode;
    private String errorPath;
    private LocalDateTime timestamp;

    public AppErrorDto(String errorMessage, int errorCode, String errorPath) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.errorPath = errorPath;
        this.timestamp = LocalDateTime.now();
    }
}
