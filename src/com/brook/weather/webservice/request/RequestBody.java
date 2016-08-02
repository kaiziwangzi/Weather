package com.brook.weather.webservice.request;

import org.simpleframework.xml.Element;

public class RequestBody {

	@Element(name="select_data_type_c_tqyj", required = false)
	private Request tqyj = null;
	
	@Element(name="select_data_type_c_pamter_time_tqyb", required = false)
	private Request tqybc = null;

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
	
}
