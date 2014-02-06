package it.unical.mat.webPizza.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.servlet.http.HttpSession;

import it.unical.mat.webPizza.dao.PizzaChefDAO;
import it.unical.mat.webPizza.daoImpl.PizzaChefDAOImpl;
import it.unical.mat.webPizza.domain.Client;
import it.unical.mat.webPizza.domain.OnlineOrder;
import it.unical.mat.webPizza.domain.Order;
import it.unical.mat.webPizza.domain.Pizza;
import it.unical.mat.webPizza.domain.PizzaIngredients;
import it.unical.mat.webPizza.domain.PizzaQuantity;
import it.unical.mat.webPizza.service.AccessManager;
import it.unical.mat.webPizza.service.ManageBalanceChef;
import it.unical.mat.webPizza.service.OrderManager;
import it.unical.mat.webPizza.service.ShoppingCart;
import it.unical.mat.webPizza.util.MD5Java;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;
import com.sun.xml.internal.bind.v2.TODO;

@Controller
@SessionAttributes({"client","cart"})
public class AccountController {
	@Autowired
	private AccessManager accessManager;
	
	@Autowired
	private OrderManager orderManager;
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String clientLogIn( Model model) {
		if(model.containsAttribute("client"))
			return "redirect:account.html";
		
		model.addAttribute("img", "resource/img/clients/woman-eating-pizza.jpg");
		model.addAttribute("actionUrl","logIn.html");
		
		return "login";
	}
	
	
	
	
	@RequestMapping(value = "/logIn", method = RequestMethod.POST)
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
	
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String clientAccount( Model model) {
		if(model.containsAttribute("client")){	
			List<Order> orders=orderManager.getAllOrderFromCLient(((Client) model.asMap().get("client")));
			while(orders.size()>3)
				orders.remove(orders.size()-1);

			model.addAttribute("orders", orders);
			return "account";
		}
		return "redirect:login.html";
	}
	
	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
	public String clientCheckout( Model model) {
		if(model.containsAttribute("client")){	
			if(model.containsAttribute("cart") && ((ShoppingCart)model.asMap().get("cart")).getTotalprice()!=0)
				return "checkout";
			else
				return "redirect:pizzaList.html";
				
		}
		return "redirect:login.html";
	}
	
	@RequestMapping(value = "/viewcart", method = RequestMethod.GET)
	public String clientViewcart( Model model) {

		return "viewcart";
	}
	
	
	@RequestMapping(value = "/editprofile", method = RequestMethod.GET)
	public String clientEditprofile( Model model) {
		if(model.containsAttribute("client")){			
			return "editprofile";
		}
		return "redirect:login.html";
	}
	
	
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String clientRegister( Model model, SessionStatus status) {
		if(model.containsAttribute("client")){			
			status.setComplete();
			
			return "redirect:index.html";
		}
		return "register";
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String clientRegistration(@RequestParam(value="User") String usr,
										@RequestParam(value="Password") String pwd,
										@RequestParam(value="Name") String name,
										@RequestParam(value="Surname") String surname,
										@RequestParam(value="Mail") String mail,
										@RequestParam(value="Phone") String phone,
										Model model) {
		
		if(usr==null || usr.equals("")){
			model.addAttribute("notifyRegistration", "Insert Username");
			return "register";
		}
		if(pwd==null || pwd.equals("")){
			model.addAttribute("notifyRegistration", "Insert Password");
			return "register";
		}
		if(name==null || name.equals("")){
			model.addAttribute("notifyRegistration", "Insert Name");
			return "register";
		}
		if(surname==null || surname.equals("")){
			model.addAttribute("notifyRegistration", "Insert Surname");
			return "register";
		}
		
		if(mail==null)
			mail="";
		if(phone==null)
			phone="";
		
		if(accessManager.existClientUsername(usr)){
			model.addAttribute("notifyRegistration", "Exist Username");
			return "register";
		}
		
		String hpwd=MD5Java.md5Java(pwd);
		
		if(accessManager.insertClient(name, surname, usr, phone, mail, hpwd)){
			
			Client client=accessManager.getClient(usr, hpwd);
			model.addAttribute("client", client);
			
			return "redirect:account.html";
		}
		
		model.addAttribute("notifyRegistration", "Registration aborted, contact Administrator");
		
		return "redirect:register.html";
		
	}
	
	@RequestMapping(value = "/existClientUser", method = RequestMethod.POST)
	public @ResponseBody String existClientUsername(@RequestParam(value="user") String usr,
										Model model) {
		
		if(accessManager.existClientUsername(usr)){
			model.addAttribute("notifyRegistration", "Exist Username");
			return "User Exist";
		}
		
		return "OK";
		
	}
	
	@RequestMapping(value = "/editProfile", method = RequestMethod.POST)
	public String clientEditprofile(@RequestParam(value="User") String usr,
										@RequestParam(value="Password") String pwd,
										@RequestParam(value="Name") String name,
										@RequestParam(value="Surname") String surname,
										@RequestParam(value="Mail") String mail,
										@RequestParam(value="Phone") String phone,
										Model model) {
		
		if(!model.containsAttribute("client")){			
			return "redirect:login.html";
		}
		
		Long id=((Client)model.asMap().get("client")).getId();
		
		if(usr==null || usr.equals("")){
			model.addAttribute("notifyRegistration", "Insert Username");
			return "editprofile";
		}
		if(pwd==null || pwd.equals("")){
			model.addAttribute("notifyRegistration", "Insert Password");
			return "editprofile";
		}
		if(name==null || name.equals("")){
			model.addAttribute("notifyRegistration", "Insert Name");
			return "editprofile";
		}
		if(surname==null || surname.equals("")){
			model.addAttribute("notifyRegistration", "Insert Surname");
			return "editprofile";
		}
		
		String userName=((Client)model.asMap().get("client")).getUsername();
		
		if(!userName.equals(usr) && accessManager.existClientUsername(usr)){
			model.addAttribute("notifyRegistration", "Username exist");
			return "editprofile";
		}
		
		if(mail==null)
			mail="";
		if(phone==null)
			phone="";
		
		
		String hpwd=MD5Java.md5Java(pwd);
		
		if(accessManager.updateClient(id, name, surname, usr, phone, mail, hpwd)){
			
			Client client=accessManager.getClient(usr, hpwd);
			model.addAttribute("client", client);
			
			return "redirect:account.html";
		}
		
		model.addAttribute("notifyRegistration", "Registration aborted, contact Administrator");
		
		return "redirect:account.html";
		
	}

//	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
//	public String checkout( Model model) {
//		
//		return "checkout";
//	}
	
	@RequestMapping(value = "/addToCart", method = RequestMethod.POST)
	public @ResponseBody String addToCart(@RequestParam(value="idPizza") Long id,@RequestParam(value="quantity") int quantity,Model model,HttpSession session) {
		ShoppingCart cartPizzas;
		if(!model.containsAttribute("cart")){
			cartPizzas=new ShoppingCart();
			model.addAttribute("cart", cartPizzas);
		}else{
			cartPizzas=(ShoppingCart) model.asMap().get("cart");
		}
		
		if(quantity<0)
			return cartPizzas.getTableBody();
		
		cartPizzas.insertPizza(id, orderManager.getPizza(id), quantity);
		
		return cartPizzas.getTableBody();
	}
	
	@RequestMapping(value = "/addToCartBuild", method = RequestMethod.POST)
	public @ResponseBody
	String addToCartBuild(@RequestParam(value = "quantity") int quantity,
			@RequestParam(value = "send") String ingridients, Model model,
			HttpSession session) {
		ShoppingCart cartPizzas;
		if (!model.containsAttribute("cart")) {
			cartPizzas = new ShoppingCart();
			model.addAttribute("cart", cartPizzas);
		} else {
			cartPizzas = (ShoppingCart) model.asMap().get("cart");
		}
		
		if(!model.containsAttribute("client"))
			return "redirect:account.html";
		Client client=(Client)model.asMap().get("client");
		cartPizzas.insertPizzaBuild(client.getUsername()
				+ new Random().nextInt(), quantity, ingridients,
				orderManager,client);

		return cartPizzas.getTableBody();
	}

	@RequestMapping(value = "/removeFromCartBuild", method = RequestMethod.POST)
	public @ResponseBody
	String removeFromCartBuild(@RequestParam(value = "name") String name,
			Model model, HttpSession session) {
		ShoppingCart cartPizzas = null;
		if(!model.containsAttribute("cart")){
			return "No cart inizializated";
		}
		cartPizzas=(ShoppingCart) model.asMap().get("cart");
		
		cartPizzas.deletePizza(name);
		return Integer.toString(cartPizzas.getTotalprice());
	}
	
	@RequestMapping(value = "/checkOut", method = RequestMethod.POST)
	public String checkOut(@RequestParam(value="Name") String name,
						   @RequestParam(value="Surname") String surname,
						   @RequestParam(value="Mail") String mail,
						   @RequestParam(value="Phone") String phone,
						   @RequestParam(required=false, value="Address") String address,
						   @RequestParam(required=false, value="Floor") String floor,
						   @RequestParam(value="PaymentMethod") String paymentMethod,
						   @RequestParam(required=false, value="Accept") Boolean accept
						   ,Model model) {
		ShoppingCart cartPizzas;
		Client client;
		if(!model.containsAttribute("cart")){
			model.addAttribute("errorMessage", "Empty Shopping Cart");
			return "checkout";
		}
		if(!model.containsAttribute("client")){
			model.addAttribute("errorMessage", "First LogIn");
			return "checkout";
		}
		cartPizzas=(ShoppingCart) model.asMap().get("cart");
		client=(Client) model.asMap().get("client");
		
		String errorMessage="";
		if(name==null || !name.equals(client.getName())){
			errorMessage="Name not equal";
		}
		if(surname==null || !surname.equals(client.getSurname())){
			errorMessage="Surname not equal";
		}
		if(mail==null || mail.equals(""))
			errorMessage="Mail is empty";
		if(phone==null || phone.equals(""))
			errorMessage="Phone is empty";

		if( floor !=null && floor.equals(""))
			address+=" Floor "+floor;
		
		if(accept==null || !accept)
			errorMessage="Acept the term to continue";
		
		if(paymentMethod==null || !(paymentMethod.equals("Credit Card") || paymentMethod.equals("Cash on Delivery")))
			errorMessage="Payment type is not correct";
		
		if(!errorMessage.equals("")){
			model.addAttribute("errorMessage", errorMessage);
			return "checkout";
		}
		
		ArrayList<PizzaQuantity> pizza=new ArrayList<PizzaQuantity>();
		for(Pizza a:cartPizzas.getPizzaQuantity().keySet()){
			PizzaQuantity pq=new PizzaQuantity();
			pq.setPizza(a);
			pq.setQuantity(cartPizzas.getPizzaQuantity().get(a));
			pizza.add(pq);
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		Long id;
		if(address!=null)
			id=orderManager.insertOrder(dateFormat.format(date),pizza, paymentMethod.equals("Credit Card"), client, address);
		else
			id=orderManager.insertOrder(dateFormat.format(date),pizza, paymentMethod.equals("Credit Card"), client);
	
		if(id==null){	
			model.addAttribute("errorMessage", "Same pizza ar not avaliable, please reinsert the pizza");
			ShoppingCart cart=(ShoppingCart) model.asMap().get("cart");
			cart.getPizzaQuantity().clear();
			cart.updateTotalprice();
			return "checkout";
		}
		
		model.addAttribute("idOrder", id);
		cartPizzas.getPizzaQuantity().clear();
		cartPizzas.setTotalprice(0);
		
		return "confirmation";
	}
	
	
	@RequestMapping(value = "/orderhistory", method = RequestMethod.GET)
	public String clientOrderhistory( Model model) {
		if(model.containsAttribute("client")){		
			List<Order> orders=orderManager.getAllOrderFromCLient(((Client) model.asMap().get("client")));
			model.addAttribute("orders", orders);
			return "orderhistory";
		}
		return "redirect:login.html";
	}
	
	@RequestMapping(value = "/updateCart", method = RequestMethod.POST)
	public @ResponseBody String updateQuantityCart(@RequestParam(value="idPizza") Long id,
													@RequestParam(value="quantity") int quantity,
													Model model,HttpSession session) {
		ShoppingCart cartPizzas = null;
		if(!model.containsAttribute("cart")){
			return "No cart inizializated";
		}
		cartPizzas=(ShoppingCart) model.asMap().get("cart");

		
		return cartPizzas.updateQuantity(id,quantity)+"*"+cartPizzas.getTableBody()+"*"+ cartPizzas.getTotalprice();
	}
	
	@RequestMapping(value = "/removeItemCart", method = RequestMethod.POST)
	public @ResponseBody String updateQuantityCart(@RequestParam(value="idPizza") Long id,
													Model model,HttpSession session) {
		ShoppingCart cartPizzas = null;
		if(!model.containsAttribute("cart")){
			return "No cart inizializated";
		}
		cartPizzas=(ShoppingCart) model.asMap().get("cart");

		cartPizzas.removePizza(id);
		return cartPizzas.getTableBody()+"*"+ cartPizzas.getTotalprice();
	}
	

	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public String order(@RequestParam(value="idOrder") Long id,
													Model model) {
		if(!model.containsAttribute("client")){
			return "redirect:login.html";
		}
		Order order=orderManager.getOrder(id);
		model.addAttribute("order", order);		

		if(order.getClient().getId()!=((Client) model.asMap().get("client")).getId())
			return "order";
		
		OnlineOrder onlineOrder=orderManager.getOnlineOrder(id);
		if(onlineOrder!=null ){
				model.addAttribute("onlineOrder", onlineOrder);
		}

		return "order";
	}
	
	@RequestMapping(value = "/buildPizza", method = RequestMethod.GET)
	public String buildPizza(Locale locale, Model model) {
		if(!model.containsAttribute("client"))
			return "redirect:account.html";
		List<PizzaIngredients> listPizza = orderManager.getAllIngredients();
		model.addAttribute("listPizzaIngredients", listPizza);
		
		
		return "buildPizza";
	}
	
		
	
	
}
