package com.brook.weather.entity;

public class Tab {

	private int drawable;
	
	private String name;

	public int getDrawable() {
		return drawable;
	}

	public void setDrawable(int drawable) {
		this.drawable = drawable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Tab(int drawable, String name) {
		super();
		this.drawable = drawable;
		this.name = name;
	}
	
}
