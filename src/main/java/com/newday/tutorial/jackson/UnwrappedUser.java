package com.newday.tutorial.jackson;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class UnwrappedUser {

	public int id;
	
	@JsonUnwrapped
	public Name name;
	
	public UnwrappedUser(int id,Name name) {
		this.id = id ;
		this.name = name ;
	}

	public static class Name {// ¾²Ì¬ÄÚ²¿Àà
		public String firstName;
		public String lastName;
		public Name(String fname,String lname) {
			firstName = fname ;
			lastName = lname ;
		}
	}

	public UnwrappedUser() {
	}

}
