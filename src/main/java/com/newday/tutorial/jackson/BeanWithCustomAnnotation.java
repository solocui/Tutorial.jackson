package com.newday.tutorial.jackson;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@CustomAnnotation
public class BeanWithCustomAnnotation {
	public int xid;
	public String xname;
	@JsonSerialize(using = CustomDateSerializer.class)//Ӧ����һ���Զ�������л���
	public Date xdateCreated;
	public BeanWithCustomAnnotation() {
	}
	public BeanWithCustomAnnotation(int xid,String xname,Date xdateCreated) {
		this.xid = xid;
		this.xname = xname ;
		this.xdateCreated = xdateCreated ;
	}

}
