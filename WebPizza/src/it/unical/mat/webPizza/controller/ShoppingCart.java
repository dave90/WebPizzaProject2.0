package it.unical.mat.webPizza.controller;

import it.unical.mat.webPizza.domain.Client;
import it.unical.mat.webPizza.domain.Pizza;
import it.unical.mat.webPizza.util.HibernateUtil;
import it.unical.mat.webPizza.domain.PizzaIngredients;
import it.unical.mat.webPizza.service.OrderManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart implements Serializable{
	Map<Pizza, Integer> pizzaQuantity= new HashMap<Pizza, Integer>();
	private int totalprice=0;
	
	public Map<Pizza, Integer> getPizzaQuantity() {
		return pizzaQuantity;
	}

	public void setPizzaQuantity(Map<Pizza, Integer> pizzaQuantity) {
		this.pizzaQuantity = pizzaQuantity;
	}

	void deletePizza(String name){
		for(Pizza p:pizzaQuantity.keySet()){
			if(p.getName().equals(name)){
				pizzaQuantity.remove(p);
				updateTotalprice();
				return;
			}
		}
	}
	
	void insertPizza(Long idPizza,Pizza pizzaDB,int quantity){
		Pizza pizza=null;
		for(Pizza p:pizzaQuantity.keySet()){
			if(p.getId()==idPizza){
				int qty=pizzaQuantity.get(p);
				pizzaQuantity.remove(p);
				pizzaQuantity.put(p, qty+=quantity);
				pizza=p;
				updateTotalprice();
				return;
			}
		}
		if(pizza==null)
			pizza=pizzaDB;
		
		pizzaQuantity.put(pizza,quantity);
		updateTotalprice();
	}
	void insertPizzaBuild(String namePizza,int quantity, String ingridients, OrderManager orderManager, Client client){
		ArrayList<PizzaIngredients> ingredientsPizza=new ArrayList<PizzaIngredients>();
		Pizza pizza=new Pizza();
		pizza.setName(namePizza);
		pizza.setDiscount(0);
		String[] parts = ingridients.split(",");
		
		for (int i = 0; i < parts.length; i++) {
			ingredientsPizza.add(orderManager.getIngredient(Long.parseLong(parts[i])));			
		}
		pizza.setIngredients(ingredientsPizza);
		for(Pizza p:pizzaQuantity.keySet()){
			if(p.getName().equals(namePizza)){
				int qty=pizzaQuantity.get(p);
				pizzaQuantity.remove(p);
				pizzaQuantity.put(p, qty+=quantity);
				pizza=p;
				updateTotalprice();
				return;
			}
		}
		Long id = orderManager.insertPizza(pizza.getName(), pizza.getIngredients(), pizza.getDiscount(),client);
		pizza.setId(id);
		pizzaQuantity.put(pizza,quantity);
		updateTotalprice();
	}

	double updateTotalprice(){
		totalprice=0;
		for(Pizza p:pizzaQuantity.keySet()){
			totalprice+=p.getPrize()*pizzaQuantity.get(p);
		}
		return totalprice;
	}
	
	String getTableBody(){
		String tableToAppend="";
		int count =0;
		for(Pizza p:pizzaQuantity.keySet()){
			//tableToAppend+="<tr>"+"<td>"+p.getName()+"</td>"+"<td>"+pizzaQuantity.get(p)+"</td>"+"<td>&euro;"+p.getPrize()*pizzaQuantity.get(p)+"</td>"+"</tr>";
			tableToAppend+="<tr id='trCart"+ count+"'>"+"<td id='nameCart"+ count+"'>"+p.getName()+"</td>"+"<td id='quantityCart"+ count+"'>"+pizzaQuantity.get(p)+"</td>"+"<td id='priceCart"+ count+"' >"+p.getPrize()*pizzaQuantity.get(p)+" &euro; </td>"+"<td><img id='deleteCart-"+p.getId()+"' src='resource/img/x.png' width='20' height='20' class='removeItem'></td>"+"</tr>";

			count++;
		}
//		tableToAppend+="<tr>"+"<th></th><th>Total</th>"+"<th>&euro;"+totalprice+"</th></tr>";
		tableToAppend+="<tr>"+"<th></th><th>Total</th>"+"<th id='totalPriceCart'>"+totalprice+"&euro;</th></tr>";
		
		// aggiungo questa parte per modificare in modo dinamico con jquery anche nell'header il carrello
		tableToAppend+="*<i class='icon-shopping-cart'></i> Items - "+totalprice+"&euro;";
		return tableToAppend;
	}

	public int getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}
	
	public List<Pizza> getPizzaList(){
		ArrayList<Pizza> pizza=new ArrayList<Pizza>();
		for(Pizza p:pizzaQuantity.keySet())
			for(int i=0;i<pizzaQuantity.get(p);i++)
				pizza.add(p);
		
		return pizza;
	}
	
	public int getQuantity(Long id){
		for(Pizza p:pizzaQuantity.keySet())
			if(p.getId()==id)
				return pizzaQuantity.get(p);
		
		return 0;
	}
	
	public double updateQuantity(Long id,int quantity){
		for(Pizza p:pizzaQuantity.keySet())
			if(p.getId()==id){
				 pizzaQuantity.put(p,quantity);
				 updateTotalprice();
				 return p.getPrize()*quantity;
			}
		
		return 0;
	}
	
	public void removePizza(Long id){
		Pizza pizzaRemove=null;
		for(Pizza p:pizzaQuantity.keySet())
			if(p.getId()==id){
				 pizzaRemove=p;
			}
		
		if(pizzaRemove!=null)
			pizzaQuantity.remove(pizzaRemove);
		
		updateTotalprice();
			
	}

}
