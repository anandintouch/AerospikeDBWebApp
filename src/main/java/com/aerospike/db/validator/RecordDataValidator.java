package com.aerospike.db.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.aerospike.db.model.Record;

/**
 * 
 * @author anandprakash
 *
 */
public class RecordDataValidator implements Validator{

	public boolean supports(Class clazz) {
		//just validate the credential instances
		return Record.class.isAssignableFrom(clazz);

	}

	public void validate(Object target, Errors errors) {

		// Record page validation
		/*ValidationUtils.rejectIfEmptyOrWhitespace(errors, "setName",
				"required.setName", "Set name is required.");*/
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "primaryKey",
				"required.primaryKey", "Primary IndexKey is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "binName",
				"required.binName", "Bin name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "binValue",
				"required.binValue", "Bin value is required.");
		
	
		Record cust = (Record)target;
		
		/*if(!(cust.getPassword().equals(cust.getConfirmPassword()))){
			errors.rejectValue("password", "notmatch.password");
		}*/
		

		/*
		if("NONE".equals(cust.getStageTypes())){
			errors.rejectValue("stageTypes", "required.stageTypes");
		}*/
		
	}
	


}
