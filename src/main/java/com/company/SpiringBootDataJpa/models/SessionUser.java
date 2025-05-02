package com.company.SpiringBootDataJpa.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Random;

@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SessionUser {
    private Integer id;
    private String name;
    private String email;

    Random random = new Random();

    public Integer getId() {
        return random.nextInt(1, 100);
    }
}
