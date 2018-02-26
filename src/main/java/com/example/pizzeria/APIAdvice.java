package com.example.pizzeria;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.pizzeria.dto.ErrorDto;

import Exception.InvalidDataException;
import Exception.NotFoundException;

@ResponseBody
@ControllerAdvice(basePackages = "com.example.pizzeria.controller, com.example.pizzeria.service")
public class APIAdvice {
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorDto notFound(NotFoundException e) {
		return new ErrorDto(404, e.getMessage());
	}
	
	@ExceptionHandler(InvalidDataException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDto notInvalidData(InvalidDataException e) {
		return new ErrorDto(400, e.getMessage());
	}
}