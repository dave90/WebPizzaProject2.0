package it.unical.mat.webPizza.dao;

import it.unical.mat.webPizza.domain.Client;
import it.unical.mat.webPizza.domain.Deliveryman;
import it.unical.mat.webPizza.domain.OnlineOrder;
import it.unical.mat.webPizza.domain.Pizza;
import it.unical.mat.webPizza.domain.PizzaQuantity;

import java.util.List;
import java.util.Set;


public interface OnlineOrderDAO  {
	
	public Long insertOrder(String date,String status,List<PizzaQuantity> pizzas,boolean paid,Client client,String deliveryStatus,String address);
	public int updateDelivery(Long id, Deliveryman deliveryman);
	public int updateDeliveryStatus(Long id,String deliveryStatus);
	public String getOrderDeliveryStatus(Long id);
	public List<OnlineOrder> getNotDeliveryManAssignedOrder();
	public List<OnlineOrder> getdeliveryManOrder(Long id);
	public String getDeliveryManPositionByOrder(Long idOrder);
	
}
