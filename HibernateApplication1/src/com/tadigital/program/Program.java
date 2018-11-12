package com.tadigital.program;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.tadigital.entity.Customer;
import com.tadigital.entity.Product;
import com.tadigital.hibernate.HibernateUtility;

public class Program {

	public static void main(String args[]) {
	SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
	
	Session session = sessionFactory.openSession();
	
	Transaction transaction = session.beginTransaction();
	
	Customer customer = new Customer();
	customer.setCustName("Naveen");
	customer.setCustLastName("Chaudhary");
	customer.setCustEmail("naveen@gmail.com");
	
	Product product = new Product();
	product.setProductName("Car");
	product.setProductPrice("10000");
	product.setProductQuantity("1");
	
	session.save(customer);
	session.save(product);
	
	transaction.commit();
	session.close();
	}
}
