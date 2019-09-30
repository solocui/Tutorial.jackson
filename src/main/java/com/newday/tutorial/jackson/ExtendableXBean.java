package com.newday.tutorial.jackson;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

public class ExtendableXBean {

	public String name;
	private Map<String, String> properties;
	public ExtendableXBean(){
		this.properties = new HashMap<String,String>();
	}
	@JsonAnySetter
	public void add(String key, String value) {
		properties.put(key, value);
	}
	@JsonAnyGetter
	public Map<String, String> getProperties() {
		return properties;
	}

}
