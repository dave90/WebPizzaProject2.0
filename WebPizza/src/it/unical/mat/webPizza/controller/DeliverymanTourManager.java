package it.unical.mat.webPizza.controller;

import it.unical.mat.webPizza.domain.OnlineOrder;
import it.unical.mat.webPizza.domain.Order;
import it.unical.mat.webPizza.domain.Pizza;
import it.unical.mat.webPizza.domain.PizzaQuantity;

import java.util.ArrayList;
import java.util.List;

public class DeliverymanTourManager {
	
	private List<OnlineOrder> orders;
	
	public DeliverymanTourManager(List<OnlineOrder> orders) {
		this.orders = orders;
		order();
	}
	
	private void order(){
		//TO-DO
	}

	public List<OnlineOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<OnlineOrder> orders) {
		this.orders = orders;
	}

	public List<Long> getIds() {
		List<Long> ids=new ArrayList<Long>();
		for(Order o:orders)
			ids.add(o.getId());
		return ids;
	}
	
	public String getOrderResponse(Long id){
		OnlineOrder order=null;
		for(OnlineOrder o:orders)
			if(o.getId()==id){
				order=o;
				break;
			}
		
		if(order==null)
			return "";
		
		String response=order.getAddress()+"*";
		response+="<tr>"+"<td>"+order.getDate()+"</td>"+"<td id='delStatus'>"+order.getDeliveryStatus()+"</td>"+"<td>"+order.getAddress()+"</td>"+"<td>"+order.isPaid()+"</td>"+"</tr>"+"*";
		for(PizzaQuantity p:order.getPizzas()){
			response+="<tr>"+"<td>"+p.getPizza().getName()+"</td>"+"<td>"+p.getQuantity()+"</td>"+"</tr>";
		}
		response+="<tr>"+"<th>Total</th><th>&euro; "+order.getPrize()+"</th>"+"</tr>";
		return response;
	}
	
	
	
	

}
