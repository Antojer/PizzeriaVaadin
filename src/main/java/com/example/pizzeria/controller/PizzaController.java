package com.example.pizzeria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.pizzeria.model.Pizza;
import com.example.pizzeria.service.pizza.PizzaService;

import Exception.InvalidDataException;
import Exception.NotFoundException;

@RestController
@RequestMapping(value = "/api/pizza")
public class PizzaController {
	
	@Autowired
	PizzaService pizzaService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Pizza> getAll() throws NotFoundException {
		return pizzaService.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Pizza getById(@PathVariable(value="id") String id) throws NotFoundException {
		return pizzaService.findById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Pizza create(@RequestBody Pizza pizza) throws InvalidDataException {
		return pizzaService.create(pizza);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public void update(@RequestBody Pizza pizza) throws InvalidDataException {
		pizzaService.update(pizza);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable(value = "id") String id) {
		pizzaService.delete(id);
	}
}
