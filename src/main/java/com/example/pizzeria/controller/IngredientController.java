package com.example.pizzeria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pizzeria.model.Ingredient;
import com.example.pizzeria.service.ingredient.IngredientService;

import Exception.NotFoundException;

@RestController
@RequestMapping(value = "/api/ingredient")

public class IngredientController {
	
	@Autowired
	private IngredientService ingredientService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Ingredient> retrieveAll()
	{
		return ingredientService.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Ingredient findOne(@PathVariable("id") String id) throws NotFoundException
	{
		return ingredientService.findById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Ingredient create(@RequestBody Ingredient ingredient)
	{
		return ingredientService.create(ingredient);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void update(@PathVariable("id") String id, @RequestBody Ingredient ingredient)
	{
		ingredientService.update(id,ingredient);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void update(@PathVariable("id") String id)
	{
		ingredientService.delete(id);
}

}
