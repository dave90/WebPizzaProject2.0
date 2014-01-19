package it.unical.mat.webPizza.daoImpl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unical.mat.webPizza.dao.PizzaChefDAO;
import it.unical.mat.webPizza.domain.Administrator;
import it.unical.mat.webPizza.domain.Client;
import it.unical.mat.webPizza.domain.PizzaChef;
import it.unical.mat.webPizza.util.HibernateUtil;

public class PizzaChefDAOImpl implements PizzaChefDAO {

	@Override
	public Long insertPizzaChef(String name, String surname, String user,
			String hpwd) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Long id = null;
		try {
			transaction = session.beginTransaction();
			
			PizzaChef chef=new PizzaChef();
			
			chef.setName(name);
			chef.setSurname(surname);
			chef.setUsername(user);
			chef.setHashPasswd(hpwd);
			
			id = (Long) session.save(chef);
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
	public PizzaChef getPizzaChef(Long id) {
	
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		PizzaChef chef = null;
		try {
			transaction = session.beginTransaction();
			
			chef= (PizzaChef) session.load(PizzaChef.class, id);
			chef.getName();
			
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return chef;
	}

	@Override
	public PizzaChef getPizzaChef(String usr, String hpwd) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		PizzaChef chef = null;
		try {
			transaction = session.beginTransaction();
			
			Query query=session.createQuery("FROM PizzaChef WHERE username=:usr AND hashPasswd=:hpwd");
			query.setParameter("usr", usr);
			query.setParameter("hpwd", hpwd);
			List<PizzaChef> list=query.list();
			if(list.size()==1)
				chef=list.get(0);

			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return chef;
	}

}
