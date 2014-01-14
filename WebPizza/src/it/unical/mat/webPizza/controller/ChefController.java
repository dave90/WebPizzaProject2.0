package it.unical.mat.webPizza.controller;

import it.unical.mat.webPizza.domain.Client;
import it.unical.mat.webPizza.domain.Pizza;
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
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes({"chef","cart"})
public class ChefController {
	
	@Autowired
	private OrderManager orderManager;
	
	@Autowired
	private AccessManager accessManager;
	
	private static final Logger logger = LoggerFactory.getLogger(ChefController.class);

	
	@RequestMapping(value = "/logInChef", method = RequestMethod.POST)
	public String clientLogInValidation(@RequestParam(value="User") String usr,
										@RequestParam(value="Password") String pwd,
										Model model) {
		
		String hpwd=MD5Java.md5Java(pwd);
		
		Client client=accessManager.getClient(usr, hpwd);
		
		if(client==null){
			model.addAttribute("notifyLog", "Error : User or password wrong");
			model.addAttribute("img", "resource/img/clients/woman-eating-pizza.jpg");
			model.addAttribute("actionUrl","logIn.html");
			return "login";
		}else{
			model.addAttribute("client", client);
		}
		
		return "redirect:account.html";
	}
	
	
	
	
	
	
	

}
