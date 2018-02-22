package com.example.pizzeria.model;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document
@Getter
@Setter
public class Ingredient implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String name;
		
	public void setId(String id)
	{
		this.id = id;
	}
}
