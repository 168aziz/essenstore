package com.essenstore.config;

import com.essenstore.dto.ProductDto;
import com.essenstore.entity.*;
import com.essenstore.factory.EntityServiceFactory;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;

import java.util.stream.Collectors;

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

    @Bean
    public User emptyUser() {
        return new User();
    }

    @Bean
    public FactoryBean<?> serviceLocatorFactoryBean() {
        var serviceLocator = new ServiceLocatorFactoryBean();
        serviceLocator.setServiceLocatorInterface(EntityServiceFactory.class);
        return serviceLocator;
    }

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();


        return modelMapper;
    }
}
