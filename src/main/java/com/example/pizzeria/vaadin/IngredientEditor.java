package com.example.pizzeria.vaadin;

import com.example.pizzeria.dao.IngredientDAO;
import com.example.pizzeria.model.Ingredient;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SpringComponent
@UIScope
public class IngredientEditor extends VerticalLayout{
	
	private static final long serialVersionUID = 1L;

	private IngredientDAO ingredientDAO;
	private Ingredient ingredient;
	
	//TextField name = new TextField("Ingredient name";)
	
}
