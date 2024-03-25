package com.companyservice.customException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.companyservice.dto.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ProductIdNotFound.class)
	public ResponseEntity<ResponseStatus> result(ProductIdNotFound pif) {

		ResponseStatus status = new ResponseStatus();

		status.setResponse(pif.getMessage());
		status.setStatus(String.valueOf(HttpStatus.NOT_FOUND));

		return ResponseEntity.ok(status);
	}

}
