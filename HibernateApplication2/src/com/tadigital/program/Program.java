package com.tadigital.program;

import java.util.Iterator;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.tadigital.entity.Product;
import com.tadigital.entity.Customer;
import com.tadigital.hibernate.HibernateUtility;

public class Program {

	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		Customer customer1 = new Customer();
		
		Product product1 = new Product();
		customer1.setCustName("Kishan");
		customer1.setCustEmail("kishan@gmail.com");
		customer1.setCustLastName("Kumar");
		session.save(customer1);
		
		product1.setProductId(101);
		product1.setProductName("Apple");
		product1.setProductPrice("100");
		product1.setProductQuantity("10");
		session.save(product1);
		
		String hql = "SELECT cobj.custName,cobj.custEmail FROM Customer cobj";
		Query query = session.createQuery(hql);
		Iterator<Object[]> iterator = query.iterate();
		while (iterator.hasNext()) {
			Object[] row = iterator.next();
		}
		Customer ven = session.get(Customer.class, 5680);
		if (ven != null) {
			session.delete(ven);
		}
		ven = session.get(Customer.class, 5681);
		ven.setCustEmail("kishan123@gmail.com");
		session.update(ven);
		
		transaction.commit();
		session.close();
		HibernateUtility.closeSessionFactory();
	}
}