package com.example.pizzeria.service.pizza;

import java.util.List;

import com.example.pizzeria.model.Pizza;

public interface PizzaService {

	List<Pizza> findAll();

	Pizza findById(String id);

	Pizza create(Pizza pizza);

	void update(Pizza pizza);

	void delete(String id);

}
