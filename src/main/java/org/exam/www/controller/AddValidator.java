package org.exam.www.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.exam.www.model.MemberVO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class AddValidator implements Validator {
	private static final String emailRegExp=
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+
					"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	private Pattern pattern;

	public AddValidator() {
		pattern=Pattern.compile(emailRegExp);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		MemberVO member=(MemberVO)target;
		if(member.getMem_email()==null||member.getMem_email().trim().isEmpty()) {
			errors.rejectValue("mem_email", "required");
		}else {
			Matcher matcher=pattern.matcher(member.getMem_email());
			if(!matcher.matches()) {
				errors.rejectValue("mem_email","bad");
			}
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"mem_name", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"mem_id", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"mem_pass", "required");
	}

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return MemberVO.class.isAssignableFrom(arg0);
	}
	
	
}
