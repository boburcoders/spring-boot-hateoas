package com.company.SpiringBootDataJpa;

import com.company.SpiringBootDataJpa.models.Comment;
import com.company.SpiringBootDataJpa.models.Post;
import com.company.SpiringBootDataJpa.models.ToDos;
import com.company.SpiringBootDataJpa.repo.CommentRepo;
import com.company.SpiringBootDataJpa.repo.PostRepo;
import com.company.SpiringBootDataJpa.repo.ToDosRepo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.net.URL;
import java.util.List;

// Anatation Based Configuration
/*@OpenAPIDefinition(
        info = @Info(
                title = "Spring Doc Documentation",
                version = "2",
                contact = @Contact(
                        name = "Bobur",
                        email = "qwerty@gmail.com",
                        url = "https://github.com/boburcoders"
                ),
                license = @License(
                        name = "Apache 2",
                        url = "springdoc.org"
                ),
                termsOfService = "http://swagger.io/terms/",
                description = "This document designed for Spring Doc Project"
        ),
        externalDocs = @ExternalDocumentation(
                description = "SpringDoc version 2",
                url = "https://springdoc.org/v/2"
        ),
        servers = {
                @Server(
                        url = "http://localhost:8080",
                        description = "Production Server"
                ),
                @Server(
                        url = "http://localhost:8090",
                        description = "Test Server"
                )
        }
)*/

@SpringBootApplication
public class SpiringBootDataJpaApplication {
    private final CommentRepo commentRepo;
    private final ToDosRepo toDosRepo;

    public SpiringBootDataJpaApplication(CommentRepo commentRepo, ToDosRepo toDosRepo) {
        this.commentRepo = commentRepo;
        this.toDosRepo = toDosRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpiringBootDataJpaApplication.class, args);


    }


    @Bean
    ApplicationRunner runner(PostRepo postRepo, ObjectMapper objectMapper) {
        return args -> {
            URL postUrl = new URL("https://jsonplaceholder.typicode.com/posts");
            List<Post> postList = objectMapper.readValue(postUrl, new TypeReference<>() {
            });
            postList.forEach(post -> post.setId(null));
            postRepo.saveAll(postList);

            URL commentUrl = new URL("https://jsonplaceholder.typicode.com/comments");
            List<Comment> commentList = objectMapper.readValue(commentUrl, new TypeReference<>() {
            });
            commentList.forEach(comment -> comment.setId(null));
            commentRepo.saveAll(commentList);


            URL toDosUrl = new URL("https://jsonplaceholder.typicode.com/todos");
            List<ToDos> toDosList = objectMapper.readValue(toDosUrl, new TypeReference<>() {
            });

            toDosList.forEach(toDos -> toDos.setId(null));
            toDosRepo.saveAll(toDosList);


        };

    }

    // Java Class Based Configuration
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Spring Doc Documentation")
                        .version("2")
                        .description("This document designed for Spring Doc Project")
                        .termsOfService("http://swagger.io/terms/")
                        .contact(new Contact()
                                .name("Bobur")
                                .email("qwerty@gmail.com")
                                .url("https://github.com/boburcoders"))
                        .license(new License()
                                .name("Apache 2")
                                .url("springdoc.org"))
                )
                .externalDocs(new ExternalDocumentation()
                        .description("SpringDoc version 2")
                        .url("https://springdoc.org/v/2"))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Production Server"),
                        new Server().url("http://localhost:8090").description("Test Server")
                )).addSecurityItem(new SecurityRequirement().addList("basicAuth"))
                .components((new Components()
                        .addSecuritySchemes("basicAuth", new SecurityScheme()
                                .name("basicAuth")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("basic"))))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .name("bearerAuth")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }

    @Bean
    public GroupedOpenApi postOpenApi() {
        return GroupedOpenApi.builder()
                .group("posts")
                .pathsToMatch("/api/posts/**")
                .build();
    }

    @Bean
    public GroupedOpenApi toDosOpenApi() {
        return GroupedOpenApi.builder()
                .group("toDos")
                .pathsToMatch("/api/todos/**")
                .build();
    }

    @Bean
    public GroupedOpenApi commentsOpenApi() {
        return GroupedOpenApi.builder()
                .group("comments")
                .pathsToMatch("/api/comments/**")
                .build();
    }
}
