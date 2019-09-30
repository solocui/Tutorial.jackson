package com.newday.tutorial.jackson;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TypeEnumWithValue {
	TYPE1(11, "Type A"), TYPE2(22, "Type 2");
	private Integer id;
	private String name;

	private TypeEnumWithValue(int id, String name) {
		this.id = id;
		this.name = name;
	}

	@JsonValue
	public String getName() {
		return name;
	}
	//@JsonValue
	public int getId() {
		return id;
	}
}
