package com.example;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

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
	
	@RequestMapping(path = "/ankieta", method = RequestMethod.GET)
	public String form(Model model)
	{
		model.addAttribute("choicesCounter", choicesCounter);
		return "form";
	}
	

	@RequestMapping(path = "/upload", method = RequestMethod.GET)
	public String upload(Model model)
	{
		return "upload";
	}
	
    @RequestMapping(value = "/fileUploaded", method = RequestMethod.POST)
    public String uploadDistricts(Model model,
    						@RequestParam("file") MultipartFile file,
    						@RequestParam(value="district", required=true) String choice) throws IOException{
    	
    	InputStream input = new BufferedInputStream(file.getInputStream());
    	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
 
   	
    	String line;
    	while ((line = bufferedReader.readLine()) != null) {
    		choicesCounter.put(line, 0);
    	}
    	
    	if (!(choicesCounter.containsKey(choice)) && !(choice.length()==0))
		{
			choicesCounter.put(choice, 0);
		}
    	    	
    	return "redirect:/ankieta";
    } 
    
	static Map<String, Integer> choicesCounter = new HashMap<>();

//	static
//	{
//		choicesCounter = new HashMap<>();
//		try {
//			Path path = Paths.get("C:\\\\Users\\\\maka3\\\\Documents\\\\districts.txt");
//			List<String> lines = Files.readAllLines(path, Charset.defaultCharset() );
//			for (int i = 0; i < lines.size(); i++)
//			{
//				choicesCounter.put(lines.get(i), 0);				
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	@RequestMapping(path = "/wynik", method = RequestMethod.POST)
	public String result(Model model,
			@RequestParam(value="district", required=true) String choice)
	{	
		if (choicesCounter.containsKey(choice))
		{
			Integer counter = choicesCounter.get(choice);
			counter +=1;
			choicesCounter.put(choice, counter);
		}
		else
		{
			choicesCounter.put(choice, 1);
		}
		model.addAttribute("choicesCounter", choicesCounter);
		
		return "result";
	}
		
}
