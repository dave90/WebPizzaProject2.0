package it.unical.mat.webPizza.service;

import it.unical.mat.webPizza.dao.PizzaChefDAO;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import it.unical.mat.webPizza.daoImpl.PizzaChefDAOImpl;
import it.unical.mat.webPizza.domain.PizzaChef;

import java.awt.List;
import java.util.ArrayList;

@Aspect
public class ManageBalanceChef {
	
	
	ArrayList<PizzaChef> chefs= new ArrayList<PizzaChef>();
	
	@Before("execution(* it.unical.mat.webPizza.daoImpl.ClientDAOImpl.getClient(..))")
	public void logBefore(JoinPoint joinPoint) {
		 
		System.out.println("logBefore() is running!");
		System.out.println("hijacked : " + joinPoint.getSignature().getName());
		System.out.println("******");
	}
	
}
