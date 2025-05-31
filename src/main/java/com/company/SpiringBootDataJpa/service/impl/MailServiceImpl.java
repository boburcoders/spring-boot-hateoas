package com.company.SpiringBootDataJpa.service.impl;

import com.company.SpiringBootDataJpa.service.CacheService;
import com.company.SpiringBootDataJpa.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final CacheService cacheService;
    private boolean on = false;

    @Override
    public void sendMail(Map<Object, Object> model) {
        if (on) {
            log.info("Connecting to SMTP server...");
            log.info("Sending mail {} ...", model);
        } else {
            log.info("Cashing model {} ...", model);
            cacheService.put(model);
        }
    }

    @Override
    public Boolean getSmtpStatus() {
        return this.on;
    }

    @Override
    public void smtpOnOff() {
        this.on = !this.on;
    }

    @Override
    public ConcurrentHashMap<Object, Map<Object, Object>> getCache() {
        return cacheService.getCache();
    }

    @Override
    public boolean isSMTPActive() {
        return this.on;
    }
}
