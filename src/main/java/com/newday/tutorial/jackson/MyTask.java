package com.newday.tutorial.jackson;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;;

@JsonFilter("myFilter")
@JsonIgnoreProperties(value = { "intValue"})
@JsonInclude(Include.NON_NULL)
public class MyTask {

	private String stringValue;
	
	private int intValue;
	@JsonIgnore
	private boolean booleanValue;
	private String[] strValues ;
	private String middleValue ;
	
	public String getMiddleValue() {
		return middleValue;
	}
	public void setMiddleValue(String middleValue) {
		this.middleValue = middleValue;
	}
	public String[] getStrValues() {
		return strValues;
	}
	public void setStrValues(String[] strValues) {
		this.strValues = strValues;
	}
	public MyTask() {
		super();
	}
	public String getStringValue() {
		return stringValue;
	}
	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}
	public int getIntValue() {
		return intValue;
	}
	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}
	public boolean isBooleanValue() {
		return booleanValue;
	}
	public void setBooleanValue(boolean booleanValue) {
		this.booleanValue = booleanValue;
	}

}
@JsonIgnoreType
class XMixInForIgnoreType {}
