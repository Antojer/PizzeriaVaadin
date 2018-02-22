package com.example.pizzeria.service.pizza;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pizzeria.dao.PizzaDAO;
import com.example.pizzeria.model.Pizza;

@Service
public class PizzaServiceImpl implements PizzaService{
	
	@Autowired
	private PizzaDAO pizzaDao;
	
	@Override
	public List<Pizza> findAll() {
		return pizzaDao.findAll();
	}
	
	@Override
	public Pizza findById(String id) {
		return pizzaDao.findOne(id);
	}
	
	@Override
	public Pizza create(Pizza pizza) {
		return pizzaDao.save(pizza);
	}
	
	@Override
	public void update(Pizza pizza) {
		pizzaDao.save(pizza);
	}
	
	@Override
	public void delete(String id) {
		pizzaDao.delete(id);
	}
}
