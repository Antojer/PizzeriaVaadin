package com.example.pizzeria.service.ingredient;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pizzeria.dao.IngredientDAO;
import com.example.pizzeria.model.Ingredient;

import Exception.InvalidDataException;
import Exception.NotFoundException;

@Service
public class IngredientServiceImpl implements IngredientService {
	
	@Autowired
	private IngredientDAO ingredientDAO;

	@Override
	public Ingredient findById(String id) throws NotFoundException {
		final Ingredient ingredient = ingredientDAO.findOne(id);
		return Optional.ofNullable(ingredient).orElseThrow(NotFoundException::new);
	}

	@Override
	public List<Ingredient> findAll() throws NotFoundException {
		final List<Ingredient> ingredients = ingredientDAO.findAll();
		return Optional.ofNullable(ingredients).orElseThrow(NotFoundException::new);
	}

	@Override
	public Ingredient create(Ingredient ingredient) throws InvalidDataException {
		if (validate(ingredient))
			return ingredientDAO.save(ingredient);
		throw new InvalidDataException("Error, faltan datos");
	}

	private boolean validate(Ingredient ingredient) {
		return (ingredient != null && ingredient.getName() != null && ingredient.getName() != "");
	}
	
	@Override
	public void update(String id, Ingredient ingredient) {
		final Ingredient newIngredient = ingredient;
		newIngredient.setId(id);
		ingredientDAO.save(ingredient);
	}

	@Override
	public void delete(String idIngredient) {
		ingredientDAO.delete(idIngredient);
	}

}
