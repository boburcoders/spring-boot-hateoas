package com.company.SpiringBootDataJpa;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Configuration
public class ScheduledTasks {

    // fixedDelay method ichidagi task bajarib bolgandan keyin ham 2 secund kutadi
//    @Scheduled(fixedDelay = 2, timeUnit = TimeUnit.SECONDS)
    public void fixedDelayTask() {
        System.out.println("(FixedDelayTask) Time is: " + new Date());
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (Exception ignored) {
        }
    }

    // fizedRate method ichidagi task bajarib bolishini kutmaydi
//    @Scheduled(fixedRate = 2, timeUnit = TimeUnit.SECONDS)
    public void fixedRateTask() {
        System.out.println("(FixedRateTask) Time is: " + new Date());
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (Exception ignored) {
        }
    }

//    @Scheduled(initialDelay = 4, fixedRate = 2, timeUnit = TimeUnit.SECONDS)
    public void fixedRateWithInitialTask() {
        System.out.println("(FixedRateTask Initial delay) Time is: " + new Date());
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (Exception ignored) {
        }
    }

    // cron patterns can help to make scheduling
    @Scheduled(cron = "10-40 * * * * *", zone = "Asia/Tashkent")     // har secunda ishga tushadi  ss:mm:hh:dd of month:mm:dd of week(0,7)
    public void cronTask() {
        System.out.println("(Cron ) Time is: " + new Date());
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (Exception ignored) {
        }
    }
}
