package com.essenstore.validator.temp;

import com.essenstore.entity.Size;
import com.essenstore.repository.SizeRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SizeValidator implements Validator {

    @Setter(onMethod = @__(@Autowired))
    private SizeRepository sizeRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Size.class);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        var size = (Size) obj;
        if (sizeRepository.existsByName(size.getName())) {
            errors.rejectValue("name", "Name is exist", "Name is exist");
        }
    }
}
