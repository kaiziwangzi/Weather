package com.brook.weather.api;

import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

public interface WeatherCallApi {

	@POST("FileZL/sayHi")
	@Headers({ "Content-Type:text/xml"})
	Observable<Response<String>> sayHi(@Body String body);

}
