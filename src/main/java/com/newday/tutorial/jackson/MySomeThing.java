package com.newday.tutorial.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class MySomeThing {
	private String initValue ;
	public int getIntValue() {
		return intValue;
	}
	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}
	public boolean isBoolValue() {
		return boolValue;
	}
	public void setBoolValue(boolean boolValue) {
		this.boolValue = boolValue;
	}
	private String lastValue ;
	private int intValue ;
	private boolean boolValue;
	public String getInitValue() {
		return initValue;
	}
	public void setInitValue(String initValue) {
		this.initValue = initValue;
	}
	public String getLastValue() {
		return lastValue;
	}
	public void setLastValue(String lastValue) {
		this.lastValue = lastValue;
	}
	public MySomeThing() {
	}

}
