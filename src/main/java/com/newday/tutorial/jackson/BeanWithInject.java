package com.newday.tutorial.jackson;

import com.fasterxml.jackson.annotation.JacksonInject;

public class BeanWithInject {

	public BeanWithInject() {
	}
	@JacksonInject
	public int id;//
	public String name;
	@JacksonInject//若不对type进行此注释，测试用例失败。
	public String type;

}
