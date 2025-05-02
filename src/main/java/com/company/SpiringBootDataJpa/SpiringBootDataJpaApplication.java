package com.company.SpiringBootDataJpa;

import com.company.SpiringBootDataJpa.models.Post;
import com.company.SpiringBootDataJpa.repo.PostRepo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Auditable;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.net.URL;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class SpiringBootDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpiringBootDataJpaApplication.class, args);


    }

    @Bean
    ApplicationRunner runner(PostRepo postRepo, ObjectMapper objectMapper) {
        return args -> {
            URL url = new URL("https://jsonplaceholder.typicode.com/posts");
            List<Post> postList = objectMapper.readValue(url, new TypeReference<>() {
            });
            postList.forEach(post -> post.setId(null));
            postRepo.saveAll(postList);


        };

    }

}
