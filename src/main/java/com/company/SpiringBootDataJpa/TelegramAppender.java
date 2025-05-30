package com.company.SpiringBootDataJpa;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import com.pengrad.telegrambot.TelegramBot;

import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class TelegramAppender extends AppenderBase<LoggingEvent> {
    @Value("${bot.Token}")
    private String botToken;

    @Value("${bot.chatId}")
    private String chatId;
    private final TelegramBot bot = new TelegramBot(botToken);

    public TelegramAppender() {
        addFilter(new Filter<>() {
            @Override
            public FilterReply decide(LoggingEvent loggingEvent) {
                if (loggingEvent.getLevel().equals(Level.ERROR)) {
                    return FilterReply.ACCEPT;
                }
                return FilterReply.DENY;
            }
        });
    }

    @Override
    protected void append(LoggingEvent loggingEvent) {
        String logMessage = loggingEvent.toString();
        SendMessage message = new SendMessage(chatId, logMessage);
        bot.execute(message);
    }
}
