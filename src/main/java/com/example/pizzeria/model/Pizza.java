package com.example.pizzeria.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document
public class Pizza implements Serializable{

	private static final long serialVersionUID = 1820983062739314305L;

	private String id;
	
	private List<Ingredient> ingredients;
	
	@Indexed(unique = true)
	private String name;
	
}
