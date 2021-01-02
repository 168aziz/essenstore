package com.essenstore.validator.temp;

import com.essenstore.entity.Brand;
import com.essenstore.service.BrandService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BrandValidator implements Validator {

    @Setter(onMethod = @__(@Autowired))
    private BrandService service;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Brand.class);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        var category = (Brand) obj;
        if (service.exists(category.getName())) {
            errors.rejectValue("name", "Name is exist", "Name is exist");
        }
    }
}
