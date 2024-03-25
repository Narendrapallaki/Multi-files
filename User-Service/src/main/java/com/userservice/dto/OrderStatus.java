package com.userservice.dto;

import lombok.Data;

@Data
public class OrderStatus {

	
	private String item;

	private int remainingQty;

	private String status;

	private long orderId;
}
