package com.brook.weather.webservice.response;

import java.util.ArrayList;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(name = "Body")
@Namespace(reference = "http://find/", prefix = "ns2")
public class ReturnBody {
	@ElementList(name = "select_data_type_c_tqyjResponse", required = false)
	public ArrayList<Return> model;
	
	@ElementList(name = "select_data_type_c_pamterResponse", required = false)
	public ArrayList<Return> model1;
	
	@ElementList(name = "select_data_type_c_pamter_time_tqybResponse", required = false)
	public ArrayList<Return> model2;
	
	@ElementList(name = "select_data_type_c_dijizhangResponse", required = false)
	public ArrayList<Return> model3;
	
	@ElementList(name = "select_data_type_c_tqybResponse", required = false)
	public ArrayList<Return> model4;
	
	
}
