package com.tadigital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LinkController {
	@RequestMapping(value = {"/","/home"})
	public String Home() {
		return "index.jsp";
	}
	@RequestMapping(value = "/Login")
	public String Login() {
		return "Login.jsp";
	}
	@RequestMapping(value = "/Register")
	public String Register() {
		return "Register.jsp";
	}
	
}