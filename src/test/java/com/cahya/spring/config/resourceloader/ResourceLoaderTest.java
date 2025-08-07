package com.cahya.spring.config.resourceloader;

import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

/**
 * Dibuat di dalam package,
 * karena spring boot otomatis akan scan semua komponen yang ada di dalam packagenya.
 */
@SpringBootTest(classes = ResourceLoaderTest.TestApplication.class)
public class ResourceLoaderTest {
    // Inject komponen sampleResource
    @Autowired
    private TestApplication.SampleResource sampleResource;

    /**
     * Test untuk ResourceLoader.
     * ResourceLoader digunakan untuk mengambil resource dari classpath.
     * Isinya harus sesuai dengan yang ada di dalam file resource.txt
     */
    @Test
    void testResourceLoader() throws Exception {
        Assertions.assertEquals("Cahya Kumala-03 Agustus 2025", sampleResource.getText().trim());
    }

    @SpringBootApplication
    public static class TestApplication {

        @Component
        public static class SampleResource implements ResourceLoaderAware {

            //gunakan lombok @Setter, biar tidak manual
            @Setter
            private ResourceLoader resourceLoader;

            /**
             * Membuat method getText() dengan kembalian String.
             */
            public String getText() throws Exception {
                Resource resource = resourceLoader.getResource("classpath:/text/resource.txt");
                try (var inputStream = resource.getInputStream()) {
                    return new String(inputStream.readAllBytes()); // Membaca semua isi file resource.txt
                }
            }
        }

    }
}
