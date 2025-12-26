package com.info.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	public ResponseEntity<String> handleUnauthrized(UnauthrizedException e){
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
	}
	@ExceptionHandler(Exception.class)
	public  ResponseEntity<String> handleInternalserverError(Exception e){
		e.printStackTrace();
		return ResponseEntity.internalServerError().body("OOps! something went wrong....");
		
	}
	
}
