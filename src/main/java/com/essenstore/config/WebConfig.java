package com.essenstore.config;

import com.essenstore.formatter.EntityServiceNameFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(serviceNameFormatter());
    }

    @Bean
    public EntityServiceNameFormatter serviceNameFormatter() {
        return new EntityServiceNameFormatter();
    }
}
