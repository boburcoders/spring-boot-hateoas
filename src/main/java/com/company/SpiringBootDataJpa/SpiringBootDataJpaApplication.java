package com.company.SpiringBootDataJpa;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


@SpringBootApplication
@EnableAsync
@EnableScheduling
public class SpiringBootDataJpaApplication {

    public static void main(String[] args) {

        /*class MyScheduledTask extends TimerTask {
            private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

            @Override
            public void run() {
                System.out.println("\r" + "Time is: " + dateFormat.format(new Date()));
            }

        }
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyScheduledTask(), 4, 1);*/
        SpringApplication.run(SpiringBootDataJpaApplication.class, args);

    }
}
