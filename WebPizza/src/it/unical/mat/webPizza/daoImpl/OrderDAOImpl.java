package it.unical.mat.webPizza.daoImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unical.mat.webPizza.dao.OrderDAO;
import it.unical.mat.webPizza.domain.Administrator;
import it.unical.mat.webPizza.domain.Client;
import it.unical.mat.webPizza.domain.OnlineOrder;
import it.unical.mat.webPizza.domain.Order;
import it.unical.mat.webPizza.domain.Pizza;
import it.unical.mat.webPizza.domain.PizzaChef;
import it.unical.mat.webPizza.domain.PizzaQuantity;
import it.unical.mat.webPizza.util.HibernateUtil;

public class OrderDAOImpl implements OrderDAO {


	@Override
	public Long insertOrder(String date,String status, List<PizzaQuantity> pizzas, boolean paid,
			Client client) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Long id = null;
		try {
			transaction = session.beginTransaction();
			
			Client clientToInset=(Client) session.get(Client.class, client.getId());
			List<PizzaQuantity> pizzasToInsert=new ArrayList<PizzaQuantity>();
			double totalPrice=0;
			for(PizzaQuantity pq:pizzas){
//				Pizza pizza=pq.getPizza();
//				Pizza p=(Pizza) session.get(Pizza.class,pizza.getId());
//				
//				PizzaQuantity pizzaQuantityToInsert=new PizzaQuantity();
//				pizzaQuantityToInsert.setPizza(p);
//				pizzaQuantityToInsert.setQuantity(pq.getQuantity());
//				pizzasToInsert.add(pizzaQuantityToInsert);
				session.merge(pq);
				System.out.println(pq.getPizza().getName());
				
				totalPrice+=pq.getPizza().getPrize()*pq.getQuantity();
			}
			
			
			Order order=new Order();
			order.setPizzas(pizzas);
			order.setPaid(paid);
			order.setStatus(status);
			order.setClient(clientToInset);
			order.setDate(date);
			order.setPrice(totalPrice);

			
			id = (Long) session.save(order);
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
			id=null;
		} finally {
			session.close();
		}
		
		return id;
	}

	@Override
	public int updateStatus(Long id, String status) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		int result = 0;
		try {
			transaction = session.beginTransaction();
			
			Order order=(Order) session.get(Order.class, id);
			order.setStatus(status);
			session.update(order);
			result=1;
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
			result=0;
		} finally {
			session.close();
		}
		
		return result;
	}

	@Override
	public int updateChef(Long id, PizzaChef chef) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		int result = 0;
		try {
			transaction = session.beginTransaction();
			
			PizzaChef chefToInsert=(PizzaChef) session.get(PizzaChef.class, chef.getId());
			Order order=(Order) session.get(Order.class, id);
			order.setPizzaiolo(chefToInsert);
			session.update(order);
			result=1;
			
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		
		return result;
	}

	@Override
	public String getOrderStatus(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		String result = null;
		try {
			transaction = session.beginTransaction();
			
			Order order=(Order) session.get(Order.class, id);
			result=order.getStatus();
			
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		
		return result;
	}

	@Override
	public List<Order> getNotChefAssignedOrder() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		List<Order> result = new ArrayList<Order>();
		try {
			transaction = session.beginTransaction();
			
			result=session.createQuery("FROM Order WHERE pizzaiolo is null").list();
			
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		
		return result;
	}

	@Override
	public List<Order> getAllOrderOfCLient(Long idCLient) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		List<Order> result = new ArrayList<Order>();
		try {
			transaction = session.beginTransaction();
			
			Query query=session.createQuery("FROM Order WHERE client.id=:idCLient");
			query.setParameter("idCLient", idCLient);
			
			result=query.list();
			for(Order o:result){
				Hibernate.initialize(o.getPizzas());
				for(PizzaQuantity p:o.getPizzas()){
					Hibernate.initialize(p.getPizza());
					Hibernate.initialize(p.getPizza().getIngredients());
				}
			}
			
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		
		return getOrderedOrder(result);
	}
	
	private List<Order> getOrderedOrder(List<Order> orders){
		Collections.sort(orders);
		return orders;
	}

	@Override
	public Order getOrder(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Order order = null;
		try {
			transaction = session.beginTransaction();
			
			order= (Order) session.load(Order.class, id);
			order.getStatus();
			Hibernate.initialize(order.getPizzas());
			

			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return order;
	}
	
	@Override
	public List<Order> getPizzaChefOrder(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		List<Order> result = new ArrayList<Order>();
		try {
			transaction = session.beginTransaction();
			
			Query query=session.createQuery("FROM Order WHERE pizzaiolo.id=:id and not(status=:status1)");
			query.setParameter("id", id);
			query.setParameter("status1", "Ready");

			result=query.list();
			for(Order o:result){
				Hibernate.initialize(o.getPizzas());
				for(PizzaQuantity p:o.getPizzas()){
					Hibernate.initialize(p.getPizza());
					Hibernate.initialize(p.getPizza().getIngredients());
				}
			}
			
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		
		return result;
		
	}

}
