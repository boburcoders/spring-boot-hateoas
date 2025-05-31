package com.company.SpiringBootDataJpa.controller;

import com.company.SpiringBootDataJpa.dto.PostCreateDto;
import com.company.SpiringBootDataJpa.dto.PostUpdateDto;
import com.company.SpiringBootDataJpa.entity.Post;
import com.company.SpiringBootDataJpa.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostCreateDto dto) {
        return ResponseEntity.status(201).body(postService.createPost(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(postService.getPost(id));
    }

    @PutMapping
    public ResponseEntity<Post> createPost(@RequestBody PostUpdateDto dto) {
        return ResponseEntity.status(201).body(postService.updatePost(dto));
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Integer id) {
        postService.deletePost(id);
    }


}
