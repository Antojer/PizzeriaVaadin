package com.example.pizzeria;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.pizzeria.dao.IngredientDAO;
import com.example.pizzeria.dao.PizzaDAO;
import com.example.pizzeria.model.Ingredient;
import com.example.pizzeria.model.Pizza;
import com.example.pizzeria.service.ingredient.IngredientService;
import com.vaadin.annotations.Theme;
import com.vaadin.data.Binder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.dd.HorizontalDropLocation;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringUI
@Theme("valo")
public class VaadinUI extends UI{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	IngredientDAO ingredientDao;
	
	@Autowired
	PizzaDAO pizzaDao;

	Grid<Ingredient> gridIngredient = new Grid<>();
	
	Grid<Pizza> gridPizza = new Grid<>();
	
	@Autowired
	IngredientService ingredientService;
	
	private Button refresh = new Button("Actualizar lista", this::refresh);
	
	
	
	TextField name = new TextField("Ingredient name");
	

	Button save = new Button("Add ingredient", event -> addIngredient());

	private TextField nameField = new TextField("Pizza name");
	private TextField idFieldPizza = new TextField("Pizza id");
	private TextField idFieldIngredientForPizza = new TextField("Ingredient id");


	private VerticalLayout pizzaContent = new VerticalLayout();
	
	@Override
	protected void init(VaadinRequest request) {
	
		VerticalLayout ingredientContent = new VerticalLayout();		
		ingredientContent.addComponent(gridIngredient);
		ingredientContent.addComponent(new Label("Añadir ingrediente"));
		ingredientContent.addComponent(name);
		ingredientContent.addComponent(save);	

		setPizzaLayout();

		HorizontalLayout webContent = new HorizontalLayout();
		webContent.addComponent(new Label("Bienvenido a Pizzería Borrego!"));
		webContent.addComponent(refresh);
		webContent.addComponent(ingredientContent);
		webContent.addComponent(pizzaContent);
	
		
		setContent(webContent);
		
		gridIngredient.addColumn(ingredient -> ingredient.getId()).setCaption("Ingredient ID");
		gridIngredient.addColumn(ingredient -> ingredient.getName()).setCaption("Name");
		
		refresh.setIcon(VaadinIcons.REFRESH);
		refresh.addStyleName(ValoTheme.BUTTON_PRIMARY);
		
		gridPizza.addColumn(ingredient -> ingredient.getId()).setCaption("Pizza ID");
		gridPizza.addColumn(ingredient -> ingredient.getName()).setCaption("Name");
		gridPizza.addColumn(ingredient -> ingredient.ingredientsToString()).setCaption("Ingredients").setExpandRatio(1);
		
		refresh.setIcon(VaadinIcons.REFRESH);
		refresh.addStyleName(ValoTheme.BUTTON_PRIMARY);
			
		listIngredients();
		listPizzas();
		
	}
	
	private void setPizzaLayout() {
		pizzaContent.addComponent(gridPizza);
		pizzaContent.addComponent(new Label("Añadir pizza"));
		pizzaContent.addComponent(nameField);
		Button savePizza = new Button("Guardar", event ->  savePizza());
		pizzaContent.addComponent(savePizza);
		pizzaContent.addComponent(new Label("Añadir ingrediente a pizza"));
		pizzaContent.addComponent(idFieldPizza);
		pizzaContent.addComponent(idFieldIngredientForPizza);
		Button addIngredientToPizza = new Button("Añadir Ingrediente", event ->  addIngredientToPizza());
		pizzaContent.addComponent(addIngredientToPizza);
	}
	
	public void savePizza() {
		Pizza pizza = new Pizza();
		pizza.setName(nameField.getValue());
		pizzaDao.save(pizza);
	}
	
	public void addIngredientToPizza() {
		Pizza pizza = pizzaDao.findOne(idFieldPizza.getValue());
		Ingredient ingredient = ingredientDao.findOne(idFieldIngredientForPizza.getValue());
		pizza.getIngredients().add(ingredient);
		pizzaDao.save(pizza);
	}
	
	public void refresh(ClickEvent clickEvent) {
		listIngredients();
		listPizzas();
	}
	
	private void listIngredients() {
		gridIngredient.setItems(ingredientDao.findAll());
	}
	
	private void listPizzas() {
		gridPizza.setItems(pizzaDao.findAll());
	}
	
	private void addIngredient(){
		Ingredient ingredient = new Ingredient();
		ingredient.setName(name.getValue());
		ingredientDao.save(ingredient);
	}

}
