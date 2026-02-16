package com.ecommerce.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptinoHandler {
		@ExceptionHandler(Exception.class)
	   public ResponseEntity<String> handleInternalServer(Exception e){
		   e.printStackTrace();
		   return ResponseEntity.internalServerError().body(e.getMessage());
	   }
}
