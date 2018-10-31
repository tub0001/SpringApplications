package com.tadigital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Main {
	@RequestMapping(value = "/")
	public String Home() {
		return "index.jsp";
	}
}
