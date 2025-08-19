package com.workforce.service;

import com.workforce.utils.ServiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Arrays;
import java.util.Locale;



public interface I18nService {

    Logger log = LoggerFactory.getLogger(I18nService.class);

    default String i18n(String code, Object... params) {
        MessageSource messageSource = ServiceUtils.getBean(MessageSource.class);
        Locale locale = LocaleContextHolder.getLocale();

        Object[] translatedParams = Arrays.stream(params)
                .map(param -> {
                    if (param instanceof String str) {
                        try {
                            if (messageSource == null) {
                                return str;
                            }
                            return messageSource.getMessage(str, null, locale);
                        } catch (Exception e) {
                            log.error("Failed to translate parameter '{}': {}", str, e.getMessage());
                            return param;
                        }
                    }
                    return param;
                }).toArray();
        try {
            if (messageSource == null) {
                return code;
            }
            return messageSource.getMessage(code, translatedParams, locale);
        } catch (Exception ex) {
            log.error("Failed to translate code '{}': {}", code, ex.getMessage());
            return code;
        }
    }
}
