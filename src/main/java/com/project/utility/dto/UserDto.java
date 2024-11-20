package com.project.utility.dto;

import com.project.utility.customValiadtion.PhoneNumberValidation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	
	private String username;
	
	@PhoneNumberValidation
	private String phoneNumber;
	
}
