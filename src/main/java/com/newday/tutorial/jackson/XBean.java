package com.newday.tutorial.jackson;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class XBean {
	private String name ;
	private String gender ;
	public XBean(String name,String gender) {
		this.gender = gender ;
		this.name = name ;
	}
	//@JsonValue
	public String getName() {
		return this.name ;		
	}
	@JsonValue
	public String getGender() {
		return this.gender ;
	}
	public static void main(String[] args) {
		XBean xb = new  XBean("Solo Cui","male");
		ObjectMapper om = new ObjectMapper() ;
		try {
			String xb1 =om.writeValueAsString(xb) ;
			String xb2 =om.writeValueAsString(new  XBean("Killer","female")) ;
			System.out.println(xb1);
			System.out.println(xb2);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}
