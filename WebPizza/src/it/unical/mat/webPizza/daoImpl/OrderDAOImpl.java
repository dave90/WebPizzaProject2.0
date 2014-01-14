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
import it.unical.mat.webPizza.domain.Order;
import it.unical.mat.webPizza.domain.Pizza;
import it.unical.mat.webPizza.domain.PizzaChef;
import it.unical.mat.webPizza.domain.PizzaQuantity;
import it.unical.mat.webPizza.util.HibernateUtil;

public class OrderDAOImpl implements OrderDAO {

	@Override
	public Long insertOrder(String date,String status, List<PizzaQuantity> pizzas,boolean paid, Client client,
			PizzaChef chef) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Long id = null;
		try {
			transaction = session.beginTransaction();
			
			Order order=new Order();
			order.setPizzas(pizzas);
			order.setPaid(paid);
			order.setStatus(status);
			order.setClient(client);
			order.setPizzaiolo(chef);
			order.setDate(date);
			
			id = (Long) session.save(order);
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		
		return id;
	}

	@Override
	public Long insertOrder(String date,String status, List<PizzaQuantity> pizzas, boolean paid,
			Client client) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Long id = null;
		try {
			transaction = session.beginTransaction();
			
			Order order=new Order();
			order.setPizzas(pizzas);
			order.setPaid(paid);
			order.setStatus(status);
			order.setClient(client);
			order.setDate(date);
			
			id = (Long) session.save(order);
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
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
			
			Order order=(Order) session.get(Order.class, id);
			order.setPizzaiolo(chef);
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
//			for(Order o:result){
//				Hibernate.initialize(o.getPizzas());
//				for(PizzaQuantity p:o.getPizzas()){
//					Hibernate.initialize(p.getPizza());
//					Hibernate.initialize(p.getPizza().getIngredients());
//				}
//			}
			
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

			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return order;
	}

}
