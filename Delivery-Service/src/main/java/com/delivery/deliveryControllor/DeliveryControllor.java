package com.delivery.deliveryControllor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.delivery.deliveryService.DeliveryService;
import com.delivery.dto.ResponseDto;
import com.delivery.entitys.ConsumerDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
public class DeliveryControllor {

	@Autowired
	private DeliveryService deliveryService;

	@PostMapping("/saveUser")
	public ResponseEntity<ConsumerDetails> saveUserData(@RequestBody ConsumerDetails details) {

		ConsumerDetails saveCompanyData = deliveryService.saveCompanyData(details);

		return ResponseEntity.ok(saveCompanyData);

	}
	
	@GetMapping("/getUser/{productId}")
	public ResponseEntity<Object>getData(@PathVariable String productId) throws JsonMappingException, JsonProcessingException
	{
		ResponseDto responseOpt = deliveryService.responseOpt(productId);
		
		return ResponseEntity.ok(responseOpt);
	}
}
