package com.tadigital.service;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

//import com.tadigital.springcontainer.SpringContainer;

public class Main {
	
	public static void main(String[] main) {
 
	//ApplicationContext springContainer=SpringContainer.getSpringContainer();
	ApplicationContext springContainer=new FileSystemXmlApplicationContext("/src/com/tadigital/springcontainer/springconfig.xml");
	A c=(A)springContainer.getBean("springA");
	
	c.operation1();	
	}
} 
