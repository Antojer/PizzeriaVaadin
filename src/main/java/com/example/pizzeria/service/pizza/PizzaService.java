package com.example.pizzeria.service.pizza;

import java.util.List;

import com.example.pizzeria.model.Pizza;

import Exception.InvalidDataException;
import Exception.NotFoundException;

public interface PizzaService {

	List<Pizza> findAll() throws NotFoundException;

	Pizza findById(String id) throws NotFoundException;

	Pizza create(Pizza pizza) throws InvalidDataException;

	void update(Pizza pizza) throws InvalidDataException;

	void delete(String id);

}
