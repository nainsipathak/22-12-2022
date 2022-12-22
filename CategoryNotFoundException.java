package com.spring.data.jpa.exception;

public class CategoryNotFoundException extends Exception  {

	private static final long serialVersionUID = 3715634784897066670L;

	public  CategoryNotFoundException(String exception) {
			super(exception);
		}
	}
