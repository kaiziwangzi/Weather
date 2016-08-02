package com.brook.weather.entity;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(name = "v:Envelope")
@NamespaceList({
	 @Namespace(reference = "http://www.w3.org/2001/XMLSchema-instance", prefix = "i"),  
     @Namespace(reference = "http://www.w3.org/2001/XMLSchema", prefix = "d"),  
     @Namespace(reference = "http://schemas.xmlsoap.org/soap/encoding/", prefix = "c"),  
     @Namespace(reference = "http://schemas.xmlsoap.org/soap/envelope/", prefix = "v") 
})

public class RequestEnvelope {
	
	 @Element(name = "v:Body")  
	 private RequestBody body;

	public RequestBody getBody() {
		return body;
	}

	public void setBody(RequestBody body) {
		this.body = body;
	}  
	 
}
