package com.essenstore.config;

import com.essenstore.deserializer.MultipartJackson2HttpMessageConverter;
import com.essenstore.validator.temp.BrandValidator;
import com.essenstore.validator.temp.CategoryValidator;
import com.essenstore.validator.temp.ColorValidator;
import com.essenstore.validator.temp.SizeValidator;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.List;
import java.util.Map;

@Configuration
@Setter(onMethod = @__(@Autowired))
public class RestConfig implements RepositoryRestConfigurer {

    private CategoryValidator categoryValidator;

    private BrandValidator brandValidator;

    private SizeValidator sizeValidator;

    private ColorValidator colorValidator;

    private MultipartJackson2HttpMessageConverter multipartJackson2HttpMessageConverter;

    @Override
    public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {
        var list = List.of("beforeCreate", "beforeSave");
        var validators = List.of(categoryValidator, brandValidator, sizeValidator, colorValidator);
        validatingListener.setValidators(Map.of(list.get(0), validators, list.get(1), validators));
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        var exposureConfig = config.getExposureConfiguration();

        exposureConfig.withItemExposure(((metaData, httpMethods) -> httpMethods.disable(HttpMethod.PATCH)));
    }

    @Override
    public void configureHttpMessageConverters(List<HttpMessageConverter<?>> messageConverters) {
        messageConverters.add(multipartJackson2HttpMessageConverter);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
