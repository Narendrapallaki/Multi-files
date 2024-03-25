package com.orderservice.orderController;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import com.orderservice.dto.CustomerOrder;
import com.orderservice.dto.OrderStatus;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;

//@Slf4j
@RestController
@RequestMapping("/api")
public class OrderController {

	private RestTemplate restTemplate;

	private static final String STOCK_API = "http://localhost:2001/api/updateStock";

	public OrderController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@PostMapping("/orders")
	@CircuitBreaker(name = "orderMS", fallbackMethod = "fallbackMethod")
	//@Retry(name="orderMS",fallbackMethod = "fallbackMethod")
	public OrderStatus doOrder(@RequestBody CustomerOrder customerOrder) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<CustomerOrder> request = new HttpEntity<>(customerOrder, headers);

		ResponseEntity<OrderStatus> responseEntity = restTemplate.postForEntity(STOCK_API, request, OrderStatus.class);
		OrderStatus orderStatus = responseEntity.getBody();

		System.out.println("Order Status::" + orderStatus);

		return orderStatus;
	}
	
	public OrderStatus fallbackMethod(Throwable throwable) {
		OrderStatus orderStatus = new OrderStatus();
		orderStatus.setStatus("Called Fallback Method");
		return orderStatus;
	}
}
