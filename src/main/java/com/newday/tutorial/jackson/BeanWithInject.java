package com.newday.tutorial.jackson;

import com.fasterxml.jackson.annotation.JacksonInject;

public class BeanWithInject {

	public BeanWithInject() {
	}
	@JacksonInject
	public int id;//
	public String name;
	@JacksonInject//������type���д�ע�ͣ���������ʧ�ܡ�
	public String type;

}
