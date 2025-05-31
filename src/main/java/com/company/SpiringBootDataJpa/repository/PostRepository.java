package com.company.SpiringBootDataJpa.repository;

import com.company.SpiringBootDataJpa.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}