package com.project.utility.externalAPIs;

import static java.util.Objects.isNull;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;


@Component
@PropertySource("classpath:confidential.properties")
public class PhoneNumberValidationAPI {
	
	@Value("${rapid.api.key}")
	private String xRapidApiKey;
	
	private static final String API_KEY_NAME = "x-rapidapi-key";
	private static final String API_HOST_NAME = "x-rapidapi-host"; 
	private static final String API_HOST_VALUE = "phonenumbervalidatefree.p.rapidapi.com"; 
	private static final String SUCCESS = "success"; 
	
	public Map<String, Object> checkPhoneNumber(String phoneNumber) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(SUCCESS, false);
		
		
		try {
			String url = "https://phonenumbervalidatefree.p.rapidapi.com/ts_PhoneNumberValidateTest.jsp?" + 
					"number=" + phoneNumber + "&country=" + getRegionCode(phoneNumber);
			
			HttpResponse<String> response = Unirest.get(url)
					.header(API_KEY_NAME, xRapidApiKey)
					.header(API_HOST_NAME, API_HOST_VALUE)
					.asString();
			
			if(response.getStatus() == 200) {
				map.put(SUCCESS, true);
				map.put("Data", new JSONObject(response.getBody()));
				map.put("isValidNumber", new JSONObject(response.getBody()).get("isValidNumber"));
			}			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return map;
	}
	
	public String getRegionCode(String phoneNumber) throws NumberParseException {
		if (isNull(phoneNumber) || phoneNumber.isEmpty())
			return null;
		if (!phoneNumber.startsWith("+"))
			return "IN";

		PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
		PhoneNumber phoneNumberObject = phoneNumberUtil.parse(phoneNumber, phoneNumber.substring(1, 3));
		return phoneNumberUtil.isValidNumber(phoneNumberObject)
				? phoneNumberUtil.getRegionCodeForNumber(phoneNumberObject)
				: null;
	}
	
	
	 // Method to validate Indian phone number
    public boolean isValidIndianPhoneNumber(String phoneNumber) {
        // Regex for matching Indian phone numbers
        String regex = "^(?:\\+91|91)?[789]\\d{9}$";
        
        // Compile the regex
        Pattern pattern = Pattern.compile(regex);
        
        // Check if the phone number matches the pattern
        Matcher matcher = pattern.matcher(phoneNumber);
        
        // Return true if the phone number matches the pattern, otherwise false
        return matcher.matches();
    }


}
