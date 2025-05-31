package com.company.SpiringBootDataJpa.service.mapper;

import com.company.SpiringBootDataJpa.dto.PostCreateDto;
import com.company.SpiringBootDataJpa.entity.Post;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PostMapper {
    Post toEntity(PostCreateDto postCreateDto);

    PostCreateDto toDto(Post post);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Post partialUpdate(PostCreateDto postCreateDto, @MappingTarget Post post);
}