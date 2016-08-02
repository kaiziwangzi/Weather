package com.brook.weather.entity;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;

@Namespace(reference = "http://find/", prefix = "n0")
public class Request {

	@Element
	private String name1;
	@Element
	private String name2;
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	public String getName2() {
		return name2;
	}
	public void setName2(String name2) {
		this.name2 = name2;
	}
	public Request(String name1, String name2) {
		super();
		this.name1 = name1;
		this.name2 = name2;
	}
	
}
