package com.codeproj.controller;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
@EnableWebSecurity
public class LoginController extends WebSecurityConfigurerAdapter {
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "hello this is login";
	}

}
