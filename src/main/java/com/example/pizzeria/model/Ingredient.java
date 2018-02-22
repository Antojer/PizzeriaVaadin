package com.example.pizzeria.model;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document
@Getter
@Setter
public class Ingredient implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	@Indexed(unique = true)
	private String nombre;
	
	private List<Pizza> pizzas = new ArrayList<>();
	
}
