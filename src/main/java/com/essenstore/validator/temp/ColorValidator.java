package com.essenstore.validator.temp;

import com.essenstore.entity.Category;
import com.essenstore.entity.Color;
import com.essenstore.service.CategoryService;
import com.essenstore.service.ColorService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ColorValidator implements Validator {

    @Setter(onMethod = @__(@Autowired))
    private ColorService service;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Color.class);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        var color = (Color) obj;
        if (service.exists(color.getName())) {
            errors.rejectValue("name", "Name is exist", "Name is exist");
        }
    }
}
