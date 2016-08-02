package com.brook.weather.webservice.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

@Root(name = "S:Envelope")
@NamespaceList({
		@Namespace(reference = "http://www.w3.org/2001/XMLSchema-instance", prefix = "i"),
		@Namespace(reference = "http://www.w3.org/2001/XMLSchema", prefix = "d"),
		@Namespace(reference = "http://schemas.xmlsoap.org/soap/encoding/", prefix = "c"),
		@Namespace(reference = "http://schemas.xmlsoap.org/soap/envelope/", prefix = "S") })
public class ResponseEnvelope {
	@Element(name = "Body")
	public ReturnBody responseBody;
}
