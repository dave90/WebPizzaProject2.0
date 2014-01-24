package it.unical.mat.webPizza.controller;

import java.util.ArrayList;
import java.util.List;

import it.unical.mat.webPizza.domain.Administrator;
import it.unical.mat.webPizza.domain.Deliveryman;
import it.unical.mat.webPizza.domain.OnlineOrder;
import it.unical.mat.webPizza.domain.Order;
import it.unical.mat.webPizza.service.AccessManager;
import it.unical.mat.webPizza.service.DeliverymanTourManager;
import it.unical.mat.webPizza.service.MangeBalanceDeliveryman;
import it.unical.mat.webPizza.service.OrderManager;
import it.unical.mat.webPizza.util.MD5Java;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes({"deliveryman","ordersManager"})
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
	
	@RequestMapping(value = "/deliverymanLogout", method = RequestMethod.GET)
	public String deliverymanLogout(@RequestParam(value="idDeliveryman") Long id,
										Model model,SessionStatus sessionStatus) {
		if(model.containsAttribute("deliveryman")){			
			sessionStatus.setComplete();
		}
		return "redirect:index.html";
	}
	
	@RequestMapping(value = "/accountDeliveryman", method = RequestMethod.GET)
	public String deliverymanAccount(Model model) {
		if(model.containsAttribute("deliveryman")){
			Long id=((Deliveryman) model.asMap().get("deliveryman")).getId();
			List<OnlineOrder> orders=orderManager.getDeliverymanOrder(id);
			DeliverymanTourManager manager=new DeliverymanTourManager(orders);
			model.addAttribute("ordersManager", manager);
			model.addAttribute("orders", manager.getOrders());
			
			return "accountDeliveryman";
		}
		return "redirect:deliverymanLogin.html";
	}
	
	@RequestMapping(value = "/deliverymanTour", method = RequestMethod.GET)
	public String deliverymanTour(Model model) {
		if(model.containsAttribute("deliveryman")){
			DeliverymanTourManager manager= (DeliverymanTourManager) model.asMap().get("ordersManager");
			if(manager.getOrders().size()>0){
				model.addAttribute("order", manager.getOrders().get(0));
				model.addAttribute("ids", manager.getIds());
			}
			return "deliverymanTour";
		}
		return "redirect:deliverymanLogin.html";
	}
	
	@RequestMapping(value = "/deliveryOrder", method = RequestMethod.POST)
	public @ResponseBody String getDeliveryOrder(@RequestParam(value="id") Long id,
										Model model) {
		
		if(model.containsAttribute("deliveryman")){
			DeliverymanTourManager manager= (DeliverymanTourManager) model.asMap().get("ordersManager");
			
			return manager.getOrderResponse(id);
		}
		
		return "Error";
	}
	
	@RequestMapping(value = "/deliveryLatLong", method = RequestMethod.POST)
	public @ResponseBody String setDeliveryLatLong(@RequestParam(value="lat") double lat,@RequestParam(value="long") double longi,
										Model model) {
		
		if(model.containsAttribute("deliveryman")){
			Deliveryman deliveryman=(Deliveryman) model.asMap().get("deliveryman");
			accessManager.setLatLongDeliveryman(deliveryman.getId(), lat, longi);
			return "OK";
		}
		
		return "Error";
	}
	
	@RequestMapping(value = "/getdeliveryLatLong", method = RequestMethod.POST)
	public @ResponseBody String getDeliveryLatLong(@RequestParam(value="id") Long id,
										Model model) {
		
		String latLangString=orderManager.getDeliveryManPositionOrder(id);
		if(latLangString==null)
			return "no latitude longitude find";
		
		return latLangString;
	}
	
	@RequestMapping(value = "/deliveryModifyStatusOrder", method = RequestMethod.POST)
	public @ResponseBody String setDeliveryStatus(@RequestParam(value="id") Long id,@RequestParam(value="status") String status,
										Model model) {
		
		if(model.containsAttribute("deliveryman")){
			String deliveryStatus=null;
			
			if(status.equals(OnlineOrder.D_ANNULLED))
				deliveryStatus=OnlineOrder.D_ANNULLED;
			if(status.equals(OnlineOrder.D_DELIVERED))
				deliveryStatus=OnlineOrder.D_DELIVERED;
			if(status.equals(OnlineOrder.D_NOT_DELIVERY))
				deliveryStatus=OnlineOrder.D_NOT_DELIVERY;
			if(status.equals(OnlineOrder.D_SUSPENDED))
				deliveryStatus=OnlineOrder.D_SUSPENDED;
			if(deliveryStatus==null)
				return "sTATUS WRONG";	
			
			
			if(orderManager.updateDeliveryStatus(id, deliveryStatus)){
				DeliverymanTourManager manager= (DeliverymanTourManager) model.asMap().get("ordersManager");
				manager.setStatusOrder(id, status);
				return status;
			}
			
			return "Status not changed";
		}
		
		return "Error";
	}

}
