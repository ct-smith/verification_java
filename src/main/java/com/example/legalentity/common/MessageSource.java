package com.example.legalentity.common;

import java.util.Locale;

import org.springframework.lang.Nullable;

public interface MessageSource {

    @Nullable
    String getMessage(String code, @Nullable Object[] args, @Nullable String defaultMessage, Locale locale);

    String getMessage(String code, @Nullable Object[] args, Locale locale);

}
