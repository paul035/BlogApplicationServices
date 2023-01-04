package com.paulsofts.blogapplicationservices.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.paulsofts.blogapplicationservices.utils.GenericResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<GenericResponse<String>> resourceNotFoundExceptionHandler(ResourceNotFoundException resourceNotFoundException){
		String message = resourceNotFoundException.getMessage();
		return new ResponseEntity<GenericResponse<String>>(new GenericResponse<String>(message, "Not Found", "False" ), HttpStatus.NOT_FOUND);
		
	}

}
