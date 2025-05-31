package com.company.SpiringBootDataJpa.dto;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.company.SpiringBootDataJpa.entity.Users}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UsersDto{
    String email;
    String username;
    String password;
}