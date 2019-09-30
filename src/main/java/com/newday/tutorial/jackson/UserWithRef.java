package com.newday.tutorial.jackson;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class UserWithRef {
	public int id;
	public String name;
	@JsonBackReference
	public List<ItemWithRef> userItems;
	
	public UserWithRef() {
	}
	public UserWithRef(int id, String name) {
		this.id = id;
		this.name = name ;
	}
	public void addItem(ItemWithRef item) {
		if(userItems==null) {
			userItems = new ArrayList<ItemWithRef>();
		}
		userItems.add(item);
	}

}
