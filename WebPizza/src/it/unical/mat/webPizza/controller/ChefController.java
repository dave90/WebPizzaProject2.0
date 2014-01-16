package it.unical.mat.webPizza.controller;

import it.unical.mat.webPizza.domain.Client;
import it.unical.mat.webPizza.domain.Deliveryman;
import it.unical.mat.webPizza.domain.OnlineOrder;
import it.unical.mat.webPizza.domain.Order;
import it.unical.mat.webPizza.domain.Pizza;
import it.unical.mat.webPizza.domain.PizzaChef;
import it.unical.mat.webPizza.service.AccessManager;
import it.unical.mat.webPizza.service.OrderManager;
import it.unical.mat.webPizza.util.MD5Java;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes({ "chef", "cart" })
public class ChefController {

	@Autowired
	private OrderManager orderManager;

	@Autowired
	private AccessManager accessManager;

	private static final Logger logger = LoggerFactory
			.getLogger(ChefController.class);

	@RequestMapping(value = "/chefLogin", method = RequestMethod.GET)
	public String chefLogIn(Model model) {
		if (!model.containsAttribute("chef")) {
			model.addAttribute("img", "resource/img/clients/pizzaChef.png");
			model.addAttribute("actionUrl", "chefLogIn.html");

			return "login";
		}
		return "redirect:accountPizzaChef.html";
	}

	@RequestMapping(value = "/chefLogIn", method = RequestMethod.POST)
	public String chefLogInValidation(
			@RequestParam(value = "User") String usr,
			@RequestParam(value = "Password") String pwd, Model model) {

		String hpwd = MD5Java.md5Java(pwd);
		PizzaChef chef = accessManager.getPizzaChef(usr, hpwd);

		if (chef == null) {
			model.addAttribute("notifyLog", "Error : User or password wrong");
			model.addAttribute("img",
					"resource/img/clients/woman-eating-pizza.jpg");
			model.addAttribute("actionUrl", "chefLogIn.html");
			System.out.println("null " + usr + " " + hpwd);
			return "redirect:chefLogin.html";
		} else {
			model.addAttribute("chef", chef);
		}

		return "redirect:accountPizzaChef.html";
	}

	@RequestMapping(value = "/accountPizzaChef", method = RequestMethod.GET)
	public String deliverymanAccount(Model model) {
		if (model.containsAttribute("chef")) {
			Long id = ((PizzaChef) model.asMap().get("chef")).getId();
			// List<Order> orders=orderManager.getAllNotAssignedChefOrder();
			List<Order> orders = orderManager.getPizzaChefOrder(id);
			System.out.println(orders);
			model.addAttribute("orders", orders);

			return "accountPizzaChef";
		}
		return "redirect:chefLogin.html";
	}
	
	@RequestMapping(value = "/accountChef", method = RequestMethod.GET)
	public String chefLogInValidation(Model model) {
		if(model.containsAttribute("chef")){			
			return "accountPizzaChef";
		}
		return "redirect:chefLogin.html";
	}
	
	@RequestMapping(value = "/chefLogout", method = RequestMethod.GET)
	public String chefLogout(@RequestParam(value = "idChef") Long idChef,Model model,SessionStatus sessionStatus) {
		if(model.containsAttribute("chef")){
			sessionStatus.setComplete();
		}
		return "redirect:index.html";
	}

	@RequestMapping(value = "/updateReadyOrder", method = RequestMethod.POST)
	public @ResponseBody String updateReadyOrder(Model model,@RequestParam(value = "idOrder") String idOrder) {
		//gestire se non c'è in sessione un pizzaChef
		if (model.containsAttribute("chef")) {
			orderManager.updateOrderStatus(Long.parseLong(idOrder), Order.S_READY);
			return "OK";
		}
		return "redirect:chefLogin.html";
	}

}
