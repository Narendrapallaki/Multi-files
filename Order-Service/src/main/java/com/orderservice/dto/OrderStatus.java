package com.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatus {

	
	private String item;

	private int remainingQty;

	private String status;

	private long orderId;
}
