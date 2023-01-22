package com.maro.crud.exception;

public class CityNotFoundException extends RuntimeException{
	
	public CityNotFoundException(String message) {
		super(message);
	}
}