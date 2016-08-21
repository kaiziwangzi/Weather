package com.brook.weather.webservice.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;

@Namespace(reference = "http://find/", prefix = "n0")
public class Request {

	@Element(required = false)
	private String name1;
	@Element(required = false)
	private String name2;
	@Element(required = false)
	private String name3;
	@Element(required = false)
	private String name;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
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
	public String getName3() {
		return name3;
	}
	public void setName3(String name3) {
		this.name3 = name3;
	}
	
	public Request(String name1, String name2, String name3) {
		super();
		this.name1 = name1;
		this.name2 = name2;
		this.name3 = name3;
	}
	
	public Request(String name1, String name2) {
		super();
		this.name1 = name1;
		this.name2 = name2;
	}
	
	public Request(String name) {
		super();
		this.name = name;
	}
}
