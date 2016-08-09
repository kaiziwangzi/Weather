package com.brook.weather.webservice.response;

import java.io.Serializable;

import org.simpleframework.xml.Element;

@Element(name = "return")
public class Return implements Serializable{
	@Element(name = "description1", required = false)
	public String description1;

	@Element(name = "id", required = false)
	public int id;

	@Element(name = "level1", required = false)
	public String level1;

	@Element(name = "pubdate", required = false)
	public String pubdate;

	@Element(name = "quyu", required = false)
	public String quyu;

	@Element(name = "status1", required = false)
	public String status1;

	@Element(name = "titles", required = false)
	public String titles;

	@Element(name = "type1", required = false)
	public String type1;

}
