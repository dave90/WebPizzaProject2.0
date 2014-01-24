package it.unical.mat.webPizza.daoImpl;

import it.unical.mat.webPizza.dao.PizzaIngredientsDAO;
import it.unical.mat.webPizza.domain.Client;
import it.unical.mat.webPizza.domain.Pizza;
import it.unical.mat.webPizza.domain.PizzaIngredients;
import it.unical.mat.webPizza.util.HibernateUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PizzaIngredientsDAOImpl implements PizzaIngredientsDAO {

	@Override
	public Long insertIngredient(String name, double cost) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Long id = null;
		try {
			transaction = session.beginTransaction();

			PizzaIngredients ingredients = new PizzaIngredients();

			ingredients.setName(name);
			ingredients.setCost(cost);
			ingredients.setDeleted(false);

			id = (Long) session.save(ingredients);
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
	public int deleteIngredient(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		int result = 0;
		try {
			transaction = session.beginTransaction();


			PizzaIngredients ingredient = (PizzaIngredients) session.load(PizzaIngredients.class, id);
			ingredient.setDeleted(true);
			session.update(ingredient);

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
	public List<PizzaIngredients> getAllIngredients() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		List<PizzaIngredients> result = new ArrayList<PizzaIngredients>();
		try {
			transaction = session.beginTransaction();

			result = session.createQuery("FROM PizzaIngredients WHERE deleted=false").list();

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
	public boolean updateIngredients(Long id, String name, double cost) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		boolean result = false;
		try {
			transaction = session.beginTransaction();

			PizzaIngredients ingredient = (PizzaIngredients) session.load(PizzaIngredients.class, id);
			if (ingredient != null && ( !name.equals(ingredient.getName()) || cost!=ingredient.getCost())) {
				ingredient.setCost(cost);
				ingredient.setName(name);

				session.update(ingredient);
				for(Pizza p:ingredient.getPizzas())
					for(int i=0;i<p.getIngredients().size();i++)
						if(p.getIngredients().get(i).getId()==ingredient.getId()){
							p.getIngredients().set(i, ingredient);
						}
				
				result = true;
			}
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
			result = false;
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public PizzaIngredients getIngredient(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		PizzaIngredients result = new PizzaIngredients();
		try {
			transaction = session.beginTransaction();
			result = (PizzaIngredients) session.createQuery("FROM PizzaIngredients WHERE id=?").setLong(0, id).uniqueResult();

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
