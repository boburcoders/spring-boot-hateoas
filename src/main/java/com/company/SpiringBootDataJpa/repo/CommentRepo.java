package com.company.SpiringBootDataJpa.repo;

import com.company.SpiringBootDataJpa.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {

}