package com.company.SpiringBootDataJpa.dto;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.company.SpiringBootDataJpa.entity.Post}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PostCreateDto {
    private String title;
    private String content;
}