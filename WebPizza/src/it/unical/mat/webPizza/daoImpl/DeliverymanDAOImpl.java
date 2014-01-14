package it.unical.mat.webPizza.daoImpl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unical.mat.webPizza.dao.DeliverymanDAO;
import it.unical.mat.webPizza.domain.Administrator;
import it.unical.mat.webPizza.domain.Deliveryman;
import it.unical.mat.webPizza.util.HibernateUtil;

public class DeliverymanDAOImpl implements DeliverymanDAO {

	@Override
	public Long insertDeliveryman(String name, String surname, String user,
			String phone, String hpwd) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Long id = null;
		try {
			transaction = session.beginTransaction();
			
			Deliveryman delivery=new Deliveryman();
			
			delivery.setName(name);
			delivery.setSurname(surname);
			delivery.setUsername(user);
			delivery.setHashPasswd(hpwd);
			delivery.setPhoneNumber(phone);
			
			id = (Long) session.save(delivery);
			
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
	public Deliveryman getDeliveryman(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Deliveryman delivery = null;
		try {
			transaction = session.beginTransaction();
			
			delivery= (Deliveryman) session.load(Deliveryman.class, id);

			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return delivery;
	}

	@Override
	public Deliveryman getDeliveryman(String usr, String hpwd) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Deliveryman delivery = null;
		try {
			transaction = session.beginTransaction();
			
			Query query=session.createQuery("FROM Deliveryman WHERE username=:usr AND hashPasswd=:hpwd");
			query.setParameter("usr", usr);
			query.setParameter("hpwd", hpwd);
			List<Deliveryman> list=query.list();
			if(list.size()==1)
				delivery=list.get(0);

			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return delivery;
	}

	@Override
	public int updateLatLong(Long id,Long longitude, Long latitude) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		int result = 0;
		try {
			transaction = session.beginTransaction();
			
			Deliveryman delivery= (Deliveryman) session.load(Deliveryman.class, id);
			delivery.setLatitude(latitude);
			delivery.setLongitude(longitude);
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
	public String getLatLong(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Deliveryman delivery = null;
		String latLong = null;
		try {
			transaction = session.beginTransaction();
			
			delivery= (Deliveryman) session.load(Deliveryman.class, id);
			latLong=delivery.getLatitude()+""+delivery.getLongitude();
			
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return latLong;
	}

}
