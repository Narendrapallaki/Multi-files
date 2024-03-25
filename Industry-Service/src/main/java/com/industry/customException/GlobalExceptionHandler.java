package com.industry.customException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.industry.dto.ResponseOpt;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	
	@ExceptionHandler(IndustryIdNotFound.class)
	public ResponseEntity<ResponseOpt>responseOpt(IndustryIdNotFound ex)
	{
		ResponseOpt rOpt=new ResponseOpt();
		
		rOpt.setResponse(ex.getMessage());
		rOpt.setStatus(String.valueOf(HttpStatus.NOT_FOUND));
		
		
		return ResponseEntity.ok(rOpt);
		
	}

}
