package com.example;

import org.springframework.stereotype.Component;

@Component
public class Calculator {

	public Integer add(Integer v1, Integer v2) {
		return v1 + v2;
	}
	
}
