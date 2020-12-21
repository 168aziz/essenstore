package com.essenstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "classpath:app.properties")
public class EssenseStoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(EssenseStoreApplication.class, args);
    }

}
