package com.newday.tutorial.jackson;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(
generator = ObjectIdGenerators.PropertyGenerator.class,
property = "id")
public class UserWithIdentity {

	public int id;
	public String name;
	public List<ItemWithIdentity> userItems;
	public UserWithIdentity() {
	}
	public UserWithIdentity(int id,String name ) {
		this.id = id ;
		this.name = name ;
	}
	public void addItem(ItemWithIdentity item) {
		if(userItems==null) {
			userItems = new ArrayList<ItemWithIdentity>();
		}
		userItems.add(item) ;
	}

}
