package com.essenstore.config;

import com.essenstore.converter.*;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Setter(onMethod = @__(@Autowired))
public class AppConfig implements WebMvcConfigurer {

    private MultipartImagesFileConverter multipartImagesFileConverter;

    private BrandFormatter brandFormatter;

    private CategoryFormatter categoryFormatter;

    private ColorFormatter colorFormatter;

    private SizeFormatter sizeFormatter;

    private GenderFormatter genderFormatter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(multipartImagesFileConverter);
        registry.addFormatter(brandFormatter);
        registry.addFormatter(categoryFormatter);
        registry.addFormatter(colorFormatter);
        registry.addFormatter(sizeFormatter);
        registry.addFormatter(genderFormatter);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
