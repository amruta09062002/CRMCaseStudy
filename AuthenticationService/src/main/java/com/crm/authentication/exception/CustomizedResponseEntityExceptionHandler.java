package com.crm.authentication.exception;

import java.time.LocalDate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
 
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		
		// Fill the code here
		ExceptionResponse exception = new ExceptionResponse(LocalDate.now(), ex.getMessage(), request.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		return new ResponseEntity<>(exception,HttpStatus.INTERNAL_SERVER_ERROR) ;
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
		HttpHeaders headers, HttpStatusCode status, WebRequest request) {	
		    
		ExceptionResponse exception = new ExceptionResponse(LocalDate.now(), ex.getMessage(), request.getDescription(false), HttpStatus.BAD_REQUEST.getReasonPhrase());
		return new ResponseEntity<>(exception,HttpStatus.BAD_REQUEST);
	}
	
}