package com.example.repository;

import java.util.List;
import java.util.Map;

public interface FormRepository {
	void createForm(String district, int votes);
	void updateForm(String district, int votes);
	Integer obtainVotes(String district);
	List<FormDTO> obtainData();
}