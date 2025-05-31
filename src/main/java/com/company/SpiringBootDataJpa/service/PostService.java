package com.company.SpiringBootDataJpa.service;

import com.company.SpiringBootDataJpa.dto.PostCreateDto;
import com.company.SpiringBootDataJpa.dto.PostUpdateDto;
import com.company.SpiringBootDataJpa.entity.Post;
import org.springframework.stereotype.Service;

@Service
public interface PostService {
    Post createPost(PostCreateDto dto);

    Post getPost(Integer id);

    void deletePost(Integer id);

    Post updatePost(PostUpdateDto dto);
}
