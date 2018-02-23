package com.example.pizzeria.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document
public class Pizza implements Serializable{

	private static final long serialVersionUID = 1820983062739314305L;

	private String id;
	
	private List<Ingredient> ingredients = new ArrayList<>();
	
	private String name;
	
	public String ingredientsToString() {
		return ingredients.stream().map(x->x.getName())
            .collect(Collectors.joining(", "));
	}
	
}
