package it.unical.mat.webPizza.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="PIZZA_QUANTITY")
public class PizzaQuantity {
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade = { CascadeType.ALL })
	@JoinTable(name = "PIZZA_QUANTITY_PIZZA", 
	joinColumns = { @JoinColumn(name = "PIZZA_QUANTITY_ID") }, 
	inverseJoinColumns = { @JoinColumn(name = "PIZZA_ID")})
	private Pizza pizza;
	
	@Column(name="QUANTITY")
	private int quantity;
	
	public PizzaQuantity() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pizza == null) ? 0 : pizza.hashCode());
		result = prime * result + quantity;
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
		PizzaQuantity other = (PizzaQuantity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (pizza == null) {
			if (other.pizza != null)
				return false;
		} else if (!pizza.equals(other.pizza))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}

}
