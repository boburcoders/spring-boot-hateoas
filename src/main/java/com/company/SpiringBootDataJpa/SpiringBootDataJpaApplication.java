package com.company.SpiringBootDataJpa;


import com.company.SpiringBootDataJpa.service.CacheService;
import com.company.SpiringBootDataJpa.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;


@SpringBootApplication
@EnableAsync
@Slf4j
@RequiredArgsConstructor
@EnableScheduling
public class SpiringBootDataJpaApplication {
    private final CacheService cacheService;
    private final MailService mailService;

    public static void main(String[] args) {
        SpringApplication.run(SpiringBootDataJpaApplication.class, args);

    }

    @Scheduled(initialDelay = 5, fixedDelay = 60, timeUnit = TimeUnit.SECONDS)
    public void sendCachedMails() {
        if (mailService.isSMTPActive()) {
            ConcurrentHashMap<Object, Map<Object, Object>> cache = cacheService.getCache();
            cache.forEach((k, value) -> {
                mailService.sendMail(value);
                cache.remove(k);
            });
        } else {
            log.info("SMTP server off");
        }
    }


}
