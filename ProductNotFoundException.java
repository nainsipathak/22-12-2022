package com.spring.data.jpa.exception;

public class ProductNotFoundException extends Exception{
	private static final long serialVersionUID = -67797093571386170L;

	public  ProductNotFoundException(String exception) {
		super(exception);
	}

}
