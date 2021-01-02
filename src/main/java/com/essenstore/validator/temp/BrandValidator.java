package com.essenstore.validator.temp;

import com.essenstore.entity.Brand;
import com.essenstore.repository.BrandRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BrandValidator implements Validator {

    @Setter(onMethod = @__(@Autowired))
    private BrandRepository brandRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Brand.class);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        var category = (Brand) obj;
        if (brandRepository.existsByName(category.getName())) {
            errors.rejectValue("name", "Name is exist", "Name is exist");
        }
    }
}
