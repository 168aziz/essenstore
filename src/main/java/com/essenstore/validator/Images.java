package com.essenstore.validator;

import com.essenstore.service.FieldUniqueService;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ImageValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Images {

    String message() default "Image not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
