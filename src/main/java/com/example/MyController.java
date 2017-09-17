package com.example;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.repository.FormRepository;


@Controller
public class MyController {
	
	@Autowired
	private Calculator calculator;
	
	@Autowired
	private FormRepository form;
	

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
	
	@RequestMapping(path = "/addDistrict", method = RequestMethod.GET)
	public String upload(Model model)
	{
		return "addDistrict";
	}
	
    @RequestMapping(value = "/importFromFile", method = RequestMethod.POST)
    public String uploadDistricts(Model model,
    						@RequestParam("file") MultipartFile file) throws IOException{
    	
    	InputStream input = new BufferedInputStream(file.getInputStream());
    	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
 
   	
    	String line;
    	while ((line = bufferedReader.readLine()) != null) {
    		form.createForm(line, 0);
    	}
    	   	    	
    	return "redirect:/form";
    } 
    
    @RequestMapping(value="/importFromTextField", method = RequestMethod.POST)
    public String uploadDistrictFromTextField(Model model,
    							@RequestParam(value="district", required=true) String choice) throws IOException{
    	if (!(choice.length()==0))
		{
    		form.createForm(choice, 0);
		}
    	    	
    	return "redirect:/form";
    }
	
	@RequestMapping(path = "/form", method = RequestMethod.GET)
	public String form(Model model)
	{
		model.addAttribute("data", form.obtainData());
		return "form";
	}

    @RequestMapping(path = "/result", method = RequestMethod.POST)
	public String result(Model model,
			@RequestParam(value="district", required=true) String choice)
	{	
		int counter = form.obtainVotes(choice);
		counter += 1;
		form.updateForm(choice, counter);
		
		model.addAttribute("data", form.obtainData());
			
		return "result";
	}
		
}
