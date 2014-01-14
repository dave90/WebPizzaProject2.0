package it.unical.mat.webPizza.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {
	
    @RequestMapping(value="/errors/404.html")
    public String handle404() {
    	return "404";
    }

}
