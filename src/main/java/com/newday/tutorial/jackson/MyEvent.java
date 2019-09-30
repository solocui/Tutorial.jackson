package com.newday.tutorial.jackson;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class MyEvent {

	public String name;
	@JsonSerialize(using = CustomDateSerializer.class)
	@JsonDeserialize(using = CustomEventDateDeserializer.class)
	public Date eventDate;

}
