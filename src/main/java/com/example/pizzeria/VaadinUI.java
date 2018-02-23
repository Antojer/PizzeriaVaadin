package com.example.pizzeria;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.pizzeria.dao.IngredientDAO;
import com.example.pizzeria.model.Ingredient;
import com.example.pizzeria.service.ingredient.IngredientService;
import com.vaadin.annotations.Theme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

@SpringUI
@Theme("valo")
public class VaadinUI extends UI {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	IngredientDAO ingredientDao;

	Grid<Ingredient> gridIngredient = new Grid<>();
	
	@Autowired
	IngredientService ingredientService;
	
	private Button refresh = new Button("AQUI", this::refresh);
	
	private Button addNew = new Button("", this::add);
	

	
	

	@Override
	protected void init(VaadinRequest request) {
		setContent(new Label("Bienvenido a Pizzería Borrego!"));		
		setContent(gridIngredient);
		
		gridIngredient.addColumn(ingredient -> ingredient.getId()).setCaption("Ingredient ID");
		gridIngredient.addColumn(ingredient -> ingredient.getName()).setCaption("Name");
	
		gridIngredient.setWidth("90%");
		
		refresh.setIcon(VaadinIcons.REFRESH);
		refresh.addStyleName(ValoTheme.BUTTON_PRIMARY);
		
		listIngredients();
		
	}
	
	public void refresh(ClickEvent clickEvent) {
		listIngredients();
}
	
	private void listIngredients() {
		gridIngredient.setItems(ingredientDao.findAll());
	}
	
	private void add(ClickEvent clickEvent){
		
	}


}
