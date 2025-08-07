package com.cahya.spring.config.environment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest(classes = EnvironmentTest.TestApplication.class)
public class EnvironmentTest {

    @Autowired
    private Environment environment;

    @Test
    void testEnvironment() {
        String javaHome = environment.getProperty("JAVA_HOME");
//        Assertions.assertEquals("/usr/lib/jvm/java-21-openjdk-amd64/bin/java", javaHome);
        System.out.println("JAVA_HOME = " + javaHome);
        // ini hasilnya masih null karena belum ada JAVA_HOME yang di set
    }

    @SpringBootApplication
    public static class TestApplication {

    }

}
