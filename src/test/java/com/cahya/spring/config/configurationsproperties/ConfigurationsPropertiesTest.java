package com.cahya.spring.config.configurationsproperties;

import com.cahya.spring.config.properties.ApplicationProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ConfigurationsPropertiesTest.TestApplication.class)
public class ConfigurationsPropertiesTest {

    @Autowired
    private ApplicationProperties properties;

    @Test
    void testConfigurationProperties() {
        Assertions.assertEquals("spring-config-properties", properties.getName());
        Assertions.assertEquals(1, properties.getVersion());
        Assertions.assertFalse(properties.isProductionMode());
    }

    /**
     * Test untuk database properties
     */
    @Test
    void testDatabaseProperties() {
        Assertions.assertEquals("cahya", properties.getDatabase().getUsername());
        Assertions.assertEquals("password", properties.getDatabase().getPassword());
        Assertions.assertEquals("belajar", properties.getDatabase().getDatabase());
        Assertions.assertEquals("jdbc:contoh", properties.getDatabase().getUrl());
    }

    /**
     * Tidak perli @Compenent atau @Bean lagi karena sudah EnableConfigurationProperties-nya.
     * Jadi spring boot otomatis akan membuatkan bean-nya.
     */
    @SpringBootApplication
    @EnableConfigurationProperties({
            ApplicationProperties.class
    })
    public static class TestApplication {

    }
}
