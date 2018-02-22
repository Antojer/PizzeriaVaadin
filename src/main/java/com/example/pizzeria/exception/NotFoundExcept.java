package com.example.pizzeria.exception;

public class NotFoundExcept extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	private static final String errorMsg = "No encontrado";
	
	public NotFoundExcept(String msg)
	{
		super(msg);
	}
	
	public NotFoundExcept()
	{
		super(errorMsg);
	}

}