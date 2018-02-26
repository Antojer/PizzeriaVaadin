package com.example.pizzeria.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDto implements Serializable{

	private static final long serialVersionUID = -9037445302589022506L;
	
	private Integer code;
	
	private String message;
	
	public ErrorDto(Integer code, String message){
		this.code = code;
		this.message = message;
	}
	
	public ErrorDto(){} 
}