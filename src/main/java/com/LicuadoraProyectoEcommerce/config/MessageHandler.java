package com.LicuadoraProyectoEcommerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageHandler {
    @Autowired
    private MessageSource messageSource;
    public String message(String message, String param){
        return messageSource.getMessage(message, new Object[]{param}, Locale.ENGLISH);
    }
}