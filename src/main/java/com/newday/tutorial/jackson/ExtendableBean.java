package com.newday.tutorial.jackson;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

public class ExtendableBean {

	public String name;

	private Map<String, String> properties;

	public ExtendableBean(String name) {
		this.name = name;
		this.properties = new HashMap<String, String>();
	}

	public void add(String key, String value) {
		this.properties.put(key, value);
	}

	@JsonAnyGetter
	public Map<String, String> getProperties() {
		return properties;
	}
	

}
