package com.newday.tutorial.jackson;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value="user",namespace="users")//不使用该注释，序列化的名子为类名
public class UserWithRoot {
	public int id;
	public String name;
	//public Item[] items = {new Item()};
	public UserWithRoot(int id,String name) {
		this.id = id;
		this.name = name ;
	}
}

class Item{
	public String sn = "12345" ;
}
