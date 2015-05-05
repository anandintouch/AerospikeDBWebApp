package com.aerospike.db.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.aerospike.db.model.Credential;

/**
 * 
 * @author anandprakash
 *
 */
public class FormInputValidator implements Validator{

	public boolean supports(Class clazz) {
		//just validate the credential instances
		return Credential.class.isAssignableFrom(clazz);

	}

	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username",
				"required.username", "Username name is mandatory.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
				"required.password", "Password is required.");
	
	
		Credential cust = (Credential)target;
		
		/*
		if("NONE".equals(cust.getStageTypes())){
			errors.rejectValue("stageTypes", "required.stageTypes");
		}*/
		if(cust.getPort() == 0){
			errors.rejectValue("port", "required.port");
		}
		
	}
	
}