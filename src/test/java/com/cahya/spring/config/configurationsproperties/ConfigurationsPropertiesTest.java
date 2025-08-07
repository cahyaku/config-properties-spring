package com.cahya.spring.config.configurationsproperties;

import com.cahya.spring.config.converter.StringToDateConverter;
import com.cahya.spring.config.properties.ApplicationProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.ConversionService;

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
     * Contoh konversi secara manual, pakai konversi service
     * Conversion Service dan test conversion service
     */
    @Autowired
    private ConversionService conversionService;

    @Test
    void testConversionService() {
        Assertions.assertTrue(conversionService.canConvert(String.class, Duration.class));
        Assertions.assertTrue(conversionService.canConvert(String.class, Date.class));

        // ini kita coba lakukan conversion
        Duration result = conversionService.convert("10s", Duration.class);
        Assertions.assertEquals(Duration.ofSeconds(10), result);
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
        var dateFormat = new SimpleDateFormat("yyyy-MM-dd");
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
    @Import(StringToDateConverter.class) // Import custom converter
    public static class TestApplication {
        /**
         * Conversion Service
         * Karena kita sudah membuat custom converter, maka kita import class ini, jadi di atas kita import.
         */
        @Bean
        public ConversionService conversionService(StringToDateConverter stringToDateConverter) {
            ApplicationConversionService conversionService = new ApplicationConversionService();
            conversionService.addConverter(stringToDateConverter);
            return conversionService;
        }
    }
}
