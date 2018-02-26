package com.example.pizzeria.service;

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
import com.example.pizzeria.dao.IngredientDAO;
import com.example.pizzeria.model.Ingredient;
import com.example.pizzeria.service.ingredient.IngredientService;
import com.example.pizzeria.service.ingredient.IngredientServiceImpl;
import Exception.InvalidDataException;
import Exception.NotFoundException;


@RunWith(MockitoJUnitRunner.class)
public class TestIngredientService {

	private static final String ID = "5a93fb512d48f8fcbb12ab81";
	private static final String NAME = "Chorizo";
	private static final Ingredient INGREDIENT = new Ingredient();
	private static final List<Ingredient> LIST_INGREDIENTS = new ArrayList<Ingredient>();
	
	@InjectMocks
	private IngredientService ingredientService = new IngredientServiceImpl();
		
	@Mock
	IngredientDAO ingredientDao;
	
	@Before
	public void init() {
		INGREDIENT.setId(ID);
		INGREDIENT.setName(NAME);
		LIST_INGREDIENTS.add(INGREDIENT);
		Mockito.when(ingredientDao.findOne(ID)).thenReturn(INGREDIENT);
		Mockito.when(ingredientDao.findAll()).thenReturn(LIST_INGREDIENTS);
		Mockito.when(ingredientDao.save(INGREDIENT)).thenReturn(INGREDIENT);
	}
	
	@Test
	public void testFindIngredientOk() throws NotFoundException {
		final Ingredient ingredient = ingredientService.findById(ID);
		
		Assert.assertNotNull(ingredient);
		Assert.assertEquals(ingredient.getId(), INGREDIENT.getId());
		Assert.assertEquals(ingredient.getName(), INGREDIENT.getName());
	}
	
	@Test(expected = NotFoundException.class)
	public void testFindIngredientError() throws NotFoundException {
		ingredientService.findById("WRONG_ID");
	}
	
	@Test
	public void testFindAllIngredientsOk() throws NotFoundException {
		final List<Ingredient> ingredients = ingredientService.findAll();
		
		Assert.assertNotNull(ingredients.get(0));
		Assert.assertEquals(ingredients.get(0).getId(), LIST_INGREDIENTS.get(0).getId());
		Assert.assertEquals(ingredients.get(0).getName(), LIST_INGREDIENTS.get(0).getName());
	}
	

	@Test(expected = NotFoundException.class)
	public void testFindAllIngredientsError() throws NotFoundException { 
		Mockito.when(ingredientDao.findAll()).thenReturn(null);
		
		final List<Ingredient> ingredients = ingredientService.findAll();
		
		Assert.assertNotNull(ingredients.get(0));		
	}
	
	
	@Test
	public void testCreateIngredientOk() throws InvalidDataException {
		final Ingredient ingredient = ingredientService.create(INGREDIENT);
	
		Assert.assertNotNull(ingredient);
		Assert.assertEquals(ingredient.getId(), INGREDIENT.getId());
		Assert.assertEquals(ingredient.getName(), INGREDIENT.getName());
		
	}
	
	@Test(expected = InvalidDataException.class)
	public void testCreateIngredientError() throws InvalidDataException {
		final Ingredient ingredient = ingredientService.create(null);
		
		Assert.assertNotNull(ingredient);	
	}
	
	@Test
	public void testUpdateIngredientOk() throws InvalidDataException {
		Ingredient ingredient = INGREDIENT;
		INGREDIENT.setName("NEW_NAME");
		ingredientService.update(ID, ingredient);
	}
	
	@Test(expected = InvalidDataException.class)
	public void testUpdateIngredientError() throws InvalidDataException {
		ingredientService.update(ID, new Ingredient());
	}
		
	public void testDeleteIngredient() {
		ingredientService.delete(ID);
	}
	
}
