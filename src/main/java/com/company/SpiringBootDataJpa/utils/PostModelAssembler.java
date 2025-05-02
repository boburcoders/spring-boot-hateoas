package com.company.SpiringBootDataJpa.utils;

import com.company.SpiringBootDataJpa.controller.PostController;
import com.company.SpiringBootDataJpa.models.Post;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PostModelAssembler implements RepresentationModelAssembler<Post, EntityModel<Post>> {
    @Override
    public EntityModel<Post> toModel(Post post) {
        Link postLink = linkTo(methodOn(PostController.class).getAllPosts()).withRel("posts");
        Link link = linkTo(methodOn(PostController.class).getPost(post.getId())).withSelfRel();
        return EntityModel.of(post, link, postLink);
    }

    @Override
    public CollectionModel<EntityModel<Post>> toCollectionModel(Iterable<? extends Post> posts) {
        List<EntityModel<Post>> entities = new ArrayList<>();
        posts.forEach(post -> entities.add(toModel(post)));
        Link postLink = linkTo(methodOn(PostController.class).getAllPosts()).withRel("posts");
        return CollectionModel.of(entities, postLink);
    }
}
