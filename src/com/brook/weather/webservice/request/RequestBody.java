package com.brook.weather.webservice.request;

import org.simpleframework.xml.Element;

public class RequestBody {

	@Element(name="select_data_type_c_tqyj", required = false)
	private Request tqyj = null;
	
	@Element(name="select_data_type_c_pamter_time_tqyb", required = false)
	private Request tqybc = null;
	
	@Element(name="select_data_type_c_pamter", required = false)
	private Request jcfw = null;
	
	@Element(name="select_data_type_c_dijizhang", required = false)
	private Request djz = null;
	
	public Request getDjz() {
		return djz;
	}

	public void setDjz(Request djz) {
		this.djz = djz;
	}

	public Request getTqyj() {
		return tqyj;
	}

	public void setTqyj(Request tqyj) {
		this.tqyj = tqyj;
	}

	public Request getTqybc() {
		return tqybc;
	}

	public void setTqybc(Request tqybc) {
		this.tqybc = tqybc;
	}

	public Request getJcfw() {
		return jcfw;
	}

	public void setJcfw(Request jcfw) {
		this.jcfw = jcfw;
	}
	
}
