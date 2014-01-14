package it.unical.mat.webPizza.dao;

import it.unical.mat.webPizza.domain.Client;
import it.unical.mat.webPizza.domain.Pizza;
import it.unical.mat.webPizza.domain.PizzaIngredients;

import java.util.List;
import java.util.Set;

public interface PizzaDAO {
	
	Long insertPizza(String name, List<PizzaIngredients> ingredients, double discount,Client client);
	int deletePizza(Long id);
	List<Pizza> getAllPizzas();
	Pizza getPizza(Long id);
	Pizza getLazyPizza(Long id);
}
