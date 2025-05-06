package com.company.SpiringBootDataJpa.repo;

import com.company.SpiringBootDataJpa.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Integer> {
}
