package com.spring.myapp.rest;

import java.util.List;
import java.util.Map;

public class RestVO {
	private int number;
	private String name;
	private List<String> hobbys;
	private Map<String, String> skills;
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getHobbys() {
		return hobbys;
	}
	public void setHobbys(List<String> hobbys) {
		this.hobbys = hobbys;
	}

	public Map<String, String> getSkills() {
		return skills;
	}
	public void setSkills(Map<String, String> skills) {
		this.skills = skills;
	}
	@Override
	public String toString() {
		return "RestVO [number=" + number + ", name=" + name + ", hobbys=" + hobbys + ", skills=" + skills + "]";
	}
	
}
