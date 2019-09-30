package com.newday.tutorial.jackson;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeName;

public class Zoo {

	public Animal animal;

	public Zoo(Animal animal) {
		this.animal = animal ;
	}
	public Zoo() {}
	
	@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, 
			      include = As.PROPERTY, 
			      property = "type")
	@JsonSubTypes(
			 { @JsonSubTypes.Type(value = Dog.class, name = "dog"),
			   @JsonSubTypes.Type(value = Cat.class, name = "cat") 
			 }
			)
	public static class Animal {//Animal的两个注解
		public String name;
	}

	@JsonTypeName("dog")
	public static class Dog extends Animal {
		public double barkVolume;
		public Dog(String name) {
			this.name = name ;
		}
	}

	@JsonTypeName("cat")
	public static class Cat extends Animal {
		boolean likesCream;
		public int lives;
	}

}
