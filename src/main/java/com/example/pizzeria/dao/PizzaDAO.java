package com.example.pizzeria.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.pizzeria.model.Pizza;

@Repository
public interface PizzaDAO extends MongoRepository<Pizza, String>{

}
