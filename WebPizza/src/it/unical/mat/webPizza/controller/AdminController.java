package it.unical.mat.webPizza.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import it.unical.mat.webPizza.domain.Administrator;
import it.unical.mat.webPizza.domain.Client;
import it.unical.mat.webPizza.domain.Deliveryman;
import it.unical.mat.webPizza.domain.Order;
import it.unical.mat.webPizza.domain.Pizza;
import it.unical.mat.webPizza.domain.PizzaChef;
import it.unical.mat.webPizza.domain.PizzaIngredients;
import it.unical.mat.webPizza.domain.PizzeriaInformation;
import it.unical.mat.webPizza.service.AccessManager;
import it.unical.mat.webPizza.service.OrderManager;
import it.unical.mat.webPizza.util.MD5Java;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sun.image.codec.jpeg.ImageFormatException;

@Controller
@SessionAttributes("admin")
public class AdminController {

	@Autowired
	private AccessManager accessManager;

	@Autowired
	private OrderManager orderManager;

	@RequestMapping(value = "/adminLogin", method = RequestMethod.GET)
	public String adminLogIn(Model model) {
		if (model.containsAttribute("admin"))
			return "redirect:accountAdmin.html";

		model.addAttribute("img", "resource/img/clients/admin.jpg");
		model.addAttribute("actionUrl", "adminLogIn.html");

		return "login";
	}

	@RequestMapping(value = "/adminLogIn", method = RequestMethod.POST)
	public String adminLogInValidation(
			@RequestParam(value = "User") String usr,
			@RequestParam(value = "Password") String pwd, Model model) {

		String hpwd = MD5Java.md5Java(pwd);

		Administrator admin = accessManager.getAdministrator(usr, hpwd);

		if (admin == null) {
			return "redirect:adminLogin.html";
		} else {
			model.addAttribute("admin", admin);
		}

		return "redirect:accountAdmin.html";
	}

	@RequestMapping(value = "/accountAdmin", method = RequestMethod.GET)
	public String adminLogInValidation(Model model) {
		if (model.containsAttribute("admin")) {
			return "accountAdmin";
		}
		return "redirect:adminLogin.html";
	}

	@RequestMapping(value = "/adminLogout", method = RequestMethod.GET)
	public String adminLogout(Model model, SessionStatus sessionStatus) {
		if (model.containsAttribute("admin")) {
			sessionStatus.setComplete();
		}
		return "redirect:index.html";
	}

	@RequestMapping(value = "/adminIgredients", method = RequestMethod.POST)
	public @ResponseBody
	String adminIngredients(Model model, SessionStatus sessionStatus) {
		if (!model.containsAttribute("admin")) {
			return "redirect:adminLogin.html";
		}

		String ingredientsString = "<table class='table table-striped' id='ingredientsTable'>";
		ingredientsString += "<thead><tr><th>Id</th><th>Name</th><th>Cost</th><th>Delete</th></tr></thead>";
		ingredientsString += "<tr>"
				+ "<td><input class='btn btn-info' type='button' value='Add' onClick='addIngredients();' /></td>"
				+ "<td><input id='newName' class='form-control' type='text'/></td>"
				+ "<td><input id='newCost' class='form-control' type='text' /></td>"
				+ "</tr>";

		for (PizzaIngredients p : orderManager.getAllIngredients()) {
			ingredientsString += "<tr>"
					+ "<td>"
					+ p.getId()
					+ "</td>"
					+ "<td><input class='form-control' type='text' id='name"
					+ p.getId()
					+ "' value='"
					+ p.getName()
					+ "'/></td>"
					+ "<td><input class='form-control' type='text' id='cost"
					+ p.getId()
					+ "' value='"
					+ p.getCost()
					+ "'/></td><td><input class='form-control' type='checkbox' /></td>"
					+ "</tr>";
		}

		ingredientsString += "<tr><td><input class='btn btn-info' type='button' value='Apply Modify' onClick='sendModify();' /></td></tr></table>";

		return ingredientsString;
	}

	@RequestMapping(value = "/addIngredients", method = RequestMethod.POST)
	public @ResponseBody
	String adminAddIngredients(@RequestParam(value = "Name") String name,
			@RequestParam(value = "Cost") Double cost, Model model) {

		if (!model.containsAttribute("admin")) {
			return "Permission Denied";
		}

		if (name == null)
			return "Insert name";
		if (cost == null)
			return "Insert cost";

		if (orderManager.addIngredients(name, cost))
			return "OK";

		return "ING no added";
	}
	
	@RequestMapping(value = "/adminEditPizza", method = RequestMethod.POST)
	public @ResponseBody
	String adminEditPizza(Model model, SessionStatus sessionStatus) {
		if (!model.containsAttribute("admin")) {
			return "redirect:adminLogin.html";
		}

		String pizzaString = "<table class='table table-striped' id='PizzaTable'>";
		pizzaString += "<thead><tr><th>Id</th><th>Name</th><th>Cost</th><th>Delete</th></tr></thead>";
		List<Pizza> allPizza=orderManager.getAllPizza();
		allPizza.addAll(orderManager.getAllClientPizza());

		for (Pizza p : allPizza) {
			pizzaString += "<tr>"
					+ "<td>"
					+ p.getId()
					+ "</td>"
					+ "<td><input readonly class='form-control' type='text' id='name"
					+ p.getId()
					+ "' value='"
					+ p.getName()
					+ "'/></td>"
					+ "<td><input readonly class='form-control' type='text' id='cost"
					+ p.getId()
					+ "' value='"
					+ p.getPrize()
					+ "'/></td><td><input class='form-control' type='checkbox' /></td>"
					+ "</tr>";
		}

		pizzaString += "<tr><td><input class='btn btn-info' type='button' value='Apply Modify' onClick='sendModifyEditPizza();' /></td></tr></table>";

		return pizzaString;
	}

	@RequestMapping(value = "/modifyIngredients", method = RequestMethod.POST)
	public @ResponseBody
	String admiModifyIngredients(@RequestParam(value = "Data") String data,
			Model model) {

		if (!model.containsAttribute("admin")) {
			return "Permission Denied";
		}

		String[] ingredients = data.split(";");
		for (String s : ingredients) {

			String[] ingString = s.split(",");
			if (ingString.length == 4) {
				Long id = Long.parseLong(ingString[0]);
				String name = ingString[1];
				double cost = Double.parseDouble(ingString[2]);
				boolean remove = false;
				if (ingString[3].equalsIgnoreCase("true"))
					remove = true;

				if (remove) {
					orderManager.deletePizzaIngredients(id);
				} else
					orderManager.updatePizzaIngredients(id, name, cost);
				// System.out.println("ID " + ingString[0] + " " + "NAME " +
				// ingString[1] + " " + "COST " + ingString[2] + " " + "REMOVE "
				// + ingString[3]);
			}

		}

		return "ING update";
	}
	
	@RequestMapping(value = "/editAdminPizza", method = RequestMethod.POST)
	public @ResponseBody
	String editAdminPizza(@RequestParam(value = "Data") String data,
			Model model) {

		if (!model.containsAttribute("admin")) {
			return "Permission Denied";
		}

		String[] pizza = data.split(";");
		for (String s : pizza) {

			String[] ingString = s.split(",");
			if (ingString.length == 2) {
				Long id = Long.parseLong(ingString[0]);
				boolean remove = false;
				if (ingString[1].equalsIgnoreCase("true"))
					remove = true;

				if (remove) {
					orderManager.deletePizza(id);
				} 
			}

		}

		return "ING update";
	}

	@RequestMapping(value = "/buildPizzaAdmin", method = RequestMethod.GET)
	public String order(Model model) {
		if (!model.containsAttribute("admin"))
			return "redirect:accountAdmin.html";

		List<PizzaIngredients> listPizza = orderManager.getAllIngredients();
		model.addAttribute("listPizzaIngredients", listPizza);

		return "buildPizzaAdmin";
	}
	
	@RequestMapping(value = "/addPizzaChef", method = RequestMethod.GET)
	public ModelAndView addPizzaChef(Model model) {
		if (!model.containsAttribute("admin"))
			return new ModelAndView("redirect:accountAdmin.html", "command", "");
		
		 return new ModelAndView("addPizzaChef", "command", new PizzaChef());
	}
	
	@RequestMapping(value = "/addPizzaChefForm", method = RequestMethod.POST)
	public String addPizzaChefForm(@ModelAttribute("SpringWeb")PizzaChef pizzaChef,Model model) {
		if (!model.containsAttribute("admin"))
			return "redirect:accountAdmin.html";
		String hpwd = MD5Java.md5Java(pizzaChef.getHashPasswd());
		boolean result=accessManager.insertPizzaChef(pizzaChef.getName(), pizzaChef.getSurname(), pizzaChef.getUsername(), hpwd);
		model.addAttribute("result", result);
		return "addPizzaChefOk";
	}
	@RequestMapping(value = "/addDeliveryMan", method = RequestMethod.GET)
	public ModelAndView addDeliveryMan(Model model) {
		if (!model.containsAttribute("admin"))
			return new ModelAndView("redirect:accountAdmin.html", "command", "");
		
		return new ModelAndView("addDeliveryMan", "command", new Deliveryman());
	}
	
	@RequestMapping(value = "/addDeliveryManForm", method = RequestMethod.POST)
	public String addDeliveryManForm(@ModelAttribute("SpringWeb")Deliveryman deliveryman,Model model) {
		if (!model.containsAttribute("admin"))
			return "redirect:accountAdmin.html";
//		System.out.println(deliveryman.getName()+" "+deliveryman.getSurname()+" "+deliveryman.getUsername()+" "+deliveryman.getHashPasswd());
		String hpwd = MD5Java.md5Java(deliveryman.getHashPasswd());
		boolean result=accessManager.insertDeliveryman(deliveryman.getName(), deliveryman.getSurname(), deliveryman.getUsername(),deliveryman.getPhoneNumber(), hpwd);
		model.addAttribute("result", result);
		return "addDeliveryManOk";
	}

	@RequestMapping(value = "/addAdminPizza", method = RequestMethod.POST)
	public @ResponseBody
	String addToCartBuildAdmin(
			@RequestParam(value = "Discount") String discountString,
			@RequestParam(value = "Name") String name,
			@RequestParam(value = "send") String ingridients, Model model) {

		double discount = 0;

		if (name == null)
			return "Insert name pizza";
		if (discountString != null)
			try {
				discount = Double.parseDouble(discountString);
			} catch (Exception e) {
			}
		if (ingridients == null || ingridients.equals(""))
			return "select ingredients";

		ArrayList<PizzaIngredients> ingredientsPizza = new ArrayList<PizzaIngredients>();
		String[] parts = ingridients.split(",");

		for (int i = 0; i < parts.length; i++) {
			ingredientsPizza.add(orderManager.getIngredient(Long
					.parseLong(parts[i])));
		}
		orderManager.insertPizza(name, ingredientsPizza, discount);
		return "OK";
	}
	

	@RequestMapping(value = "/editPizzeriaInformation", method = RequestMethod.GET)
	public String editPizzeria(Model model) {
		if (!model.containsAttribute("admin"))
			return "redirect:accountAdmin.html";
		
		PizzeriaInformation pizzeria=accessManager.getPizzeriaInformation();
		model.addAttribute("pizzeria", pizzeria);
		
		return "editPizzeriaInformation";
	}
	
	@RequestMapping(value = "/contactus", method = RequestMethod.GET)
	public String contactus(Model model) {
		
		PizzeriaInformation pizzeria=accessManager.getPizzeriaInformation();
		model.addAttribute("pizzeria", pizzeria);
		
		return "contactus";
	}

	@RequestMapping(value="editPizzeriaInfo",method = RequestMethod.POST)
	public String editPizzaInfo( @RequestParam("Name") String name,
								 @RequestParam("Address") String address,
								 @RequestParam("Mail") String mail,
								 @RequestParam("Phone") String phone,
								 Model model,HttpSession session) {
		if(name!=null && address!=null && mail!=null && phone!=null){
			accessManager.updatePizzeriaInformation(address, phone, name, mail);
		}
		
		return "redirect:editPizzeriaInformation.html";
	}

	@RequestMapping(value = "uploadImage", method = RequestMethod.POST)
	public String uploadImage(@RequestParam("image") MultipartFile image,
			@RequestParam("NamePizza") String name, Model model,
			HttpSession session) {

		if (!model.containsAttribute("admin"))
			return "redirect:accountAdmin.html";

		try {
			if (!image.isEmpty()) {
				saveImage(session.getServletContext().getRealPath("/"), name
						+ ".png", image);
				model.addAttribute("resultAdding",
						"<strong style='color: green;'>File upload</strong>");
			}
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("resultAdding",
					"<strong style='color: red;'>File not upload</strong>");
		}
		List<PizzaIngredients> listPizza = orderManager.getAllIngredients();
		model.addAttribute("listPizzaIngredients", listPizza);

		return "buildPizzaAdmin";
	}

	private void saveImage(String path, String filename, MultipartFile image)
			throws IOException {
		File file = new File(path + "/resource/img/pizza/" + filename);
		FileOutputStream writer = new FileOutputStream(file);
		writer.write(image.getBytes());
		writer.flush();
		writer.close();
	}

}
