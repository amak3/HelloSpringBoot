package com.example.repository;

public class FormDTO {
	
	private String district;
	
	private int votes;
	
	public FormDTO(String district, int votes) {
		this.district = district;
		this.votes = votes;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public int getVotes() {
		return votes;
	}
	public void setVotes(int votes) {
		this.votes = votes;
	}
}
