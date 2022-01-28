package com.application.Infibeam.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = PhoneConstraintValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumber {

	 String message() default "Phone number is invalid";
	 
	    Class<?>[] groups() default {};
	 
	    Class<? extends Payload>[] payload() default {};
}
