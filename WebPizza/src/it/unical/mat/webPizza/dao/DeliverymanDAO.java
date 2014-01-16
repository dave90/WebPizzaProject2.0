package it.unical.mat.webPizza.dao;

import it.unical.mat.webPizza.domain.Deliveryman;

public interface DeliverymanDAO {
	
	public Long insertDeliveryman(String name,String surname,String user,String phone, String hpwd);
	public Deliveryman getDeliveryman(Long id);
	public Deliveryman getDeliveryman(String usr,String hpwd);
	public int updateLatLong(Long id,double longitude,double latitude);
	public String getLatLong(Long id);
	public boolean isFreeDeliveryman(Long id);

}
