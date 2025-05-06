package com.company.SpiringBootDataJpa.controllers;

import com.company.SpiringBootDataJpa.models.Post;
import com.company.SpiringBootDataJpa.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Integer id) {
        return postService.getPostById(id);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Post>> getAllPost() {
        return postService.getAllPost();
    }

    @PutMapping
    public ResponseEntity<Post> updatePost(@RequestBody Post post) {
        return postService.updatePost(post);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Integer id) {
        return postService.deletePostById(id);
    }
}
