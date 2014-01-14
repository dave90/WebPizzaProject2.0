package it.unical.mat.webPizza.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unical.mat.webPizza.dao.AdminDAO;
import it.unical.mat.webPizza.dao.ClientDAO;
import it.unical.mat.webPizza.dao.DeliverymanDAO;
import it.unical.mat.webPizza.dao.PizzaChefDAO;
import it.unical.mat.webPizza.daoImpl.AdminDAOImpl;
import it.unical.mat.webPizza.daoImpl.ClientDAOImpl;
import it.unical.mat.webPizza.daoImpl.DeliverymanDAOImpl;
import it.unical.mat.webPizza.daoImpl.PizzaChefDAOImpl;
import it.unical.mat.webPizza.domain.Administrator;
import it.unical.mat.webPizza.domain.Client;
import it.unical.mat.webPizza.domain.Deliveryman;
import it.unical.mat.webPizza.domain.PizzaChef;

@Service
public class AccessManager {
	@Autowired
	private ClientDAO clientDAO;
	@Autowired
	private AdminDAO adminDAO;
	@Autowired
	private PizzaChefDAO chefDAO;
	@Autowired
	private DeliverymanDAO deliverymanDAO;
	
	public AccessManager() {
		clientDAO=new ClientDAOImpl();
		adminDAO=new AdminDAOImpl();
		chefDAO=new PizzaChefDAOImpl();
		deliverymanDAO=new DeliverymanDAOImpl();
	}
	
	public boolean insertClient(String name,String surname,String user,String phone,String mail,String hpwd){
		if(clientDAO.insertClient(name, surname, user, phone,mail, hpwd)>0)
			return true;
		return false;
	}
	
	public boolean insertAdmin(String name,String surname,String user,String hpwd){
		if(adminDAO.insertAdmin(name, surname, user, hpwd)>0)
			return true;
		return false;
		
	}
	
	public boolean insertPizzaChef(String name,String surname,String user,String hpwd){
		if(chefDAO.insertPizzaChef(name, surname, user, hpwd)>0)
			return true;
		return false;		
	}
	
	public boolean insertDeliveryman(String name,String surname,String user,String phone,String hpwd){
		if(deliverymanDAO.insertDeliveryman(name, surname, user, phone, hpwd)>0)
			return true;
		return false;
	}
	
	public Client getClient(String usr,String hpwd){
		return clientDAO.getClient(usr, hpwd);
	}
	
	public Administrator getAdministrator(String usr,String hpwd){
		return adminDAO.getAdmin(usr, hpwd);
	}
	
	public Deliveryman getDeliveryman(String usr,String hpwd){
		return deliverymanDAO.getDeliveryman(usr, hpwd);
	}
	
	public PizzaChef getPizzaChef(String usr,String hpwd){
		return chefDAO.getPizzaChef(usr, hpwd);
	}
	
	public boolean existClientUsername(String usr){
		return clientDAO.existUsername(usr);
	}

	public boolean updateClient(Long id,String name,String surname,String user,String phone,String mail,String hpwd){
		return clientDAO.updateClient(id, name, surname, user, phone, mail, hpwd);
	}
}
