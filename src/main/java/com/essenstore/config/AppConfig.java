package com.essenstore.config;

import com.essenstore.dto.ProductDto;
import com.essenstore.dto.RegisterUserDto;
import com.essenstore.entity.*;
import com.essenstore.factory.EntityServiceFactory;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
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

    @Bean
    public User emptyUser() {
        return new User();
    }

    @Bean
    public ActivationCode activationCode() {
        return new ActivationCode();
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

        modelMapper.addMappings(new PropertyMap<Product, ProductDto>() {
            @Override
            protected void configure() {
                map().setBrandName(source.getBrand().getName());
            }
        });

        return modelMapper;
    }
}
