package org.exam.www.controller;

import org.exam.www.model.CommandLogin;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class LoginValidator implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		return CommandLogin.class.isAssignableFrom(arg0);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mem_id", "required");
		ValidationUtils.rejectIfEmpty(errors, "mem_pass", "required");
	
	}
	

}
