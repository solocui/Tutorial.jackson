package com.newday.tutorial.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "name", "id" })
public class MyXBean {
	public int id;
	public String name;
	public MyXBean(int id,String name) {
		this.id = id;
		this.name = name ;
	}
}
