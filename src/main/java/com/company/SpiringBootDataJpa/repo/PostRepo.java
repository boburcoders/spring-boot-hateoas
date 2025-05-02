package com.company.SpiringBootDataJpa.repo;

import com.company.SpiringBootDataJpa.models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@RepositoryRestResource(path = "postlar", collectionResourceRel = "postlar")
public interface PostRepo extends JpaRepository<Post, Integer> {

    // http://localhost:8080/api/postlar/search/findAllByTitleStartingWith?title=sunt
    List<Post> findAllByTitleStartingWith(String title);
}
