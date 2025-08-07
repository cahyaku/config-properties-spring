package com.cahya.spring.config.messagesource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

public class MessageSourceTest {

    private ApplicationContext applicationContext;
    private MessageSource messageSource;

    @BeforeEach
    void setUp() {
        applicationContext = new AnnotationConfigApplicationContext(TestApplication.class);
        messageSource = applicationContext.getBean(MessageSource.class);
    }

    /**
     * Buat 2 unit test untuk menguji MessageSource:
     * 1. Satu untuk bahasa default (Locale.ENGLISH).
     * 2. Pakai bahasa Indonesia -> my_id_ID.properties.
     */
    @Test
    void testDefaultLocale() {
        String message = messageSource.getMessage("hello", new Object[]{"Cahya"}, Locale.ENGLISH);
        Assertions.assertEquals("Hello Cahya", message);
    }

    @Test
    void testIndonesianLocale() {
        String message = messageSource.getMessage("hello", new Object[]{"Cahya"}, new Locale("id", "ID"));
        Assertions.assertEquals("Halo Cahya", message);
    }

    @SpringBootApplication
    public static class TestApplication {
        @Bean
        public MessageSource messageSource() {
            ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

            // Set basename untuk mencari file properties, cukup basename saja
            messageSource.setBasenames("my");
            messageSource.setDefaultEncoding("UTF-8");
            return messageSource;
        }
    }
}
