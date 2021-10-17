package com.company.ProductManagementSystem.exceptionhandler;

public class ProductNotFoundException extends RuntimeException {

	public ProductNotFoundException(String message) {
		super(message);
	}

}
