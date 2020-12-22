package com.essenstore.validator;

import com.essenstore.service.FieldUniqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class UniqueValidator implements ConstraintValidator<Unique, String> {

    private FieldUniqueService service;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void initialize(Unique unique) {
        Class<? extends FieldUniqueService> serviceClazz = unique.service();
        String serviceQualifier = unique.serviceQualifier();
        if (serviceQualifier.isEmpty())
            service = applicationContext.getBean(serviceClazz);
        else
            service = applicationContext.getBean(serviceClazz, serviceQualifier);
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        return !service.exists(name);
    }

}
