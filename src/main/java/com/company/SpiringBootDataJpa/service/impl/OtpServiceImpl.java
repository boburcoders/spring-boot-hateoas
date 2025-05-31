package com.company.SpiringBootDataJpa.service.impl;

import com.company.SpiringBootDataJpa.entity.Users;
import com.company.SpiringBootDataJpa.repository.UsersRepository;
import com.company.SpiringBootDataJpa.service.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {
    private final UsersRepository usersRepository;

    @Override
    public void generateOtp(Users user) {
        user.setOtp(UUID.randomUUID().toString());
        usersRepository.save(user);
    }
}
