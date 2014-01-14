package it.unical.mat.webPizza.dao;

import it.unical.mat.webPizza.domain.Client;


public interface ClientDAO {
	
	public Long insertClient(String name,String surname,String user,String phone,String mail,String hpwd);
	public Client getClient(Long id);
	public Client getClient(String usr,String hpwd);
	public boolean existUsername(String usr);
	public boolean updateClient(Long id,String name,String surname,String user,String phone,String mail,String hpwd);

}
