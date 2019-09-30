package com.newday.tutorial.jackson;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EventBean {

	public String name;
	@JsonFormat(shape = JsonFormat.Shape.STRING,
				pattern = "yyyy-MM-dd hh:mm:ss")
	public Date eventDate;

	public EventBean() {//显式声明无参构造器
	}
	public EventBean(String name,Date eventDate) {
		this.name = name ;
		this.eventDate = eventDate ;
	}

}
