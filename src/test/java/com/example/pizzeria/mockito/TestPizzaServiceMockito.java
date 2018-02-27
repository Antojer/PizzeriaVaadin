package com.example.pizzeria.mockito;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.example.pizzeria.dao.PizzaDAO;
import com.example.pizzeria.model.Pizza;
import com.example.pizzeria.service.pizza.PizzaService;
import com.example.pizzeria.service.pizza.PizzaServiceImpl;

import Exception.InvalidDataException;
import Exception.NotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class TestPizzaServiceMockito {
	
	private final Pizza pizza = new Pizza();
	private final List<Pizza> pizzas = new ArrayList<>();
	
	private String id = "5a900d9bf711ec1be8e3a6c3";
	private String name = "carbonara";
	
	@InjectMocks
	private PizzaService pizzaService = new PizzaServiceImpl();
	
	@Mock
	private PizzaDAO pizzaDao;
	
	public void pizzaStarter() {
		pizza.setId(id);
		pizza.setName(name);
		pizzas.add(pizza);
	}
	
	public void Mockitos() {
		Mockito.when(pizzaDao.findAll()).thenReturn(pizzas);
		Mockito.when(pizzaDao.findOne(id)).thenReturn(pizza);
		Mockito.when(pizzaDao.save(pizza)).thenReturn(pizza);
	}
	
	@Before
	public void Initializer() {
		pizzaStarter();
		Mockitos();
	}
	
	@Test
	public void testFindAllFine() throws NotFoundException {
		final List<Pizza> res = pizzaService.findAll();
		Assert.assertEquals(pizzas.get(0).getId(), res.get(0).getId());
		Assert.assertEquals(pizzas.get(0).getName(), res.get(0).getName());
	}
	
	@Test(expected = NotFoundException.class)
	public void testFindAllWrong() throws NotFoundException{
		Mockito.when(pizzaDao.findAll()).thenReturn(null);
		pizzaService.findAll();
	}
	
	@Test
	public void testFindByIdFine() throws NotFoundException{
		final Pizza res = pizzaService.findById(id);
		Assert.assertEquals(pizza.getId(), res.getId());
		Assert.assertEquals(pizza.getName(), res.getName());
	}
	
	@Test(expected = NotFoundException.class)
	public void testFindByIdWrong() throws NotFoundException{
		pizzaService.findById("");
	}
	
	@Test
	public void createFine() throws InvalidDataException {
		final Pizza res = pizzaService.create(pizza);
		Assert.assertEquals(pizza.getId(), res.getId());
		Assert.assertEquals(pizza.getName(), res.getName());
	}
	
	@Test(expected = InvalidDataException.class)
	public void createWrong() throws InvalidDataException{
		pizzaService.create(new Pizza());
	}
	
	@Test
	public void updateFine() throws InvalidDataException{
		pizzaService.update(pizza);
	}
	
	@Test(expected=InvalidDataException.class)
	public void updateWrong() throws InvalidDataException{
		pizzaService.update(new Pizza());
	}
	
	@Test
	public void delete() throws InvalidDataException{
		pizzaService.delete(id);
	}
}
