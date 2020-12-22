package com.essenstore.config;

import com.essenstore.entity.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Product emptyProduct() {
        return new Product();
    }

    @Bean
    public Color emptyColor() {
        return new Color();
    }

    @Bean
    public Size emptySize() {
        return new Size();
    }

    @Bean
    public Brand emptyBrand() {
        return new Brand();
    }

    @Bean
    public Category emptyCategory() {
        return new Category();
    }

}
