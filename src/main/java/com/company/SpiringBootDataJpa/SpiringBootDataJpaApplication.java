package com.company.SpiringBootDataJpa;

import com.company.SpiringBootDataJpa.models.Post;
import com.company.SpiringBootDataJpa.repo.PostRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;
import org.springframework.data.web.PagedResourcesAssembler;

import java.net.URL;
import java.util.List;

@SpringBootApplication
public class SpiringBootDataJpaApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpiringBootDataJpaApplication.class, args);


    }


    @Bean
    ApplicationRunner runner(PostRepository postRepo, ObjectMapper objectMapper) {
        return args -> {
            URL url = new URL("https://jsonplaceholder.typicode.com/posts");
            List<Post> postList = objectMapper.readValue(url, new TypeReference<>() {
            });
            postList.forEach(post -> post.setId(null));
            postRepo.saveAll(postList);


        };

    }

    @Bean
    public PagedResourcesAssembler<Post> postPagedResourcesAssembler() {
        return new PagedResourcesAssembler<>(new HateoasPageableHandlerMethodArgumentResolver(), null);
    }


}
