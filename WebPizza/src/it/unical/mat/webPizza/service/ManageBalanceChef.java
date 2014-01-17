package it.unical.mat.webPizza.service;

import it.unical.mat.webPizza.dao.PizzaChefDAO;

import org.apache.commons.collections.map.HashedMap;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.transform.ToListResultTransformer;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import it.unical.mat.webPizza.daoImpl.PizzaChefDAOImpl;
import it.unical.mat.webPizza.domain.Order;
import it.unical.mat.webPizza.domain.Pizza;
import it.unical.mat.webPizza.domain.PizzaChef;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Aspect
public class ManageBalanceChef {

	ArrayList<PizzaChef> chefs = new ArrayList<PizzaChef>();

	@AfterReturning(pointcut = "execution(* it.unical.mat.webPizza.daoImpl.PizzaChefDAOImpl.getPizzaChef(String,String))", returning = "chef")
	public void logInChef(Object chef) {
		if (chef != null) {
			if (!present((PizzaChef) chef))
				chefs.add((PizzaChef) chef);
			manage();
//			System.out.println("insert is running!");
//			System.out.println("size " + chefs.size());
//			System.out.println("******");
		}
	}

	private boolean present(PizzaChef chef) {
		return chefs.contains(chef);
	}

	@After("execution(* it.unical.mat.webPizza.controller.ChefController.chefLogout(..))")
	public void logoutChef(JoinPoint chef) {
		if (chef != null) {
			for (int i = 0; i < chefs.size(); i++) {
				if (chefs.get(i).getId() == chef.getArgs()[0]) {
					chefs.remove(chefs.get(i));
				}
			}
//			System.out.println("chef.getArgs()[0]	 " + chef.getArgs()[0]);
//			System.out.println("size " + chefs.size());
//			System.out.println("******");
		}
	}

	@After("execution(* it.unical.mat.webPizza.controller.AccountController.checkOut(..))") 
	public void manage() {
		OrderManager orderManager=new OrderManager();
		List<Order> orderList = orderManager.getAllNotAssignedChefOrder();
//		System.out.println("orderList.size() "+orderList.size());
		// creo hashMap del tipo /id Chef /numero totale ordini/
		for (Order order : orderList) {
			Map<Long, Integer> totalOrderChefs = new HashMap<Long, Integer>();

			for (int i = 0; i < chefs.size(); i++) {
				totalOrderChefs.put(chefs.get(i).getId(), orderManager
						.getPizzaChefOrder(chefs.get(i).getId()).size());
			}
			if(chefs.size() >0){
				Long idChefMin = chefs.get(0).getId();
				// trovo lo chef con minore numero di ordini
				for (Long idChef : totalOrderChefs.keySet()) {
					if (totalOrderChefs.get(idChef) < totalOrderChefs
							.get(idChefMin)) {
						idChefMin = idChef;
					}
				}
				orderManager.updateOrderStatus(order.getId(), Order.S_ASSIGNED);
				PizzaChefDAO dao = new PizzaChefDAOImpl();
				orderManager.assignChefToOrder(dao.getPizzaChef(idChefMin),
						order.getId());
			}
		}
	}

}
