package com.company.SpiringBootDataJpa.service;

import com.company.SpiringBootDataJpa.models.Post;
import com.company.SpiringBootDataJpa.repo.PostRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final PostRepo postRepo;

    public ResponseEntity<Post> createPost(Post post) {
        Post save = postRepo.save(post);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    public ResponseEntity<Post> getPostById(Integer id) {
        Optional<Post> byId = postRepo.findById(id);
        return byId.map(post -> new ResponseEntity<>(post, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<List<Post>> getAllPost() {
        List<Post> all = postRepo.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    public ResponseEntity<Post> updatePost(Post post) {
        Post saved = postRepo.save(post);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }

    public ResponseEntity<String> deletePostById(Integer id) {
        postRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
