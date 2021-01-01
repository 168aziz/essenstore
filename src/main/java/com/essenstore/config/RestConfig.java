package com.essenstore.config;

import com.essenstore.validator.ProductValidator;
import com.essenstore.validator.temp.BrandValidator;
import com.essenstore.validator.temp.CategoryValidator;
import com.essenstore.validator.temp.ColorValidator;
import com.essenstore.validator.temp.SizeValidator;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.List;
import java.util.Map;

@Configuration
@Setter(onMethod = @__(@Autowired))
public class RestConfig implements RepositoryRestConfigurer {

    private CategoryValidator categoryValidator;

    private BrandValidator brandValidator;

    private SizeValidator sizeValidator;

    private ColorValidator colorValidator;

    private ProductValidator productValidator;

    @Bean
    @Primary
    public Validator validatorBean() {
        return new LocalValidatorFactoryBean();
    }


    @Override
    public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {
        var list = List.of("beforeCreate", "beforeSave");
        var validators = List.of(categoryValidator, brandValidator, sizeValidator, colorValidator, productValidator);

        validatingListener.setValidators(Map.of(list.get(0), validators, list.get(1), validators));

    }
}
