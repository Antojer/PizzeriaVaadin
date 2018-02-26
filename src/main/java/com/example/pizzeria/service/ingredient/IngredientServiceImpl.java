package com.example.pizzeria.service.ingredient;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pizzeria.dao.IngredientDAO;
import com.example.pizzeria.model.Ingredient;

import Exception.NotFoundException;

@Service
public class IngredientServiceImpl implements IngredientService {
	
	@Autowired
	private IngredientDAO ingredientDAO;

	@Override
	public Ingredient findById(String id) throws NotFoundException {
		final Ingredient ingredient = Optional.ofNullable(ingredientDAO.findOne(id))
										  .orElseThrow(NotFoundException::new);
		return ingredient;
	}

	@Override
	public List<Ingredient> findAll() {
		return ingredientDAO.findAll();
	}

	@Override
	public Ingredient create(Ingredient ingredient) {
		return ingredientDAO.save(ingredient);
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
