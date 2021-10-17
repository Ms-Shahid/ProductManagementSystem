package com.company.ProductManagementSystem.exceptionhandler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice //this annotation handles exception globally
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map((error->error.getDefaultMessage()))
				.collect(Collectors.toList());
		return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	// This annotation is a custom exception response handler, which sends to client
	public ResponseEntity<?> handleProductNotFoundException(ProductNotFoundException exception,
			WebRequest request){
		ProductErrorHandler errorHandler = new ProductErrorHandler(new Date(), exception.getMessage(),request.getDescription(false));
		return new ResponseEntity(errorHandler, HttpStatus.NOT_FOUND);
	}
}