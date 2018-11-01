package com.tadigital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LinkController {
	@RequestMapping(value = {"/","/home"})
	public String Home() {
		return "/html/index.html";
	}
	@RequestMapping(value = "/Login")
	public String Login() {
		return "/html/Login.html";
	}
	@RequestMapping(value = "/Register")
	public String Register() {
		return "Register.jsp";
	}
	
}