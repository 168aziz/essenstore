package com.essenstore.validator.temp;

import com.essenstore.entity.Category;
import com.essenstore.service.CategoryService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CategoryValidator implements Validator {

    @Setter(onMethod = @__(@Autowired))
    private CategoryService service;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Category.class);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        var category = (Category) obj;
        if (service.exists(category.getName())) {
            errors.rejectValue("name", "Name is exist", "Name is exist");
        }
    }
}
