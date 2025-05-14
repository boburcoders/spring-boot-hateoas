package com.company.SpiringBootDataJpa;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


@SpringBootApplication
@EnableAsync
public class SpiringBootDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpiringBootDataJpaApplication.class, args);

    }

    /*@Bean
//    @Profile("dev")
    public TaskExecutor taskExecutorDev() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setKeepAliveSeconds(10);
        executor.setQueueCapacity(30);
        executor.setThreadNamePrefix("dev-");
        executor.initialize();
        return executor;
    }

    @Bean
//    @Profile("test")
    public TaskExecutor taskExecutorTest() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(5);
        executor.setKeepAliveSeconds(10);
        executor.setQueueCapacity(20);
        executor.setThreadNamePrefix("test-");
        executor.initialize();
        return executor;
    }

    @Bean
//    @Profile("prod")
    public TaskExecutor taskExecutorProd() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(50);
        executor.setMaxPoolSize(100);
        executor.setKeepAliveSeconds(50);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("prod-");
        executor.initialize();
        return executor;
    }*/

}
