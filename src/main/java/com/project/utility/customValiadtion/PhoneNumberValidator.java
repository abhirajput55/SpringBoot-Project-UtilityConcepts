package com.project.utility.customValiadtion;

import java.util.Map;
import java.util.Objects;

import com.project.utility.externalAPIs.PhoneNumberValidationAPI;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberValidation, String> {
	
	private final PhoneNumberValidationAPI phoneNumberValidationAPI;
	
	public PhoneNumberValidator(PhoneNumberValidationAPI phoneNumberValidationAPI) {
		this.phoneNumberValidationAPI = phoneNumberValidationAPI;
	}
	

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if(Objects.isNull(value))
			return false;
		
//		Map<String, Object> response = phoneNumberValidationAPI.checkPhoneNumber(value);
		return phoneNumberValidationAPI.isValidIndianPhoneNumber(value);
		
//		return (boolean)response.get("suceess") && (boolean)response.get("isValidNumber");
	}

}
