package it.unical.mat.webPizza.daoImpl;

import it.unical.mat.webPizza.dao.OnlineOrderDAO;
import it.unical.mat.webPizza.domain.Client;
import it.unical.mat.webPizza.domain.Deliveryman;
import it.unical.mat.webPizza.domain.OnlineOrder;
import it.unical.mat.webPizza.domain.Order;
import it.unical.mat.webPizza.domain.Pizza;
import it.unical.mat.webPizza.domain.PizzaQuantity;
import it.unical.mat.webPizza.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.StaleObjectStateException;
import org.hibernate.Transaction;

public class OnlineOrderDAOImpl implements OnlineOrderDAO{

	@Override
	public Long insertOrder(String date,String status, List<PizzaQuantity> pizzas, boolean paid,
			Client client, String deliveryStatus, String address) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Long id = null;
		try {
			transaction = session.beginTransaction();
			
			double totalPrice=0;
			Client clientToInset=(Client) session.get(Client.class, client.getId());
			List<PizzaQuantity> pizzasToInsert=new ArrayList<PizzaQuantity>();
			for(PizzaQuantity pq:pizzas){
				Pizza pizza=pq.getPizza();
				Pizza p=(Pizza) session.merge(pizza);

				PizzaQuantity pizzaQuantityToInsert=new PizzaQuantity();
				pizzaQuantityToInsert.setPizza(p);
				pizzaQuantityToInsert.setQuantity(pq.getQuantity());
				pizzasToInsert.add(pizzaQuantityToInsert);
				
				totalPrice+=pq.getPizza().getPrize()*pq.getQuantity();
			}
			
			OnlineOrder order=new OnlineOrder();
			order.setPizzas(pizzasToInsert);
			order.setPaid(paid);
			order.setStatus(status);
			order.setClient(clientToInset);
			order.setDeliveryStatus(deliveryStatus);
			order.setAddress(address);
			order.setDate(date);
			order.setPrice(totalPrice);

			
			id = (Long) session.save(order);
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
			id=null;
		}finally {
			session.close();
		}
		
		return id;
	}

	@Override
	public int updateDelivery(Long id, Deliveryman deliveryman) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		int result = 0;
		try {
			transaction = session.beginTransaction();
			
			OnlineOrder order=(OnlineOrder) session.get(OnlineOrder.class, id);
			Deliveryman deliverymanToInsert=(Deliveryman) session.get(Deliveryman.class, deliveryman.getId());
			order.setDeliveryman(deliverymanToInsert);
			session.update(order);
			
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
	public int updateDeliveryStatus(Long id, String deliveryStatus) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		int result = 0;
		try {
			transaction = session.beginTransaction();
			
			OnlineOrder order=(OnlineOrder) session.get(OnlineOrder.class, id);
			order.setDeliveryStatus(deliveryStatus);
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
	public String getOrderDeliveryStatus(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		String result = "-1";
		try {
			transaction = session.beginTransaction();
			
			OnlineOrder order=(OnlineOrder) session.get(OnlineOrder.class, id);
			result=order.getDeliveryStatus();
			
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
	public List<OnlineOrder> getNotDeliveryManAssignedOrder() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		List<OnlineOrder> result = new ArrayList<OnlineOrder>();
		try {
			transaction = session.beginTransaction();
			
			result=session.createQuery("FROM OnlineOrder WHERE deliveryman is null AND status='"+Order.S_READY+"'").list();
			
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
	public String getDeliveryManPositionByOrder(Long idOrder) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		String result = null;
		try {
			transaction = session.beginTransaction();
			
			OnlineOrder order=(OnlineOrder) session.get(OnlineOrder.class, idOrder);
			
			if(order.getDeliveryman()!=null )
					result=order.getDeliveryman().getLatitude()+";"+order.getDeliveryman().getLongitude();
			
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
	public List<OnlineOrder> getdeliveryManOrder(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		List<OnlineOrder> result = new ArrayList<OnlineOrder>();
		try {
			transaction = session.beginTransaction();
			
			Query query=session.createQuery("FROM OnlineOrder WHERE deliveryman.id=:id AND ( deliveryStatus='"+OnlineOrder.D_NOT_DELIVERY+"' OR deliveryStatus='"+OnlineOrder.D_SUSPENDED+"')");
			query.setParameter("id", id);
			result=query.list();
			for(OnlineOrder o:result){
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
	
	public OnlineOrder getOnlineOrder(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		OnlineOrder order = null;
		try {
		
			transaction = session.beginTransaction();
			Query query=session.createQuery("FROM OnlineOrder WHERE id=:id");
			query.setParameter("id",id);
			order=(OnlineOrder) query.uniqueResult();
			if(order!=null){
				Hibernate.initialize(order.getPizzas());
				for(PizzaQuantity p:order.getPizzas()){
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
		return order;
	}

}
