package com.orderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Orders {
	
	
	 private int id;
	    private String name;
	    private String category;
	    private String color;
	    private double price;

}
