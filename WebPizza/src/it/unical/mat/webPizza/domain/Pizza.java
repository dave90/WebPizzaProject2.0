package it.unical.mat.webPizza.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="PIZZA")
public class Pizza implements Serializable{
	// aggiungere descrizione pizza e immagine?
	@Id
	@GeneratedValue
	@Column(name="ID")
	private Long id;
	
	@Column(name="NAME")
	private String name;
	
	
	@ManyToMany(fetch=FetchType.EAGER,cascade = { CascadeType.ALL })
	@JoinTable(name = "PIZZA_INGREDIENTS", 
	joinColumns = { @JoinColumn(name = "PIZZA_ID") }, 
	inverseJoinColumns = { @JoinColumn(name = "INGREDIENTS_ID")})
	private List<PizzaIngredients> ingredients=new ArrayList<PizzaIngredients>();
	
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinTable(name = "PIZZA_CLIENT", 
	joinColumns = { @JoinColumn(name = "PIZZA_ID") }, 
	inverseJoinColumns = { @JoinColumn(name = "CLIENT_ID")})
	private Client client;
	
	@Column(name="DISCOUNT")
	private double discount;
	
	public Pizza() {
	}
	
	public double getPrize(){
		
		double prize=0;
		
		for(PizzaIngredients i:ingredients)
			prize+=i.getCost();
		
		prize-=discount;
		
		return prize;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PizzaIngredients> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<PizzaIngredients> ingredients) {
		this.ingredients = ingredients;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		long temp;
		temp = Double.doubleToLongBits(discount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pizza other = (Pizza) obj;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (Double.doubleToLongBits(discount) != Double.doubleToLongBits(other.discount))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pizza [id=" + id + ", name=" + name + ", ingredients="
				+ ingredients + ", discount=" + discount + "]";
	}

}
