package com.newday.tutorial.jackson;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonPropertyOrder(alphabetic = true)
public class MyBean {

	public int id;
	// public LocalDate age = LocalDate.now().;
	private String name;

	public MyBean(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public MyBean() {
		
	}

	@JsonGetter("name")
	public String getTheName() {
		return name;
	}

	@JsonSetter("name")
	public void setTheName(String name) {
		this.name = name;
	}

}
