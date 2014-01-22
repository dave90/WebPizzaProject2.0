package it.unical.mat.test.DB;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import it.unical.mat.webPizza.dao.OrderDAO;
import it.unical.mat.webPizza.dao.PizzaDAO;
import it.unical.mat.webPizza.dao.PizzaIngredientsDAO;
import it.unical.mat.webPizza.daoImpl.ClientDAOImpl;
import it.unical.mat.webPizza.daoImpl.OnlineOrderDAOImpl;
import it.unical.mat.webPizza.daoImpl.OrderDAOImpl;
import it.unical.mat.webPizza.daoImpl.PizzaChefDAOImpl;
import it.unical.mat.webPizza.daoImpl.PizzaDAOImpl;
import it.unical.mat.webPizza.daoImpl.PizzaIngredientsDAOImpl;
import it.unical.mat.webPizza.domain.OnlineOrder;
import it.unical.mat.webPizza.domain.Order;
import it.unical.mat.webPizza.domain.Pizza;
import it.unical.mat.webPizza.domain.PizzaIngredients;

import org.junit.Test;

public class TestDAOPizzaIngridientsOrder {

	@Test
	public void testPizzaIngridientsDAO() {
		PizzaIngredientsDAO ingredientsDAO = new PizzaIngredientsDAOImpl();

		int max = 50;

		PizzaIngredients toInsert[] = new PizzaIngredients[max];

		for (int i = 0; i < max; i++) {
			toInsert[i] = new PizzaIngredients();
			toInsert[i].setName(RandomGen.getRandomString());
			toInsert[i].setCost(RandomGen.getRandomNumber());
		}

		for (int i = 0; i < max; i++) {
			Long id = ingredientsDAO.insertIngredient(toInsert[i].getName(),
					toInsert[i].getCost());
			assertFalse(id == null);
			toInsert[i].setId(id);
		}

		List<PizzaIngredients> ingredients = ingredientsDAO.getAllIngredients();
		for (int i = 0; i < max; i++) {
			boolean find = false;
			for (PizzaIngredients pi : ingredients)
				if (toInsert[i].equals(pi)) {
					pi = null;
					find = true;
					break;
				}
			assertFalse(!find);
		}

		for (int i = 0; i < max; i++) {
			ingredientsDAO.deleteIngredient(toInsert[i].getId());
		}

		assertFalse(ingredients.size() != ingredientsDAO.getAllIngredients()
				.size() + max);

	}

	@Test
	public void testPizzaDAO() {
		PizzaDAO pizzaDAO = new PizzaDAOImpl();

		PizzaIngredients ingredients[] = new PizzaIngredients[5];
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

		PizzaIngredientsDAO ingredientsDAO = new PizzaIngredientsDAOImpl();
		for (int i = 0; i < 5; i++) {
			Long id = ingredientsDAO.insertIngredient(ingredients[i].getName(),
					ingredients[i].getCost());
			ingredients[i].setId(id);
		}

		int max = 50;
		Pizza pizzas[] = new Pizza[max];

		for (int i = 0; i < max; i++) {
			pizzas[i] = new Pizza();
			pizzas[i].setName(RandomGen.getRandomString());
			pizzas[i].setDiscount(RandomGen.getRandomNumber());

			HashSet<PizzaIngredients> pizzaIngres = new HashSet<PizzaIngredients>();
			for (int j = 0; j < 5; j++)
				pizzaIngres.add(ingredients[new Random()
						.nextInt(ingredients.length)]);

			pizzas[i].setIngredients(new ArrayList<PizzaIngredients>(
					pizzaIngres));

		}

		for(int i=0;i<max;i++){
			Long id=pizzaDAO.insertPizza(pizzas[i].getName(), pizzas[i].getIngredients(), pizzas[i].getDiscount(),pizzas[i].getClient());
			assertFalse(id==null);
			pizzas[i].setId(id);
		}

		List<Pizza> pizzasDB = pizzaDAO.getAllClientPizzas();
		pizzasDB.addAll(pizzaDAO.getAllMenuPizzas());

		for (int i = 0; i < max; i++) {
			boolean find = false;
			for (Pizza pi : pizzasDB) {
				if (pizzas[i].equals(pi)) {
					pi = null;
					find = true;
					break;
				}
			}
			if(!find){
				System.out.println("To FIND " +pizzas[i].getId());
				for(Pizza pipi:pizzasDB){
					if(pizzas[i].getId()==pipi.getId()){
						System.out.println(pipi.equals(pizzas[i]));
						System.out.println(pipi.getName().equals(pizzas[i].getName()));
						
					}
				}
				System.out.println();
			}
			assertFalse(!find);
		}

		for (int i = 0; i < max; i++) {
			pizzaDAO.deletePizza(pizzas[i].getId());
		}

		assertFalse(pizzasDB.size() != pizzaDAO.getAllClientPizzas().size() +pizzaDAO.getAllMenuPizzas().size() + max);

	}

	@Test
	public void testPopulatePizza() {

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

	}

}
