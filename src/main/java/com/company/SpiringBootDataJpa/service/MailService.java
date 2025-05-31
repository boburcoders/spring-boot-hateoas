package com.company.SpiringBootDataJpa.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public interface MailService {
    void sendMail(Map<Object, Object> model);

    Boolean getSmtpStatus();

    void smtpOnOff();

    ConcurrentHashMap<Object, Map<Object, Object>> getCache();

    boolean isSMTPActive();
}
