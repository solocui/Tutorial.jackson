package com.newday.tutorial.jackson;

import com.fasterxml.jackson.annotation.JsonView;

public class Itemx {

	@JsonView(Views.Public.class)
	public int id;
	
	@JsonView(Views.Public.class)
	public String itemName;
	
	@JsonView(Views.Internal.class)
	public String ownerName;
	
	
	public Itemx(int id,String itemName,String ownerName) {
		this.id = id;
		this.itemName = itemName;
		this.ownerName = ownerName; 
	}
	
	
}
