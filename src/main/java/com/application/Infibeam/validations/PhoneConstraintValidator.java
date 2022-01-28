package com.application.Infibeam.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneConstraintValidator implements ConstraintValidator<PhoneNumber, String>{

	
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value==null || value.length()!=10) {
			return false;
		}
		return value.matches("[0-9]+");
	}

}
