package it.unical.mat.webPizza.dao;

import it.unical.mat.webPizza.domain.PizzeriaInformation;

public interface PizzeriaDAO {
	public Long modifyPizzeria(String address,String telephon,String name,String mail);
	public PizzeriaInformation getPizzeriaInformation();
}
