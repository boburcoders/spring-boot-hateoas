package com.company.SpiringBootDataJpa.controller;

import com.company.SpiringBootDataJpa.models.Post;
import com.company.SpiringBootDataJpa.repo.CustomPostRepo;
import com.company.SpiringBootDataJpa.repo.PostRepo;
import org.hibernate.engine.spi.ExecutableList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("api/posts")
public class PostController {
    PostRepo postRepo;
    CustomPostRepo customPostRepo;

    public PostController(PostRepo postRepo, CustomPostRepo customPostRepo) {
        this.postRepo = postRepo;
        this.customPostRepo = customPostRepo;
    }

    @PostMapping
    public Post savePost(@RequestBody Post post) {
        return customPostRepo.save(post);
    }

    @GetMapping
    public Page<Post> getAllPosts(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Sort sort = Sort.by(Sort.Order.desc("body"), Sort.Order.asc("userId"));

        Pageable pageable = PageRequest.of(page, size, sort);
        return postRepo.findAll(pageable);
    }

    @GetMapping("/paged")
    public Page<Post> getAllPostsPage(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Sort sort = Sort.by(Sort.Order.asc("id"));

        Pageable pageable = PageRequest.of(page, size, sort);
        return postRepo.findAllPostWithPage(pageable);
    }

    @GetMapping("/byUsers/{userIds}")
    public List<Post> getAllPostByUserIds(@PathVariable Collection<Integer> userIds) {

        return postRepo.findAllByUserIds(userIds);
    }

    @GetMapping("/{userId}")
    public List<Post> getAllPostsByUserId(@PathVariable Integer userId) {
        return postRepo.findAllByUserId(userId);
    }

    @GetMapping("/sortedPosts")
    public List<Post> getAllPostBySort() {
        Sort.Order body = Sort.Order.desc("body");
        Sort.Order id = Sort.Order.desc("id");
        Sort sort = Sort.by(body, id);
        return postRepo.findAll(sort);
    }


}
