package com.example.pizzeria.service.ingredient;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.pizzeria.exception.NotFoundExcept;
import com.example.pizzeria.model.Ingredient;


public interface IngredientService {
	
	Ingredient findById(String id) throws NotFoundExcept;
	
	List<Ingredient> findAll(Integer page, Integer size);
	
	Ingredient create(Ingredient ingredient);
	
	void update(String id, Ingredient ingredient);
	
	void delete(String idIngredient);
}
