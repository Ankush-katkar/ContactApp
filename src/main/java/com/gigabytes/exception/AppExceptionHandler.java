package com.gigabytes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class AppExceptionHandler {
	@ExceptionHandler(value=NoDataFoundException.class)
	public ResponseEntity<String> handleNoDataFoundException (NoDataFoundException ndfe){
		String exMsg = ndfe.getMessage();
		return new ResponseEntity<>(exMsg, HttpStatus.BAD_REQUEST);
		
	}

}
