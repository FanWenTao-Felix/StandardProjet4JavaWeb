package com.kxw.validator;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class CheckDigitValidator implements
		ConstraintValidator<CheckDigit, String> {

	@Override
	public void initialize(CheckDigit constraintAnnotation) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (StringUtils.isBlank(value))
			return true;
		boolean is = true;
		for (int i = 0; i < value.length(); i++) {
			is = Character.isDigit(value.charAt(i));
			if (!is) {
				break;
			}
		}
		return is;
	}

}
