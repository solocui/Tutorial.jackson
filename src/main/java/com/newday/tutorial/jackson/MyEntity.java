package com.newday.tutorial.jackson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MyEntity {

	@Override
	public String toString() {
		return "MyEntity [stringValue=" + stringValue + ", intValue=" + intValue + ", booleanValue=" + booleanValue
				+ "]";
	}

	private String stringValue;
	private int intValue;
	private boolean booleanValue;

	public int getIntValue() {
		return intValue;
	}

	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}

	public boolean isBooleanValue() {
		return booleanValue;
	}

	public void setBooleanValue(boolean booleanValue) {
		this.booleanValue = booleanValue;
	}

	public MyEntity() {
		super();
	}

	//@JsonProperty("strVal")//测试未知字段时，可以注释掉该注解
	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}
	
	public static void main(String[] args) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		MyEntity me = new MyEntity();
		me.setStringValue("some value");
		String result = mapper.writeValueAsString(me) ;
		System.out.println(result);
	}

}
