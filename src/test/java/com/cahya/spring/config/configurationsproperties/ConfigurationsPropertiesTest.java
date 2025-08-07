package com.cahya.spring.config.configurationsproperties;

import com.cahya.spring.config.properties.ApplicationProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;

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
     * Test untuk duration
     */
    @Test
    void testDuration() {
        Assertions.assertEquals(Duration.ofSeconds(10), properties.getDefaultTimeout());
    }

    /**
     * Test untuk tipe custom
     * Kalau ini di run akan error karena belum ada converter-nya.
     * Jadi kita harus registrasukan ke ConversionService
     */
    @Test
    void testCustomConverter() {
        Date expiredDate = properties.getExpiredDate();
        var dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Assertions.assertEquals("2023-08-03", dateFormat.format(expiredDate));
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
