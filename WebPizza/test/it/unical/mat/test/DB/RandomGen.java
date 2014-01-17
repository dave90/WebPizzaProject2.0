package it.unical.mat.test.DB;

import static org.junit.Assert.assertFalse;
import it.unical.mat.webPizza.dao.OnlineOrderDAO;
import it.unical.mat.webPizza.dao.PizzaDAO;
import it.unical.mat.webPizza.daoImpl.AdminDAOImpl;
import it.unical.mat.webPizza.daoImpl.DeliverymanDAOImpl;
import it.unical.mat.webPizza.daoImpl.OnlineOrderDAOImpl;
import it.unical.mat.webPizza.daoImpl.PizzaChefDAOImpl;
import it.unical.mat.webPizza.daoImpl.PizzaDAOImpl;
import it.unical.mat.webPizza.domain.Deliveryman;
import it.unical.mat.webPizza.domain.OnlineOrder;
import it.unical.mat.webPizza.domain.Pizza;
import it.unical.mat.webPizza.domain.PizzaIngredients;
import it.unical.mat.webPizza.service.AccessManager;
import it.unical.mat.webPizza.util.MD5Java;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class RandomGen {
	
	public static void main(String[] args) {
		PizzaDAO pizzaDAO = new PizzaDAOImpl();

		PizzaIngredients ingredients[] = new PizzaIngredients[6];
		ingredients[0] = new PizzaIngredients();
		ingredients[0].setCost(2);
		ingredients[0].setName("pomodoro");
		ingredients[1] = new PizzaIngredients();
		ingredients[1].setCost(1);
		ingredients[1].setName("mozzarella");
		ingredients[2] = new PizzaIngredients();
		ingredients[2].setCost(3);
		ingredients[2].setName("olive");
		ingredients[3] = new PizzaIngredients();
		ingredients[3].setCost(5);
		ingredients[3].setName("salsiccia");
		ingredients[4] = new PizzaIngredients();
		ingredients[4].setCost(2);
		ingredients[4].setName("tonno");
		ingredients[5] = new PizzaIngredients();
		ingredients[5].setCost(2);
		ingredients[5].setName("cipolla");

		int max = 5;
		Pizza pizzas[] = new Pizza[max];

		pizzas[0] = new Pizza();
		pizzas[0].setName("Quattro Stagioni");
		pizzas[0].setDiscount(0);

		HashSet<PizzaIngredients> pizzaIngres = new HashSet<PizzaIngredients>();
		pizzaIngres.add(ingredients[0]);
		pizzaIngres.add(ingredients[1]);
		pizzaIngres.add(ingredients[2]);
		pizzaIngres.add(ingredients[3]);
		pizzaIngres.add(ingredients[5]);

		pizzas[0].setIngredients(new ArrayList<PizzaIngredients>(pizzaIngres));

		pizzas[1] = new Pizza();
		pizzas[1].setName("Calabrese");
		pizzas[1].setDiscount(0);

		pizzaIngres = new HashSet<PizzaIngredients>();
		pizzaIngres.add(ingredients[0]);
		pizzaIngres.add(ingredients[1]);
		pizzaIngres.add(ingredients[2]);

		pizzas[1].setIngredients(new ArrayList<PizzaIngredients>(pizzaIngres));

		pizzas[2] = new Pizza();
		pizzas[2].setName("Tonnara");
		pizzas[2].setDiscount(0);

		pizzaIngres = new HashSet<PizzaIngredients>();
		pizzaIngres.add(ingredients[0]);
		pizzaIngres.add(ingredients[1]);
		pizzaIngres.add(ingredients[3]);
		pizzaIngres.add(ingredients[5]);

		pizzas[2].setIngredients(new ArrayList<PizzaIngredients>(pizzaIngres));

		pizzas[3] = new Pizza();
		pizzas[3].setName("Salame");
		pizzas[3].setDiscount(0);

		pizzaIngres = new HashSet<PizzaIngredients>();
		pizzaIngres.add(ingredients[0]);
		pizzaIngres.add(ingredients[1]);
		pizzaIngres.add(ingredients[3]);

		pizzas[3].setIngredients(new ArrayList<PizzaIngredients>(pizzaIngres));

		pizzas[4] = new Pizza();
		pizzas[4].setName("Margherita");
		pizzas[4].setDiscount(0);

		pizzaIngres = new HashSet<PizzaIngredients>();
		pizzaIngres.add(ingredients[0]);
		pizzaIngres.add(ingredients[1]);

		pizzas[4].setIngredients(new ArrayList<PizzaIngredients>(pizzaIngres));

		for (int i = 0; i < max; i++) {
			Long id = pizzaDAO.insertPizza(pizzas[i].getName(),
					pizzas[i].getIngredients(), pizzas[i].getDiscount(),null);
			System.out.println("pizzasDB id " + id);
			assertFalse(id == null);
			pizzas[i].setId(id);
		}
		
		new AdminDAOImpl().insertAdmin("admin", "admin", "admin", "81dc9bdb52d04dc20036dbd8313ed055");
		new PizzaChefDAOImpl().insertPizzaChef("carmelo", "lagamba", "melo", "81dc9bdb52d04dc20036dbd8313ed055");
		new DeliverymanDAOImpl().insertDeliveryman("davide", "fusca", "mario", "3243145", "81dc9bdb52d04dc20036dbd8313ed055");
		new PizzaChefDAOImpl().insertPizzaChef("giovanni", "ianni", "gianni", "81dc9bdb52d04dc20036dbd8313ed055");
		new DeliverymanDAOImpl().insertDeliveryman("francesco", "calimeri", "cali", "3243145", "81dc9bdb52d04dc20036dbd8313ed055");
		new PizzaChefDAOImpl().insertPizzaChef("maurizio", "macri", "zzmauri", "81dc9bdb52d04dc20036dbd8313ed055");
		
		
	}
	
	static Random r =new Random();
	static int maxLenght=20;
	
	static int getRandomNumber(){
		return r.nextInt(maxLenght);
	}
	
	static char[] symbols={'a','b','c','d','e','f','g','1','2','5','8'};
	static String getRandomString(){
		String string=new String();
		for(int i=0;i<maxLenght;i++)
			string+=symbols[r.nextInt(symbols.length)];
		return string;
	}

}
