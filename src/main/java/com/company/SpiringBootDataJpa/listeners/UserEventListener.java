package com.company.SpiringBootDataJpa.listeners;


import com.company.SpiringBootDataJpa.entity.Users;
import com.company.SpiringBootDataJpa.events.OtpGenerateEvent;
import com.company.SpiringBootDataJpa.events.SendMailEvent;
import com.company.SpiringBootDataJpa.repository.UsersRepository;

import com.company.SpiringBootDataJpa.service.MailService;
import com.company.SpiringBootDataJpa.service.OtpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserEventListener {
    private final OtpService otpService;
    private final MailService mailService;

    @EventListener(value = {OtpGenerateEvent.class})
    @Async
    //    @Order(1)
    /*@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, condition = "#event.users.email ne null")
    @Transactional(propagation = Propagation.REQUIRES_NEW)*/
    public CompletableFuture<SendMailEvent> generateOtpEventListener(OtpGenerateEvent event) throws InterruptedException {
        Users user = event.getUsers();
        otpService.generateOtp(user);
        log.info("Generate OTP event: {}", user);
        return CompletableFuture.completedFuture(new SendMailEvent(user.getId(), user.getEmail(), user.getOtp()));
    }

    @EventListener({SendMailEvent.class})
//    @Order(2)
    public void mailSendEventListener(SendMailEvent event) {
        Map<Object, Object> model = Map.of(
                "userId", event.getId(),
                "email", event.getEmail(),
                "otp", event.getOtp()
        );
        mailService.sendMail(model);
    }
}
