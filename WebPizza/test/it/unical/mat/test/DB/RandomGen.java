package it.unical.mat.test.DB;

import it.unical.mat.webPizza.service.AccessManager;
import it.unical.mat.webPizza.util.MD5Java;

import java.util.Random;

public class RandomGen {
	
	public static void main(String[] args) {
		new AccessManager().getPizzaChef("mario", "1234");
	}
	
	static Random r =new Random();
	static int maxLenght=20;
	
	static int getRandomNumber(){
		return r.nextInt(maxLenght);
	}
	
	static char[] symbols={'a','b','c','d','e','f','g','1','2','5','8'};
	static String getRandomString(){
		String string=new String();
		for(int i=0;i<maxLenght;i++)
			string+=symbols[r.nextInt(symbols.length)];
		return string;
	}

}
