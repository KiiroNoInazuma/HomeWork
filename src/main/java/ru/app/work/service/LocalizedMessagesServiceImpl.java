package ru.app.work.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.app.work.config.LocalConfig;

@Service
public class LocalizedMessagesServiceImpl implements LocalizedMessagesService{

    private final MessageSource messageSource;

    private final LocalConfig config;

    public LocalizedMessagesServiceImpl(MessageSource messageSource, LocalConfig config) {
        this.messageSource = messageSource;
        this.config = config;
    }

    @Override
    public String getMessage(String code, Object... args) {
        return messageSource.getMessage(code, args, config.getLocale());
    }
}
