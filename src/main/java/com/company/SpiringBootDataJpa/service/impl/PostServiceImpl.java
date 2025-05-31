package com.company.SpiringBootDataJpa.service.impl;

import com.company.SpiringBootDataJpa.dto.PostCreateDto;
import com.company.SpiringBootDataJpa.dto.PostUpdateDto;
import com.company.SpiringBootDataJpa.entity.Post;
import com.company.SpiringBootDataJpa.repository.PostRepository;
import com.company.SpiringBootDataJpa.service.PostService;
import com.company.SpiringBootDataJpa.service.mapper.PostMapper;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final ConcurrentHashMap<Integer, Post> potCache = new ConcurrentHashMap<>();

    @Override
    public Post createPost(PostCreateDto dto) {
        Post entity = postMapper.toEntity(dto);
        return postRepository.save(entity);
    }

    @Override
    @SneakyThrows
    public Post getPost(Integer id) {
        Post cachedPost = potCache.get(id);
        if (cachedPost != null) {
            return cachedPost;
        }
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        TimeUnit.SECONDS.sleep(2);
        potCache.put(id, post);
        return post;
    }

    @Override
    public Post updatePost(PostUpdateDto dto) {
        Post post = getPost(dto.getId());
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        postRepository.save(post);
        return post;
    }

    @Override
    public void deletePost(Integer id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        postRepository.deleteById(id);
        potCache.remove(id);
    }
}
