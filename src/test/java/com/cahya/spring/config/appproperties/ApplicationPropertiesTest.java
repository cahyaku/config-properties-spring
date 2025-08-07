package com.cahya.spring.config.appproperties;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest(classes = ApplicationPropertiesTest.TestApplication.class)
public class ApplicationPropertiesTest {

    @Autowired
    private Environment environment; // auto inject Environment dgn @Autowired

    @Test
    void testApplicationProperties() {
        // semua yang di masukkan harus sesuai dengan yang ada di application.properties
        String applicationName = environment.getProperty("spring.application.name");
        Assertions.assertEquals("spring-config-properties", applicationName);
    }

    @SpringBootApplication
    public static class TestApplication {
        // TIDAK perlu komponen, karena saat kita membuat Spring Boot Application,
        // Spring Boot dia langsung ngambil application.properties
    }
}
