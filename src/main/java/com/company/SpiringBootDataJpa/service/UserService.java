package com.company.SpiringBootDataJpa.service;

import com.company.SpiringBootDataJpa.dto.UsersDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UsersDto createUser(UsersDto dto);
}
