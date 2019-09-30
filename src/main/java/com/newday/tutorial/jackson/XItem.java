package com.newday.tutorial.jackson;


import java.util.Date;

import lombok.AllArgsConstructor;

//为减少代码量，不要自己再显示创建构造器，
//这里应用了lombok第三方注释包.
@AllArgsConstructor
public class XItem {
	public int id;
	public String itemName;
	public User owner;
	public Date date;
	
}
class User {
	public String name = "admin";
}
