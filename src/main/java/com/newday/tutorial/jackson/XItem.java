package com.newday.tutorial.jackson;


import java.util.Date;

import lombok.AllArgsConstructor;

//Ϊ���ٴ���������Ҫ�Լ�����ʾ������������
//����Ӧ����lombok������ע�Ͱ�.
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
