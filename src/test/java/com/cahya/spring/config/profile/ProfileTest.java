package com.cahya.spring.config.profile;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = ProfileTest.TestApplication.class)
/**
 * Ini jika pakai @ActiveProfiles("local") pada class ini,
 * sehingga @Profile("local") pada SayHelloLocal yang akan aktif.
 */
@ActiveProfiles({"production"})
public class ProfileTest {
    @Autowired
    private TestApplication.SayHello sayHello;

    // Ini yang harus disesuaikan, misal antara local dan production
    @Test
    void testProfile() {
        Assertions.assertEquals("Hello Cahya from Production", sayHello.say("Cahya"));
    }

    @SpringBootApplication
    public static class TestApplication {
        /**
         * Contoh menggunakan @Profile untuk menentukan bean yang aktif
         * Kalau tidak menambahkan @Profile, dua"nya tidak akan dibuat,
         * karena defaultnya adalah @Profile("default")
         * <p>
         * TAPI jika tidak ditambahkan @Profile, yakni hanya @Component saja,
         * maka keduanya akan dijalankan, karena tidak ada profile yang membatasi.
         * Atau kita pakai @Profile("default") pada keduanya.
         *
         * <p>
         * Ini yng berjalan adalah local, karena pada
         * application.properties kita set spring.profiles.active=local
         */

        public interface SayHello {
            String say(String name);
        }

        /**
         * JIKA sudah ada @ActiveProfiles("local") pada class di atas,
         * pada class ini tidak perlu lagi menambahkan @Profile("local")
         * begitu juga untuk @Profile("production").
         */
        @Component
        @Profile("local")
        public static class SayHelloLocal implements SayHello {
            @Override
            public String say(String name) {
                return "Hello " + name + " from Local";
            }
        }

        @Component
        @Profile("production")
        public static class SayHelloProduction implements SayHello {
            @Override
            public String say(String name) {
                return "Hello " + name + " from Production";
            }
        }
    }
}
