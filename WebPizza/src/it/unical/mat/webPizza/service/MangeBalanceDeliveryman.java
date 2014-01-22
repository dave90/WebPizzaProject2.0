package it.unical.mat.webPizza.service;

import it.unical.mat.webPizza.dao.OnlineOrderDAO;
import it.unical.mat.webPizza.daoImpl.DeliverymanDAOImpl;
import it.unical.mat.webPizza.daoImpl.OnlineOrderDAOImpl;
import it.unical.mat.webPizza.domain.Deliveryman;
import it.unical.mat.webPizza.domain.OnlineOrder;
import it.unical.mat.webPizza.domain.Order;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

import sun.misc.Compare;

@Aspect
public class MangeBalanceDeliveryman {

	private ManageBDeliveryThread thread = new ManageBDeliveryThread();
	private int firstDeliveryman = 0;

	@AfterReturning(pointcut = "execution(* it.unical.mat.webPizza.daoImpl.DeliverymanDAOImpl.getDeliveryman(String,String))", returning = "deliverymanObj")
	public void loginDeliveryman(Object deliverymanObj) {
		if (deliverymanObj != null) {
			Deliveryman deliveryman = (Deliveryman) deliverymanObj;
			thread.addDeliveryman(deliveryman);
			if (firstDeliveryman == 0) {
				thread.start();
				firstDeliveryman++;
			}
		}
	}

	@After("execution(* it.unical.mat.webPizza.controller.DeliverymanController.deliverymanLogout(..))")
	public void logoutDeliveryman(JoinPoint joinpoint) {
		thread.removeDeliveryman((Long) joinpoint.getArgs()[0]);
	}

}

class ManageBDeliveryThread extends Thread {

	private Collection<Deliveryman> deliverymans = new HashSet<Deliveryman>();
	private final int ORDERXDELIVERYMAN = 5;
	private final int ORDER_MAX_WAIT = 5;
	Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	public ManageBDeliveryThread() {
	}

	public void addDeliveryman(Deliveryman d) {
		lock.lock();
		try {

			deliverymans.add(d);
			if (deliverymans.size() > 0)
				condition.signalAll();

		} finally {
			lock.unlock();
		}
	}

	public void removeDeliveryman(Long id) {
		Deliveryman d = null;
		lock.lock();
		try {
			for (Deliveryman deliveryman : deliverymans)
				if (deliveryman.getId() == id) {
					d = deliveryman;
					break;
				}
			if (d != null)
				deliverymans.remove(d);

		} finally {
			lock.unlock();
		}

	}

	@Override
	public void run() {
		OnlineOrderDAO orderDAO = new OnlineOrderDAOImpl();
		while (true) {
			try {
				lock.lock();
				while (deliverymans.size() <= 0)
					try {
						System.out.println("DELIVERY MANAGER STOPPED");
						condition.await();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				List<OnlineOrder> orders = orderDAO.getNotDeliveryManAssignedOrder();
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				boolean waitPizza = false;
				System.out.println("ORDERS READY " + orders.size());

				for (OnlineOrder o : orders) {

					try {
						Date date = dateFormat.parse(o.getDate());
						Long elapsedTime = Calendar.getInstance().getTimeInMillis() - date.getTime();
						if (elapsedTime > ORDER_MAX_WAIT * 60 * 1000) {
							System.out.println("ORDER " + o.getId() + " wait " + elapsedTime);
							waitPizza = true;
							break;
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}

				}

				Collections.sort(orders, new Comparator<OnlineOrder>() {

					@Override
					public int compare(OnlineOrder o1, OnlineOrder o2) {
						DateFormat formatter;
						Date date1 = null;
						Date date2 = null;
						formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
						try {
							date1 = (Date) formatter.parse(o1.getDate());
							date2 = (Date) formatter.parse(o2.getDate());
						} catch (ParseException e) {
							if (date1 == null)
								return 1;
							return -1;
						} catch (NullPointerException e) {
							if (date1 == null)
								return 1;
							return -1;
						}

						return date1.compareTo(date2);
					}
				});

				if (waitPizza || orders.size() >= ORDERXDELIVERYMAN) {
					int i = 0;
					for (Deliveryman d : deliverymans) {
						if (!new DeliverymanDAOImpl().isFreeDeliveryman(d.getId())) {
							System.out.println("Deliveryman " + d.getId() + " is busy");
							continue;
						}

						for (int j = i; j < ORDERXDELIVERYMAN; j++) {
							if (j >= orders.size()) {
								i = -1;
								break;
							}
							OnlineOrder onlineOrder = orders.get(j);
							orderDAO.updateDelivery(onlineOrder.getId(), d);
							System.out.println("ASSOCIATED " + onlineOrder.getId() + " with deliveryman " + d.getId());
						}
						if (i == -1)
							break;
						i += ORDERXDELIVERYMAN;

					}
				}
			} finally {
				lock.unlock();
			}

			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


}
