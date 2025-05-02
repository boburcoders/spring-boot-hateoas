package com.company.SpiringBootDataJpa.controller;

import com.company.SpiringBootDataJpa.models.Post;
import com.company.SpiringBootDataJpa.repo.PostRepository;
import com.company.SpiringBootDataJpa.utils.PostModelAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.data.web.PagedResourcesAssemblerArgumentResolver;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;
    private final PostModelAssembler postModelAssembler;
    private final PagedResourcesAssembler<Post> postPagedResourcesAssembler;

    @GetMapping("/{id}")
    public EntityModel<Post> getPost(@PathVariable Integer id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        return postModelAssembler.toModel(post); // using HATEOAS
    }

    @GetMapping("/get-all")
    public CollectionModel<EntityModel<Post>> getAllPosts() {
        List<Post> postList = postRepository.findAll();
        return postModelAssembler.toCollectionModel(postList);
    }

    @GetMapping("/get-all-page")
    public PagedModel<EntityModel<Post>> getAllPostsPaged(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                          @RequestParam(required = false, defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> postList = postRepository.findAll(pageable);
        return postPagedResourcesAssembler.toModel(postList, postModelAssembler);
    }
}
