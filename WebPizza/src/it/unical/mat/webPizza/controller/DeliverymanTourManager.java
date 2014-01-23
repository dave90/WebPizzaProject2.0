package it.unical.mat.webPizza.controller;

import it.unical.mat.webPizza.daoImpl.PizzeriaDAOImpl;
import it.unical.mat.webPizza.domain.OnlineOrder;
import it.unical.mat.webPizza.domain.Order;
import it.unical.mat.webPizza.domain.Pizza;
import it.unical.mat.webPizza.domain.PizzaQuantity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DeliverymanTourManager {
	
	private List<OnlineOrder> orders;
	
	public DeliverymanTourManager(List<OnlineOrder> orders) {
		this.orders = orders;
		if(this.orders.size()>1)
			order();
	}
	
	private void order(){
		URL url;
		//TO-DO start on the real position of the pizzeria
		String start=new PizzeriaDAOImpl().getPizzeriaInformation().getAddressNoSpace();
		try {
			String urlString="http://maps.googleapis.com/maps/api/directions/json?origin="+start+"&destination="+start+"&waypoints=optimize:true";
			for(OnlineOrder o:orders){
				String address=o.getAddress();
				address=address.replaceAll(" ", "+");
				urlString+="|"+address;
			}
			urlString+="&sensor=false";
			
			
			url = new URL(urlString);
			InputStream inputStream = url.openStream();
			BufferedReader inputReader = new BufferedReader(new InputStreamReader(inputStream));
			StringBuffer webPageData = new StringBuffer();
			
			String inputLine = null;
			while ((inputLine = inputReader.readLine()) != null) {
				webPageData.append(inputLine);
				webPageData.append("\n");
			}
			inputReader.close();
			String waypoints=webPageData.substring(webPageData.indexOf("waypoint_order"));
			String wayOrderedString=waypoints.split(":")[1];
			String wayOrder=wayOrderedString.substring(wayOrderedString.indexOf("[")+1, wayOrderedString.indexOf("]"));
			System.out.println(wayOrder);
			ArrayList<OnlineOrder> ordersOrdered=new ArrayList<OnlineOrder>();
			for(String stringId:wayOrder.split(",")){
				int i=Integer.parseInt(stringId.trim());
				ordersOrdered.add(orders.get(i));
			}
			if(ordersOrdered.size()==orders.size())
				orders=new ArrayList<OnlineOrder>(ordersOrdered);
		} catch (Exception e) {
			System.err.println("Error ordering list parsering google");
		}

	}

	public List<OnlineOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<OnlineOrder> orders) {
		this.orders = orders;
	}
	
	public void setStatusOrder(Long id,String status){
		for(OnlineOrder o:orders)
			if(o.getId()==id){
				o.setDeliveryStatus(status);
				return;
			}
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
		response+="<tr>"+"<th>Total</th><th>&euro; "+order.getPrice()+"</th>"+"</tr>";
		return response;
	}
	
	
	
	

}
