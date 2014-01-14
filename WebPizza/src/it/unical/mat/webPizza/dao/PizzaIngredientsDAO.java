package it.unical.mat.webPizza.dao;

import it.unical.mat.webPizza.domain.PizzaIngredients;

import java.util.List;

public interface PizzaIngredientsDAO {
	Long insertIngredient(String name,double cost);
	int deleteIngredient(Long id);
	List<PizzaIngredients> getAllIngredients();
	PizzaIngredients getIngredient(Long name);
	boolean updateIngredients(Long id,String name,double cost);
}
