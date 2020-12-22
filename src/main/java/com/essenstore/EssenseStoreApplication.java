package com.essenstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
@PropertySource(value = "classpath:app.properties")
public class EssenseStoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(EssenseStoreApplication.class, args);
    }

}
