package com.company.SpiringBootDataJpa.service.mapper;

import com.company.SpiringBootDataJpa.dto.UsersDto;
import com.company.SpiringBootDataJpa.entity.Users;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    Users toEntity(UsersDto usersDto);

    UsersDto toDto(Users users);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Users partialUpdate(UsersDto usersDto, @MappingTarget Users users);
}