package it.unical.mat.webPizza.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unical.mat.webPizza.dao.PizzaIngredientsDAO;
import it.unical.mat.webPizza.dao.PizzeriaDAO;
import it.unical.mat.webPizza.domain.Pizza;
import it.unical.mat.webPizza.domain.PizzaIngredients;
import it.unical.mat.webPizza.domain.PizzeriaInformation;
import it.unical.mat.webPizza.util.HibernateUtil;

public class PizzeriaDAOImpl implements PizzeriaDAO {

	@Override
	public Long modifyPizzeria(String address, String telephon, String name, String mail) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Long id = null;
		boolean exist=true;
		try {
			transaction = session.beginTransaction();

			PizzeriaInformation pizzeria;
			pizzeria = (PizzeriaInformation) session.createQuery("FROM PizzeriaInformation").uniqueResult();
			if (pizzeria == null) {
				pizzeria = new PizzeriaInformation();
				exist=false;
			}
			
			pizzeria.setAddress(address);
			pizzeria.setMail(mail);
			pizzeria.setName(name);
			pizzeria.setTelephon(telephon);
			if(exist)
				session.update(pizzeria);
			else
				session.save(pizzeria);
			

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
	public PizzeriaInformation getPizzeriaInformation() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		PizzeriaInformation pizzeria = null;
		try {
			transaction = session.beginTransaction();

			pizzeria = (PizzeriaInformation) session.createQuery("FROM PizzeriaInformation").uniqueResult();
			if(pizzeria==null)
				pizzeria=new PizzeriaInformation();
			
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}

		return pizzeria;

	}

}
