package com.newday.tutorial.jackson;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value="user",namespace="users")//��ʹ�ø�ע�ͣ����л�������Ϊ����
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
