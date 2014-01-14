package it.unical.mat.webPizza.util;

import it.unical.mat.webPizza.domain.Administrator;
import it.unical.mat.webPizza.domain.Client;
import it.unical.mat.webPizza.domain.Deliveryman;
import it.unical.mat.webPizza.domain.OnlineOrder;
import it.unical.mat.webPizza.domain.Order;
import it.unical.mat.webPizza.domain.Pizza;
import it.unical.mat.webPizza.domain.PizzaChef;
import it.unical.mat.webPizza.domain.PizzaIngredients;
import it.unical.mat.webPizza.domain.PizzaQuantity;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory;
	
	static {
		try {
			sessionFactory = new Configuration()
								.configure("resource/hibernate.cfg.xml")
								.addPackage("it.unical.mat.webPizza.domain") 
								.addAnnotatedClass(Administrator.class)
								.addAnnotatedClass(Client.class)
								.addAnnotatedClass(Order.class)
								.addAnnotatedClass(PizzaChef.class)
								.addAnnotatedClass(Deliveryman.class)
								.addAnnotatedClass(Pizza.class)
								.addAnnotatedClass(PizzaIngredients.class)
								.addAnnotatedClass(OnlineOrder.class)
								.addAnnotatedClass(PizzaQuantity.class)
//								.addAnnotatedClass(PUT CLASS)
								.buildSessionFactory();

		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
