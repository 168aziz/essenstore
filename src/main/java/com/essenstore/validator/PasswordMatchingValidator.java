package com.essenstore.validator;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchingValidator implements ConstraintValidator<PasswordMatch, Object> {

    private String field;

    private String fieldMatch;

    private String msg;

    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
        field = constraintAnnotation.field();
        fieldMatch = constraintAnnotation.fieldMatch();
        msg = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        Object fieldValue = new BeanWrapperImpl(obj).getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(obj).getPropertyValue(fieldMatch);

        boolean isValid = false;

        if (fieldValue != null)
            isValid = fieldValue.equals(fieldMatchValue);

        if (!isValid) {
            constraintValidatorContext.disableDefaultConstraintViolation();

            constraintValidatorContext
                    .buildConstraintViolationWithTemplate(msg)
                    .addPropertyNode(field)
                    .addConstraintViolation();

            constraintValidatorContext
                    .buildConstraintViolationWithTemplate(msg)
                    .addPropertyNode(field)
                    .addConstraintViolation();
        }

        return isValid;
    }
}
