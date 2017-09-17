package com.example.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class FormRepositoryDefault implements FormRepository {
	
	private static final RowMapper<FormDTO> FORM_MAPPER = new RowMapper<FormDTO>() {

		@Override
		public FormDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new FormDTO(rs.getString(1), rs.getInt(2));
		}
	};

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void createForm(String district, int votes)
	{
		jdbcTemplate.update("INSERT INTO Form(district, votes) VALUES (?, ?)", district, votes);
	}

	@Override
	public void updateForm(String district, int votes) {
		jdbcTemplate.update("UPDATE Form SET votes = ? WHERE district = ?", votes, district);	
	}
	
	@Override
	public Integer obtainVotes(String district) {
		Integer votes = jdbcTemplate.queryForObject("SELECT votes FROM form WHERE district = ?", new Object[] { district }, Integer.class);
		return votes;
	}
	
	@Override
	public List<FormDTO> obtainData(){
		List<FormDTO> data = jdbcTemplate.query("SELECT district, votes FROM form", FORM_MAPPER);
		return data;
	}
}
