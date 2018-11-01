package com.tadigital.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tadigital.entity.Test;
import com.tadigital.service.Service;

@Controller
public class Login {
	
	private Service service;
	
	@Autowired
	public Login(Service service) {
		this.service = service;
	}


	@RequestMapping(value="/vendorlogin", method = RequestMethod.POST)
	public String loginTask(@ModelAttribute Test t, HttpServletRequest req) {
		
	/*	Test t = new Test();
		t.setName(username);
		t.setPassword(password);*/
		
		
		Boolean b = service.loginVendor(t);
		
		if(b) {
			return "LoginSuccess.jsp";
		}
		return "LoginFail.jsp";
	}
}
