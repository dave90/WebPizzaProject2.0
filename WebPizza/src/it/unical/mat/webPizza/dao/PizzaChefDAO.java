package it.unical.mat.webPizza.dao;

import it.unical.mat.webPizza.domain.Client;
import it.unical.mat.webPizza.domain.PizzaChef;

public interface PizzaChefDAO {
	
	public Long insertPizzaChef(String name,String surname,String user, String hpwd);
	public PizzaChef getPizzaChef(Long id);
	public PizzaChef getPizzaChef(String user, String hpwd);
}
