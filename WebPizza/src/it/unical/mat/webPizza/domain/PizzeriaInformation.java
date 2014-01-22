package it.unical.mat.webPizza.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PIZZERIA")
public class PizzeriaInformation {
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	private Long id;
	
	@Column(name="ADDRESS")
	private String address;
	
	@Column(name="PHONE")
	private String telephon;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="MAIL")
	private String mail;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephon() {
		return telephon;
	}

	public void setTelephon(String telephon) {
		this.telephon = telephon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getAddressNoSpace(){
		return address.replaceAll(" ", "+");
	}
	

}
