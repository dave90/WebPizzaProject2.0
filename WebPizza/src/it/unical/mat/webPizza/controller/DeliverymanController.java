package it.unical.mat.webPizza.controller;

import java.util.List;

import it.unical.mat.webPizza.domain.Administrator;
import it.unical.mat.webPizza.domain.Deliveryman;
import it.unical.mat.webPizza.domain.OnlineOrder;
import it.unical.mat.webPizza.domain.Order;
import it.unical.mat.webPizza.service.AccessManager;
import it.unical.mat.webPizza.service.OrderManager;
import it.unical.mat.webPizza.util.MD5Java;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"deliveryman","orders"})
public class DeliverymanController {
	
	@Autowired
	private AccessManager accessManager;
	
	@Autowired
	private OrderManager orderManager;
	
	@RequestMapping(value = "/deliverymanLogin", method = RequestMethod.GET)
	public String deliverymanLogIn( Model model) {
		if(model.containsAttribute("deliveryman"))
			return "redirect:accountDeliveryman.html";
		
		model.addAttribute("img", "resource/img/clients/deliveryman.JPG");
		model.addAttribute("actionUrl","deliverymanLogIn.html");
		
		return "login";
	}
	
	@RequestMapping(value = "/deliverymanLogIn", method = RequestMethod.POST)
	public String adminLogInValidation(@RequestParam(value="User") String usr,
										@RequestParam(value="Password") String pwd,
										Model model) {
		
		String hpwd=MD5Java.md5Java(pwd);
		
		Deliveryman deliveryman=accessManager.getDeliveryman(usr, hpwd);
		
		if(deliveryman==null){
			return "redirect:deliverymanLogin.html";
		}else{
			model.addAttribute("deliveryman", deliveryman);
		}
		
		return "redirect:accountDeliveryman.html";
	}
	
	@RequestMapping(value = "/accountDeliveryman", method = RequestMethod.GET)
	public String deliverymanAccount(Model model) {
		if(model.containsAttribute("deliveryman")){
			Long id=((Deliveryman) model.asMap().get("deliveryman")).getId();
			List<OnlineOrder> orders=orderManager.getDeliverymanOrder(id);
			model.addAttribute("orders", orders);
			
			return "accountDeliveryman";
		}
		return "redirect:deliverymanLogin.html";
	}
	
	@RequestMapping(value = "/deliverymanTour", method = RequestMethod.GET)
	public String deliverymanTour(Model model) {
		if(model.containsAttribute("deliveryman")){

			
			return "deliverymanTour";
		}
		return "redirect:deliverymanLogin.html";
	}
	

}
