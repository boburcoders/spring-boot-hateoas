package com.company.SpiringBootDataJpa.service.impl;

import com.company.SpiringBootDataJpa.dto.PostCreateDto;
import com.company.SpiringBootDataJpa.dto.PostUpdateDto;
import com.company.SpiringBootDataJpa.entity.Post;
import com.company.SpiringBootDataJpa.repository.PostRepository;
import com.company.SpiringBootDataJpa.service.PostService;
import com.company.SpiringBootDataJpa.service.mapper.PostMapper;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Component
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public PostServiceImpl(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    @Override
    public Post createPost(PostCreateDto dto) {
        Post entity = postMapper.toEntity(dto);
        return postRepository.save(entity);
    }

    @Override
    @SneakyThrows
    @Cacheable(value = "posts", key = "#id")
    public Post getPost(Integer id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        TimeUnit.SECONDS.sleep(2);
        return post;
    }

    @Override
    @Cacheable(value = "posts", key = "#root.methodName")  // maxsus xolat bolmasa cache qilmaslik kk
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @Override
    @CachePut(value = "posts", key = "#dto.id")
    public Post updatePost(PostUpdateDto dto) {
        Post post = getPost(dto.getId());
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        postRepository.save(post);
        return post;
    }

    @Override
    @CacheEvict(value = "posts", key = "#id")
    public void deletePost(Integer id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        postRepository.deleteById(id);
    }
}
