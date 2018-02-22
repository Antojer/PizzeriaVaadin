package com.example.pizzeria.model;

import java.awt.List;
import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Document
@Getter
@Setter
public class Ingredient implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	private String nombre;
	
	private List<Pizza> pizzas = new ArrayList<>();
	
}
