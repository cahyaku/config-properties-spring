package com.cahya.spring.config.configurationsproperties;

import com.cahya.spring.config.properties.ApplicationProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

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
     * Embedded Collection
     */
    @Test
    void testEmbeddedCollection() {
        Assertions.assertEquals("default", properties.getDefaultRoles().get(0).getId());
        Assertions.assertEquals("Default Role", properties.getDefaultRoles().get(0).getName());
        Assertions.assertEquals("guest", properties.getDefaultRoles().get(1).getId());
        Assertions.assertEquals("Guest Role", properties.getDefaultRoles().get(1).getName());

        Assertions.assertEquals("admin", properties.getRoles().get("admin").getId());
        Assertions.assertEquals("Admin Role", properties.getRoles().get("admin").getName());
        Assertions.assertEquals("finance", properties.getRoles().get("finance").getId());
        Assertions.assertEquals("Finance Role", properties.getRoles().get("finance").getName());
    }

    /**
     * Test Collection configuration properties
     */
    @Test
    void testCollection() {
        Assertions.assertEquals(
                Arrays.asList("products", "customers", "categories"),
                properties.getDatabase().getWhiteListTables()
        );
        Assertions.assertEquals(100, properties.getDatabase().getMaxTableSize().get("products"));
        Assertions.assertEquals(100, properties.getDatabase().getMaxTableSize().get("customers"));
        Assertions.assertEquals(100, properties.getDatabase().getMaxTableSize().get("categories"));
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
