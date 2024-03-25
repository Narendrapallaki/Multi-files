package com.delivery.customException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import com.delivery.dto.ResponseOpt;

@ControllerAdvice
public class GlobalExceptionHandler {

	
	
	@ExceptionHandler(ProductIdNotFound.class)
	public ResponseEntity<ResponseOpt>response(ProductIdNotFound ex)
	{
		
		ResponseOpt opt=new ResponseOpt();
		
		opt.setResponse(ex.getMessage());
		opt.setStatus(String.valueOf(HttpStatus.NOT_FOUND));
		
		return ResponseEntity.ok(opt);
	}
}
