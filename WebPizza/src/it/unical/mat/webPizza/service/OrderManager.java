package it.unical.mat.webPizza.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unical.mat.webPizza.dao.OnlineOrderDAO;
import it.unical.mat.webPizza.dao.OrderDAO;
import it.unical.mat.webPizza.dao.PizzaDAO;
import it.unical.mat.webPizza.dao.PizzaIngredientsDAO;
import it.unical.mat.webPizza.daoImpl.OnlineOrderDAOImpl;
import it.unical.mat.webPizza.daoImpl.OrderDAOImpl;
import it.unical.mat.webPizza.daoImpl.PizzaDAOImpl;
import it.unical.mat.webPizza.daoImpl.PizzaIngredientsDAOImpl;
import it.unical.mat.webPizza.domain.Client;
import it.unical.mat.webPizza.domain.Deliveryman;
import it.unical.mat.webPizza.domain.OnlineOrder;
import it.unical.mat.webPizza.domain.Order;
import it.unical.mat.webPizza.domain.Pizza;
import it.unical.mat.webPizza.domain.PizzaChef;
import it.unical.mat.webPizza.domain.PizzaIngredients;
import it.unical.mat.webPizza.domain.PizzaQuantity;
import it.unical.mat.webPizza.util.HibernateUtil;

@Service
public class OrderManager {
	
	@Autowired
	private OnlineOrderDAO onlineOrderDAO;
	@Autowired
	private OrderDAO orderDAO;
	@Autowired
	private PizzaDAO pizzaDAO;
	@Autowired
	private PizzaIngredientsDAO ingredientsDAO;
	
	public OrderManager() {
		onlineOrderDAO=new OnlineOrderDAOImpl();
		orderDAO=new OrderDAOImpl();
		pizzaDAO=new PizzaDAOImpl();
		ingredientsDAO=new PizzaIngredientsDAOImpl();
	}
	
	public List<PizzaIngredients> getAllIngredients(){
		return ingredientsDAO.getAllIngredients();
	}
	public PizzaIngredients getIngredient(Long name){
		return ingredientsDAO.getIngredient(name);
	}
	
	public boolean addIngredients(String name,double cost){
		if(ingredientsDAO.insertIngredient(name, cost)>0)
			return true;
		return false;
	}
	
	public boolean removeIngredients(Long id){
		if(ingredientsDAO.deleteIngredient(id)>0)
			return true;
		return false;
	}
	
	public List<Pizza> getAllPizza(){
		List<Pizza> menuPizza=new ArrayList<Pizza>();
		List<Pizza> listPizza = pizzaDAO.getAllPizzas();

		for(Pizza p:listPizza){
			if(p.getClient()==null)
				menuPizza.add(p);
		}
		
		return menuPizza;
	}
	
	public List<Pizza> getAllClientPizza(){
		List<Pizza> menuPizza=new ArrayList<Pizza>();
		List<Pizza> listPizza = pizzaDAO.getAllPizzas();

		for(Pizza p:listPizza){
			if(p.getClient()!=null)
				menuPizza.add(p);
		}
		
		return menuPizza;
	}
	
	public boolean insertPizza(String name,List<PizzaIngredients> ingredients,double discount){
		if(pizzaDAO.insertPizza(name, ingredients, discount,null)>0)
			return true;
		return false;
	}

	public Long insertPizza(String name,List<PizzaIngredients> ingredients,double discount,Client client){
		return pizzaDAO.insertPizza(name, ingredients, discount,client);
	}
	
	public boolean deletePizza(Long id){
		if(pizzaDAO.deletePizza(id)>0)
			return true;
		return false;
	}
	
	public Long insertOrder(String date,List<PizzaQuantity> pizzas,boolean paid,Client client){
		return orderDAO.insertOrder(date,Order.S_NOT_ASSIGNED, pizzas, paid, client);
	}
	
	public Long insertOrder(String date,List<PizzaQuantity> pizzas,boolean paid,Client client,String address){
		return onlineOrderDAO.insertOrder(date,Order.S_NOT_ASSIGNED, pizzas, paid, client, OnlineOrder.D_NOT_DELIVERY, address);
	}
	
	public boolean assignChefToOrder(PizzaChef chef,Long idOrder){
		if(orderDAO.updateChef(idOrder, chef)>0)
			return true;
		return false;
	}
	
	public boolean assignDeliveryManToOrder(Deliveryman delivery,Long idOrder){
		if(onlineOrderDAO.updateDelivery(idOrder, delivery)>0)
			return true;
		return false;
	}
	
	public boolean updateOrderStatus(Long id,String status){
		if(orderDAO.updateStatus(id, status)>0)
			return true;
		return false;
	}
	
	public boolean updateDeliveryStatus(Long id,String deliveryStatus){
		if(onlineOrderDAO.updateDeliveryStatus(id, deliveryStatus)>0)
			return true;
		return false;
	}
	
	public List<Order> getAllNotAssignedChefOrder(){
		return orderDAO.getNotChefAssignedOrder();
	}
	
	public String getDeliveryStatusOrder(Long idOrder){
		return onlineOrderDAO.getOrderDeliveryStatus(idOrder);
	}
	
	public String getDeliveryManPositionOrder(Long idOrder){
		return onlineOrderDAO.getDeliveryManPositionByOrder(idOrder);
	}
	
	public String getOrderStatus(Long idOrder){
		return orderDAO.getOrderStatus(idOrder);
	}
	
	public Pizza getPizza(Long id){
		return pizzaDAO.getPizza(id);
	}
	
	
	public boolean updatePizzaIngredients(Long id,String name,double cost){
		return ingredientsDAO.updateIngredients(id, name, cost);
	}
	
	public boolean deletePizzaIngredients(Long id){
		return ingredientsDAO.deleteIngredient(id)>0;
	}
	
	public List<Order> getAllOrderFromCLient(Client client){
		return orderDAO.getAllOrderOfCLient(client.getId());
	}
	
	public OnlineOrder getOnlineOrder(Long id){
		OnlineOrder order=onlineOrderDAO.getOnlineOrder(id);
		return order;
	}
	
	public Order getOrder(Long id){
		Order order=orderDAO.getOrder(id);
		return order;
	}
	
	public List<OnlineOrder> getDeliverymanOrder(Long id){
		return  onlineOrderDAO.getdeliveryManOrder(id);
	}
	public List<Order> getPizzaChefOrder(Long id){
		return  orderDAO.getPizzaChefOrder(id);
	}

}
