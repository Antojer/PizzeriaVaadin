package com.example.pizzeria;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.pizzeria.dao.IngredientDAO;
import com.example.pizzeria.dao.PizzaDAO;
import com.example.pizzeria.model.Ingredient;
import com.example.pizzeria.model.Pizza;
import com.example.pizzeria.service.ingredient.IngredientService;
import com.vaadin.annotations.Theme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
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
	
	private Button refresh = new Button("Actualizar las Listas.", this::refresh);
		
	TextField name = new TextField("Ingredient name");
	
	Button save = new Button("Añadir ingredient", event -> addIngredient(event));
	
	private TextField nameField = new TextField("Pizza name");
	private TextField idFieldPizza = new TextField("Pizza id");
	private TextField idFieldIngredientForPizza = new TextField("Ingredient id");
	private TextField idPizzaDelete = new TextField("Pizza id");
	private TextField idIngredientDelete = new TextField("Ingredient id");
	
	private Label title = new Label("Bienvenido a Pizzería Borrego!");
	
	@Override
	protected void init(VaadinRequest request) {
		
		/*
		 * LAYOUTS
		 */
		VerticalLayout ingredientAddition = new VerticalLayout();
		ingredientAddition.addComponent(new Label("Añadir ingrediente"));
		save.setIcon(VaadinIcons.CHECK_CIRCLE);
		ingredientAddition.addComponent(name);
		ingredientAddition.addComponent(save);
		
		VerticalLayout ingredientElimination = new VerticalLayout();
		ingredientElimination.addComponent(new Label("Eliminar ingrediente"));
		ingredientElimination.addComponent(idIngredientDelete);
		Button deleteIngredient = new Button("Eliminar", event ->  deleteIngredient(event));
		deleteIngredient.setIcon(VaadinIcons.CLOSE_CIRCLE);
		ingredientElimination.addComponent(deleteIngredient);
		
		HorizontalLayout ingredientModifications = new HorizontalLayout();
		ingredientModifications.addComponent(ingredientAddition);
		ingredientModifications.addComponent(ingredientElimination);
		
		VerticalLayout ingredientContent = new VerticalLayout();
		ingredientContent.addComponent(gridIngredient);
		ingredientContent.addComponent(ingredientModifications);
		
		
		
		setIngredientLayout();

		VerticalLayout pizzaAddition = new VerticalLayout();
		pizzaAddition.addComponent(new Label("Añadir pizza"));
		pizzaAddition.addComponent(nameField);
		Button savePizza = new Button("Guardar", event ->  savePizza(event));
		savePizza.setIcon(VaadinIcons.CHECK_CIRCLE);
		pizzaAddition.addComponent(savePizza);
		
		VerticalLayout pizzaElimination = new VerticalLayout();
		pizzaElimination.addComponent(new Label("Eliminar pizza"));
		pizzaElimination.addComponent(idPizzaDelete);
		Button deletePizza = new Button("Eliminar", event ->  deletePizza(event));
		deletePizza.setIcon(VaadinIcons.CLOSE_CIRCLE);
		pizzaElimination.addComponent(deletePizza);
		
		HorizontalLayout pizzaModification = new HorizontalLayout();
		pizzaModification.addComponent(pizzaAddition);
		pizzaModification.addComponent(pizzaElimination);
		
		HorizontalLayout pizzaIngredientAddition = new HorizontalLayout();
		pizzaIngredientAddition.addComponent(idFieldPizza);
		pizzaIngredientAddition.addComponent(idFieldIngredientForPizza);
		
		VerticalLayout pizzaIngredients = new VerticalLayout();
		pizzaIngredients.addComponent(new Label("Añadir ingrediente a pizza"));
		pizzaIngredients.addComponent(pizzaIngredientAddition);
		Button addIngredientToPizza = new Button("Añadir Ingrediente", event ->  addIngredientToPizza(event));
		addIngredientToPizza.setIcon(VaadinIcons.CHECK_CIRCLE);
		pizzaIngredients.addComponent(addIngredientToPizza);
		
		VerticalLayout pizzaContent = new VerticalLayout();
		pizzaContent.addComponent(gridPizza);
		pizzaContent.addComponent(pizzaModification);
		pizzaContent.addComponent(pizzaIngredients);
		setPizzaLayout();
		
		HorizontalLayout dataContent = new HorizontalLayout();
		dataContent.addComponent(pizzaContent);
		dataContent.addComponent(ingredientContent);
		
		
		VerticalLayout titleContent = new VerticalLayout();
		titleContent.addComponent(title);
		
		VerticalLayout refreshContent = new VerticalLayout();
		HorizontalLayout refreshHorizontalContent = new HorizontalLayout();
		refreshContent.addComponent(refreshHorizontalContent);
		titleContent.addComponent(refresh);
		
		HorizontalLayout headerContent = new HorizontalLayout();
		headerContent.addComponent(titleContent);
		headerContent.addComponent(refreshContent);
			
		VerticalLayout webContent = new VerticalLayout();
		webContent.addComponent(headerContent);
		webContent.addComponent(dataContent);
		
		refreshContent.setComponentAlignment(refreshHorizontalContent, Alignment.TOP_CENTER);
		webContent.setComponentAlignment(headerContent, Alignment.TOP_CENTER);
		webContent.setComponentAlignment(dataContent, Alignment.TOP_CENTER);
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
	
	

	private void setIngredientLayout() {
		
		
		
		
	}

	private void setPizzaLayout() {
		
	}
	
	public void savePizza(ClickEvent clickEvent) {
		Pizza pizza = new Pizza();
		pizza.setName(nameField.getValue());
		pizzaDao.save(pizza);
		this.refresh(clickEvent);
	}
	
	public void addIngredientToPizza(ClickEvent clickEvent) {
		Pizza pizza = pizzaDao.findOne(idFieldPizza.getValue());
		Ingredient ingredient = ingredientDao.findOne(idFieldIngredientForPizza.getValue());
		pizza.getIngredients().add(ingredient);
		pizzaDao.save(pizza);
		this.refresh(clickEvent);
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
	
	private void addIngredient(ClickEvent clickEvent){
		Ingredient ingredient = new Ingredient();
		ingredient.setName(name.getValue());
		ingredientDao.save(ingredient);
		this.refresh(clickEvent);
	}
	
	private void deleteIngredient(ClickEvent clickEvent)
	{
		ingredientDao.delete(idIngredientDelete.getValue());
		this.refresh(clickEvent);
	}

	private void deletePizza(ClickEvent clickEvent) 
	{
		pizzaDao.delete(idPizzaDelete.getValue());
		this.refresh(clickEvent);
	}
	
}
