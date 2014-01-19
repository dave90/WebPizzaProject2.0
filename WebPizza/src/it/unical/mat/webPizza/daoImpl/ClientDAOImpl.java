package it.unical.mat.webPizza.daoImpl;

import java.util.List;

import it.unical.mat.webPizza.dao.ClientDAO;
import it.unical.mat.webPizza.domain.Administrator;
import it.unical.mat.webPizza.domain.Client;
import it.unical.mat.webPizza.util.HibernateUtil;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

public class ClientDAOImpl implements ClientDAO {

	@Override
	public Long insertClient(String name, String surname, String user,
			String phone,String mail, String hpwd) {
	
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Long id = null;
		try {
			transaction = session.beginTransaction();
			
			Client client=new Client();
			
			client.setName(name);
			client.setSurname(surname);
			client.setPhoneNumber(phone);
			client.setMail(mail);
			client.setUsername(user);
			client.setHashPasswd(hpwd);
			
			id = (Long) session.save(client);
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
	public Client getClient(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Client client = null;
		try {
			transaction = session.beginTransaction();
			
			client= (Client) session.load(Client.class, id);
			client.getName();
			
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return client;
	}

	@Override
	public Client getClient(String usr, String hpwd) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Client client = null;
		try {
			transaction = session.beginTransaction();
			
			Query query=session.createQuery("FROM Client WHERE username=:usr AND hashPasswd=:hpwd");
			query.setParameter("usr", usr);
			query.setParameter("hpwd", hpwd);
			List<Client> list=query.list();
			if(list.size()==1)
				client=list.get(0);

			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return client;
	}

	@Override
	public boolean existUsername(String usr) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		boolean exist = true;
		try {
			transaction = session.beginTransaction();
			
			Query query=session.createQuery("FROM Client WHERE username=:usr");
			query.setParameter("usr", usr);
			List<Client> list=query.list();
			if(list.size()<=0)
				exist=false;

			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return exist;
	}

	@Override
	public boolean updateClient(Long id, String name, String surname, String user,
			String phone, String mail, String hpwd) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		boolean result = false;
		try {
			transaction = session.beginTransaction();
			
			Client client= (Client) session.load(Client.class, id);
			if(client != null){
				client.setName(name);
				client.setSurname(surname);
				client.setUsername(user);
				client.setPhoneNumber(phone);
				client.setMail(mail);
				client.setHashPasswd(hpwd);
				
				session.update(client);
				result = true;
			}
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
			result=false;
		} finally {
			session.close();
		}
		return result;
	}

}
