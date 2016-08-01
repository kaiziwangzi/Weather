package com.brook.weather.utils;

public class StringUtil {

	public static String getRequestBody(String method,String...names){
		String name = "";
		for(int i=1;i<=names.length;i++){
			name = name+"<name"+i+" i:type='d:string'>"+names[i-1]+"</name"+i+">";
		}
		String body = "<v:Envelope xmlns:i='http://www.w3.org/2001/XMLSchema-instance' xmlns:d='http://www.w3.org/2001/XMLSchema' xmlns:c='http://schemas.xmlsoap.org/soap/encoding/' xmlns:v='http://schemas.xmlsoap.org/soap/envelope/'><v:Header /><v:Body><n0:" +
				method +" id='o0' c:root='1' xmlns:n0='http://find/'>" +
				"<name1 i:type='d:string'>rain_tqyb_short_024</name1><name2 i:type='d:string'>20160801</name2>" +
				"</n0:" +method +"></v:Body></v:Envelope>";
		return body;
	}
}
