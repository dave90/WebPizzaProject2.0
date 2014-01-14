package it.unical.mat.webPizza.dao;

import it.unical.mat.webPizza.domain.Client;
import it.unical.mat.webPizza.domain.Order;
import it.unical.mat.webPizza.domain.Pizza;
import it.unical.mat.webPizza.domain.PizzaChef;
import it.unical.mat.webPizza.domain.PizzaQuantity;

import java.util.List;
import java.util.Set;

public interface OrderDAO {

	public Long insertOrder(String date,String status,List<PizzaQuantity> pizzas,boolean paid,Client client,PizzaChef chef);
	public Long insertOrder(String date,String status,List<PizzaQuantity> pizzas,boolean paid,Client client);
	public int updateStatus(Long id,String status);
	public int updateChef(Long id,PizzaChef chef);
	public String getOrderStatus(Long id);
	public List<Order> getNotChefAssignedOrder();
	public List<Order> getAllOrderOfCLient(Long idCLient);
	public Order getOrder(Long id);
	
}
