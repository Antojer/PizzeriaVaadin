package com.example.pizzeria.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.pizzeria.model.Ingredient;

public interface IngredientDAO extends MongoRepository<Ingredient,String> {
	

}
