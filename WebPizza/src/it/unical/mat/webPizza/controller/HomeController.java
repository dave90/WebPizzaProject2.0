package it.unical.mat.webPizza.controller;

import it.unical.mat.webPizza.daoImpl.PizzeriaDAOImpl;
import it.unical.mat.webPizza.domain.Pizza;
import it.unical.mat.webPizza.service.OrderManager;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private OrderManager orderManager;
	
	private int displayPizza=6;
	private int displayUserPizza=10;

	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index( Model model) {
		List<Pizza> menuPizza=orderManager.getAllPizza();
		List<Pizza> userPizza=orderManager.getAllClientPizza();
				
		Random r=new Random();
		while(menuPizza.size()>displayPizza){
			menuPizza.remove(r.nextInt(menuPizza.size()));
		}
		while(userPizza.size()>displayUserPizza){
			userPizza.remove(r.nextInt(userPizza.size()));
		}
		
		
		model.addAttribute("listPizza", menuPizza);
		model.addAttribute("userPizza",userPizza);
		
		
		return "index";
	}
	
	
	
	
	
	
	
}
