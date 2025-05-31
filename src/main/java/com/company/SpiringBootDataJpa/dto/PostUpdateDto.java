package com.company.SpiringBootDataJpa.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.company.SpiringBootDataJpa.entity.Post}
 */
@Value
public class PostUpdateDto implements Serializable {
    Integer id;
    String title;
    String content;
}