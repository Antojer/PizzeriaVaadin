package com.example.pizzeria.service.ingredient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pizzeria.dao.IngredientDAO;
import com.example.pizzeria.exception.NotFoundExcept;
import com.example.pizzeria.model.Ingredient;

@Service
public class IngredientServiceImpl implements IngredientService {
	
	@Autowired
	private IngredientDAO ingredientDAO;

	@Override
	public Ingredient findById(String id) throws NotFoundExcept {
		final Ingredient ingredient = Optional.ofNullable(ingredientDAO.findOne(id))
										  .orElseThrow(() -> new NotFoundExcept("Ingrediente con id "+id+" no encontrado"));
		return ingredient;
	}

	@Override
	public List<Ingredient> findAll(Integer page, Integer size) {
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
