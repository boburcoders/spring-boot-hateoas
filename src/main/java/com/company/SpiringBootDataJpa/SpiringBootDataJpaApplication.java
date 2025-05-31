package com.company.SpiringBootDataJpa;


import com.company.SpiringBootDataJpa.entity.Post;
import com.company.SpiringBootDataJpa.repository.PostRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.net.URL;
import java.util.List;


@SpringBootApplication
@EnableCaching
public class SpiringBootDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpiringBootDataJpaApplication.class, args);

    }

    @Bean
    public ApplicationRunner applicationRunner(ObjectMapper objectMapper, PostRepository repository) {
        return args -> {
            try {
                List<Post> postList = objectMapper.readValue(
                        new URL("https://jsonplaceholder.org/posts"),
                        new TypeReference<List<Post>>() {
                        }
                );
                repository.saveAll(postList);
                System.out.println("Posts loaded successfully!");
            } catch (IOException e) {
                System.err.println("Failed to load posts: " + e.getMessage());
            }
        };
    }

}
