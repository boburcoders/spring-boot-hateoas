package com.company.SpiringBootDataJpa.repo;

import com.company.SpiringBootDataJpa.models.Post;
import io.micrometer.common.lang.NonNull;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class CustomPostRepo {
    private final EntityManager em;

    public CustomPostRepo(EntityManager em) {
        this.em = em;
    }

    @Modifying
    public Post save(@NonNull Post post) {
        em.persist(post);
        return post;
    }
}
