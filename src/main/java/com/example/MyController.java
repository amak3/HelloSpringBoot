package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyController {
	
	@Autowired
	private Calculator calculator;

	@RequestMapping("/")
	public String index(Model model) 
	{
		return "index";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String login(Model model, 
			@RequestParam(value="login") String login, 
			@RequestParam(value="password") String password) {
		if (login.equals("Ola") && password.equals("1234")) {
			model.addAttribute("login", login);
			return "loginOk";
		}
		return "loginFail";
	}
	
	@RequestMapping("/addValues")
	public String add(Model model, 
			@RequestParam(value="val1", required=true) Integer value1, 
			@RequestParam(value="val2", required=true) Integer value2) {
		model.addAttribute("modelValue1", value1);
		model.addAttribute("modelValue2", value2);
		model.addAttribute("modelResult", calculator.add(value1, value2));
		return "add";
	}
	
	@RequestMapping(path = "/ankieta", method = RequestMethod.GET)
	public String form(Model model)
	{
		return "form";
	}
	
	@RequestMapping(path = "/wynik", method = RequestMethod.POST)
	public void result()
	{
		
	}
	
}
