package com.delivery.customException;

public class ProductIdNotFound extends RuntimeException{

	
	public ProductIdNotFound(String message)
	{
		super(message);
	}
}
