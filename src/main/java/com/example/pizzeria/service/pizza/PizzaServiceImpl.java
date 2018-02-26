package com.example.pizzeria.service.pizza;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pizzeria.dao.PizzaDAO;
import com.example.pizzeria.model.Pizza;

import Exception.InvalidDataException;
import Exception.NotFoundException;

@Service
public class PizzaServiceImpl implements PizzaService{
	
	@Autowired
	private PizzaDAO pizzaDao;
	
	@Override
	public List<Pizza> findAll() throws NotFoundException {
		final List<Pizza> pizzas = pizzaDao.findAll();
		return Optional.ofNullable(pizzas).orElseThrow(NotFoundException::new);
	}
	
	@Override
	public Pizza findById(String id) throws NotFoundException {
		final Pizza pizza = pizzaDao.findOne(id);
		return Optional.ofNullable(pizza).orElseThrow(NotFoundException::new);
	}
	
	@Override
	public Pizza create(Pizza pizza) throws InvalidDataException {
		if(validate(pizza))
			return pizzaDao.save(pizza);
		throw new InvalidDataException("Error, no hay datos suficientes");
	}
	
	private boolean validate(Pizza pizza) {
		return (pizza != null && pizza.getName() != null);
	}
	
	@Override
	public void update(Pizza pizza) throws InvalidDataException {
		if(validate(pizza))
			pizzaDao.save(pizza);
		throw new InvalidDataException("Error, no hay datos suficientes");
	}
	
	@Override
	public void delete(String id) {
		pizzaDao.delete(id);
	}
}
