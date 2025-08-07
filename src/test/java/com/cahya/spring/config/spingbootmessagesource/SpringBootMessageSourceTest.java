package com.cahya.spring.config.spingbootmessagesource;

import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;

import java.util.Locale;

@SpringBootTest(classes = SpringBootMessageSourceTest.TestApplication.class)
public class SpringBootMessageSourceTest {
    /**
     * Langkah-2
     * Buat unit testnya.
     */
    @Autowired
    private TestApplication.SampleSource sampleSource;


    @Test
    void testHelloEko() {
        Assertions.assertEquals("Hello Cahya", sampleSource.helloCahya(Locale.ENGLISH));
        Assertions.assertEquals("Halo Cahya", sampleSource.helloCahya(new Locale("id", "ID")));
    }

    /**
     * Langkah-1
     * Buat test applicationnya
     */
    @SpringBootApplication
    public static class TestApplication {

        @Component
        public static class SampleSource implements MessageSourceAware {
            @Setter
            private MessageSource messageSource;

            public String helloCahya(Locale locale) {
                return messageSource.getMessage("hello", new Object[]{"Cahya"}, locale);
            }
        }
    }
}
