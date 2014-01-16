package it.unical.mat.test.DB;

import it.unical.mat.webPizza.dao.OnlineOrderDAO;
import it.unical.mat.webPizza.daoImpl.DeliverymanDAOImpl;
import it.unical.mat.webPizza.daoImpl.OnlineOrderDAOImpl;
import it.unical.mat.webPizza.domain.Deliveryman;
import it.unical.mat.webPizza.domain.OnlineOrder;
import it.unical.mat.webPizza.service.AccessManager;
import it.unical.mat.webPizza.util.MD5Java;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class RandomGen {
	
	public static void main(String[] args) {
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
