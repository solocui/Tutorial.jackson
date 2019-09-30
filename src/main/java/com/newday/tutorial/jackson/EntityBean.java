package com.newday.tutorial.jackson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EntityBean {

	public int id;
	private String name;

	public EntityBean() {
		
	}
	public EntityBean(int id,String name) {
		this.id = id ;
		this.name = name ;
	}
	@JsonProperty("name")
	public void setTheName(String name) {
		this.name = name;
	}

	@JsonProperty("name")
	public String getTheName() {
		return name;
	}
	

}
