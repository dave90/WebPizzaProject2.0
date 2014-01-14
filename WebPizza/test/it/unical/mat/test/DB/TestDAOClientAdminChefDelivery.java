package it.unical.mat.test.DB;
import static org.junit.Assert.assertFalse;
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

import java.util.Random;

import org.hibernate.Hibernate;


public class TestDAOClientAdminChefDelivery {
	

	

	@org.junit.Test
	public void testAdminDAO() {
		AdminDAO adminDAO =new AdminDAOImpl();
		
		int numberInsertion=40;
		
		Administrator admis[]=new Administrator[numberInsertion];
		for(int i=0;i<numberInsertion;i++){
			admis[i]=new Administrator();
			admis[i].setName(RandomGen.getRandomString());
			admis[i].setSurname(RandomGen.getRandomString());
			admis[i].setUsername(RandomGen.getRandomString());
			admis[i].setHashPasswd(RandomGen.getRandomString());
		}
		
		for(int i=0;i<numberInsertion;i++){
			Long id=adminDAO.insertAdmin(admis[i].getName(), admis[i].getSurname(), admis[i].getUsername(), admis[i].getHashPasswd());
			assertFalse(id==null);
			admis[i].setId(id);
		}
		
		for(int i=0;i<numberInsertion;i++){
			Administrator dbADmin=adminDAO.getAdmin(admis[i].getId());
			assertFalse(!dbADmin.equals(admis[i]));
		}
		
		for(int i=0;i<numberInsertion;i++){
			Administrator dbADmin=adminDAO.getAdmin(admis[i].getUsername(), admis[i].getHashPasswd());
			assertFalse(!dbADmin.equals(admis[i]));
		}
		
		
		
	}
	
	@org.junit.Test
	public void testClientDAO() {
		ClientDAO clientDAO=new ClientDAOImpl();
		
		int numberInsertion=40;
		
		Client clients[]=new Client[numberInsertion];
		for(int i=0;i<numberInsertion;i++){
			clients[i]=new Client();
			clients[i].setName(RandomGen.getRandomString());
			clients[i].setSurname(RandomGen.getRandomString());
			clients[i].setPhoneNumber(RandomGen.getRandomString());
			clients[i].setUsername(RandomGen.getRandomString());
			clients[i].setHashPasswd(RandomGen.getRandomString());
			clients[i].setMail(RandomGen.getRandomString());
		}
		
		for(int i=0;i<numberInsertion;i++){
			Long id=clientDAO.insertClient(clients[i].getName(), clients[i].getSurname(), clients[i].getUsername(), clients[i].getPhoneNumber(),clients[i].getMail(), clients[i].getHashPasswd());
			assertFalse(id==null);
			clients[i].setId(id);
		}
		
		for(int i=0;i<numberInsertion;i++){
			Client clientDB=clientDAO.getClient(clients[i].getId());
			assertFalse(!clientDB.equals(clients[i]));
		}
		
		for(int i=0;i<numberInsertion;i++){
			Client dbADmin=clientDAO.getClient(clients[i].getUsername(), clients[i].getHashPasswd());
			assertFalse(!dbADmin.equals(clients[i]));
		}
		
	}
	
	@org.junit.Test
	public void testDeliveryDAO() {
		DeliverymanDAO deliveryDAO=new DeliverymanDAOImpl();
		
		int numberInsertion=40;
		
		Deliveryman deliverys[]=new Deliveryman[numberInsertion];
		for(int i=0;i<numberInsertion;i++){
			deliverys[i]=new Deliveryman();
			deliverys[i].setName(RandomGen.getRandomString());
			deliverys[i].setSurname(RandomGen.getRandomString());
			deliverys[i].setPhoneNumber(RandomGen.getRandomString());
			deliverys[i].setUsername(RandomGen.getRandomString());
			deliverys[i].setHashPasswd(RandomGen.getRandomString());
		}
		
		for(int i=0;i<numberInsertion;i++){
			Long id=deliveryDAO.insertDeliveryman(deliverys[i].getName(), deliverys[i].getSurname(), deliverys[i].getUsername(), deliverys[i].getPhoneNumber(), deliverys[i].getHashPasswd());
			assertFalse(id==null);
			deliverys[i].setId(id);
		}
		
		for(int i=0;i<numberInsertion;i++){
			Deliveryman clientDB=deliveryDAO.getDeliveryman(deliverys[i].getId());
			assertFalse(!clientDB.equals(deliverys[i]));
		}
		
		for(int i=0;i<numberInsertion;i++){
			Deliveryman dbADmin=deliveryDAO.getDeliveryman(deliverys[i].getUsername(), deliverys[i].getHashPasswd());
			assertFalse(!dbADmin.equals(deliverys[i]));
		}
		
	}
	
	@org.junit.Test
	public void testChefDAO() {
		PizzaChefDAO chefDAO=new PizzaChefDAOImpl();
		
		int numberInsertion=40;
		
		PizzaChef chefs[]=new PizzaChef[numberInsertion];
		for(int i=0;i<numberInsertion;i++){
			chefs[i]=new PizzaChef();
			chefs[i].setName(RandomGen.getRandomString());
			chefs[i].setSurname(RandomGen.getRandomString());
			chefs[i].setUsername(RandomGen.getRandomString());
			chefs[i].setHashPasswd(RandomGen.getRandomString());
		}
		
		for(int i=0;i<numberInsertion;i++){
			Long id=chefDAO.insertPizzaChef(chefs[i].getName(), chefs[i].getSurname(), chefs[i].getUsername(), chefs[i].getHashPasswd());
			assertFalse(id==null);
			chefs[i].setId(id);
		}
		
		for(int i=0;i<numberInsertion;i++){
			PizzaChef clientDB=chefDAO.getPizzaChef(chefs[i].getId());
			assertFalse(!clientDB.equals(chefs[i]));
		}
		
		for(int i=0;i<numberInsertion;i++){
			PizzaChef dbADmin=chefDAO.getPizzaChef(chefs[i].getUsername(), chefs[i].getHashPasswd());
			assertFalse(!dbADmin.equals(chefs[i]));
		}
		
	}
	

}
