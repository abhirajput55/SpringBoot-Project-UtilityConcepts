package com.project.utility.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobleExceptionHandler {

	
	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<String>> handleValidationException(MethodArgumentNotValidException ex) {
		List<String> list = new ArrayList<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> list.add(error.getDefaultMessage()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(list);
	}
}
