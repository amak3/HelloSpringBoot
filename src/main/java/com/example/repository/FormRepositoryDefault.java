package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class FormRepositoryDefault implements FormRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void createForm(String district, int votes)
	{
		
		jdbcTemplate.update("INSERT INTO Form(district, votes) VALUES (?, ?)", district, votes);
	}
	
}
