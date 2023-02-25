package com.como.hogar.common.util;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageFormatUtil {
    private final MessageSource messageSource;

    public MessageFormatUtil(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String messageKey, Object param) {
        return String.format(messageSource.getMessage(messageKey, null, null,
                LocaleContextHolder.getLocale().stripExtensions()), param);
    }

    public String getMessage(String messageKey, Object param, Object secondParam) {
        return String.format(messageSource.getMessage(messageKey, null, null,
                LocaleContextHolder.getLocale().stripExtensions()), param, secondParam);
    }
}
