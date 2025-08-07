package com.cahya.spring.config.value;

import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

@SpringBootTest(classes = ValueTest.TestApplication.class)
public class ValueTest {

    @Autowired
    private TestApplication.ApplicationProperties properties;

    //    @Autowired
//    private TestApplication.SystemProperties systemProperties;

    //    @Test
//    void testSystemProperties() {
//        Assertions.assertEquals("/usr/lib/jvm/java-21-openjdk-amd64", systemProperties.getJavaHome());
//    }
    @Test
    void testValue() {
        Assertions.assertEquals("spring-config-properties", properties.getName());
        Assertions.assertEquals(1, properties.getVersion());
        Assertions.assertFalse(properties.isProductionMode());
    }

    @SpringBootApplication
    public static class TestApplication {

//        @Component
//        @Getter
//        public static class SystemProperties {
//            @Value("${JAVA_HOME}")
//            private String javaHome;
//        }

        @Component
        @Getter
        public static class ApplicationProperties {

            // Biasanya kan pakai @Autowired
            // Cuma itu akan mencari spring bean dari ApplicationContext

            // JIKA hanya ingin mengambil yang ada di dalam application.properties, pakai @Value
            // masukan nama property yang akan diambil.
            @Value("${spring.application.name}")
            private String name;

            @Value("${spring.application.version}")
            private Integer version;

            @Value("${spring.application.production-mode}")
            private boolean productionMode;
        }
    }
}
