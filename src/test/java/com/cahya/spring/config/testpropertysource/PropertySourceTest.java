package com.cahya.spring.config.testpropertysource;

import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(locations = "classpath:/test.properties")
@SpringBootTest(classes = PropertySourceTest.TestApplication.class)
public class PropertySourceTest {

    @Autowired
    private TestApplication.SampleProperties properties;

    @Test
    void testPropertySource() {
        Assertions.assertEquals("Sample Project Test", properties.getName());
        Assertions.assertEquals(1, properties.getVersion());
    }

    // Tidak perlu propertysource lagi karena sudah pakai @TestPropertySource di atas.
    @SpringBootApplication
//    @PropertySources({
//            @PropertySource("classpath:/sample.properties")
//    })
    public static class TestApplication {

        @Component
        @Getter
        public static class SampleProperties {
            @Value("${sample.name}")
            private String name;

            @Value("${sample.version}")
            private Integer version;
        }
    }
}
