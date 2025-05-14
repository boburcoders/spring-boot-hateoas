package com.company.SpiringBootDataJpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Slf4j
@Configuration
public class GlobalAsyncConfigurer implements AsyncConfigurer {
    /*@Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(60);
        executor.setKeepAliveSeconds(30);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("my-thread-");
        executor.initialize();
        return executor;
    }*/

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (ex, method, params) -> {
            log.error("Error: {}, Params: {}", ex.getMessage(), params);
            ex.printStackTrace();
        };
    }
}
