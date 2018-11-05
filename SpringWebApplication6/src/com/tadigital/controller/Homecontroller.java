package com.tadigital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Homecontroller {
	@RequestMapping(value = { "/", "/home" })
	public String reloadHome() {
		return "html/index.html";
	}
}
