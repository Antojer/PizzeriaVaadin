package com.example.pizzeria.service.ingredient;

import java.util.List;

import com.example.pizzeria.model.Ingredient;

import Exception.InvalidDataException;
import Exception.NotFoundException;


public interface IngredientService {
	
	Ingredient findById(String id) throws NotFoundException;
	
	List<Ingredient> findAll() throws NotFoundException;
	
	Ingredient create(Ingredient ingredient) throws InvalidDataException;
	
	void update(String id, Ingredient ingredient);
	
	void delete(String idIngredient);
}
