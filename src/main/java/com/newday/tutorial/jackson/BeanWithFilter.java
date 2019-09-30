package com.newday.tutorial.jackson;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("myFilter")
public class BeanWithFilter {

	public int id;
	public String name;
	public String type ;
	
	public BeanWithFilter() {
	}
	public BeanWithFilter(int id,String name,String type) {
		this.id = id ;
		this.name = name ;
		this.type = type ;
	}
	

}
