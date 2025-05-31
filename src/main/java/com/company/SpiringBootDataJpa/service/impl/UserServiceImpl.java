package com.company.SpiringBootDataJpa.service.impl;

import com.company.SpiringBootDataJpa.dto.UsersDto;
import com.company.SpiringBootDataJpa.entity.Users;
import com.company.SpiringBootDataJpa.events.OtpGenerateEvent;
import com.company.SpiringBootDataJpa.repository.UsersRepository;
import com.company.SpiringBootDataJpa.service.UserService;
import com.company.SpiringBootDataJpa.service.mapper.UsersMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;
    private final ApplicationEventPublisher publisher;

    @Override
    @Transactional
    public UsersDto createUser(UsersDto dto) {
        Users entity = usersMapper.toEntity(dto);
        usersRepository.save(entity);
        publisher.publishEvent(new OtpGenerateEvent(entity));
        return usersMapper.toDto(entity);
    }
}
