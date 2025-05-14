package com.company.SpiringBootDataJpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class ReportService {
    ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void sendReportWithThreadJavaThread() {
        Runnable runnable = () -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception ignored) {
            }
            log.info("Report sending successful");
        };
//        executorService.submit(runnable);
        CompletableFuture.runAsync(runnable);
    }

    @Async
    public void sendReportWithAsync() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception ignored) {
        }
        log.info("Report sending successful");
//        throw new RuntimeException("Runtime error");
    }

    public void sendReportWithoutNewThread() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception ignored) {
        }
        log.info("Report sending successful");
    }
}
