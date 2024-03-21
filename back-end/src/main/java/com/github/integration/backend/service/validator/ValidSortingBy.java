package com.github.integration.backend.service.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = SortingByValidator.class)
@Documented
public @interface ValidSortingBy {

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
