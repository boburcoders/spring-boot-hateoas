package com.company.SpiringBootDataJpa.service;

import com.company.SpiringBootDataJpa.models.Comment;
import com.company.SpiringBootDataJpa.repo.CommentRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Transactional
@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepo commentRepo;

    public ResponseEntity<Comment> createComment(Comment comment) {
        return new ResponseEntity<>(commentRepo.save(comment), HttpStatus.CREATED);
    }

    public ResponseEntity<Comment> getCommentById(Integer id) {
        return new ResponseEntity<>(commentRepo.findById(id).get(), HttpStatus.OK);
    }

    public ResponseEntity<List<Comment>> getAllComments() {
        return new ResponseEntity<>(commentRepo.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Comment> updateComment(Comment comment) {
        return new ResponseEntity<>(commentRepo.save(comment), HttpStatus.OK);
    }

    public ResponseEntity<String> deleteCommentById(Integer id) {
        commentRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
