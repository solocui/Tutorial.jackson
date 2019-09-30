package com.newday.tutorial.jackson;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


public class Event {
	public String name;
	
	
	@JsonSerialize(using = CustomDateSerializer.class)
	@JsonDeserialize(using = CustomDateDeserializer.class)
	public Date eventDate;
	
	/*
	 * public Event(String name,Date eventDate) { this.name = name ; this.eventDate
	 * = eventDate ; } public Event() {
	 * 
	 * }
	 */
	
	
}
