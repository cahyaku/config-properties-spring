package com.cahya.spring.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Contoh menggunakan @ConfigurationProperties
 * dengan prefix "spring.application"
 * karena di application.properties diawali dengan "spring.application"
 * jika application saja, maka prefix-nya tinggal ditulis "application".
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "spring.application")
public class ApplicationProperties {
    /**
     * Contoh untuk conversion, tambahan Duration
     */
    private Duration defaultTimeout;

    /**
     * Contoh conversion ke tipe custom
     */
    private Date expiredDate;
    
    private String name;
    private Integer version;
    private boolean productionMode;
    // dimana database punya 4 atribut, seperti dibawah ini
    private DatabaseProperties database;

    // embedded properties
    private List<Role> defaultRoles;
    private Map<String, Role> roles;

    /**
     * Database properties
     */
    @Getter
    @Setter
    public static class DatabaseProperties {
        private String username;
        private String password;
        private String database;
        private String url;

        /**
         * Tambahan List dan Map
         * setelah ini lakukan run mvn clean compile.
         */
        private List<String> whiteListTables;
        private Map<String, Integer> maxTableSize;
    }

    /**
     * Contoh untuk embedded properties
     */
    @Getter
    @Setter
    public static class Role {
        private String id;
        private String name;
    }
}
