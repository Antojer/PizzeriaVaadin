package com.example.pizzeria.mongo;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.pizzeria.dao.IngredientDAO;
import com.example.pizzeria.model.Ingredient;

import com.mongodb.DBCollection;

@RunWith(SpringRunner.class)
@DataMongoTest
public class TestMongoDBIntegration {

	String collectionName;
	Ingredient ingredient;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private IngredientDAO ingredientDAO;
	
	@Before
	public void before() {
		collectionName = "pizzeria";
		ingredient = new Ingredient();
		ingredient.setName("Chorizo");
		
	}
	
	@After
	public void after() {
		mongoTemplate.dropCollection(collectionName);
	}
	
	@Test
	public void checkMongoTemplate() {
		assertNotNull(mongoTemplate);
		DBCollection createdCollection = mongoTemplate.createCollection(collectionName);
		assertTrue(mongoTemplate.collectionExists(collectionName));
	}
	
	@Test
	public void checkDocument() {
		mongoTemplate.save(ingredient, collectionName);
		Query query = new Query(new Criteria().andOperator(Criteria.where("name").regex(ingredient.getName())));
		Ingredient retrievedIngredient = mongoTemplate.findOne(query, Ingredient.class, collectionName);
		assertNotNull(retrievedIngredient);
	}	
	
	
	
}
