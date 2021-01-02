package com.essenstore.converter;

import com.essenstore.entity.Brand;
import com.essenstore.repository.BrandRepository;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@Setter(onMethod = @__(@Autowired))
public class BrandFormatter implements Formatter<Brand> {

    private BrandRepository brandRepository;

    @Override
    public Brand parse(String id, Locale locale) {
        return StringUtils.isNumeric(id) ? brandRepository.getOne(Long.parseLong(id)) : null;
    }

    @Override
    public String print(Brand brand, Locale locale) {
        return brand.getName();
    }
}
