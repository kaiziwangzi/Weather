package com.brook.weather;

import retrofit2.Response;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import com.brook.weather.api.WeatherRequest;
import com.brook.weather.entity.Request;
import com.brook.weather.entity.RequestBody;
import com.brook.weather.entity.RequestEnvelope;
import com.brook.weather.utils.StringUtil;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		RequestEnvelope t = new RequestEnvelope();
		RequestBody b = new RequestBody();
		b.setTqyj(new Request("10", "0"));
		t.setBody(b);
		
		
		WeatherRequest
				.buildXml().sayHi(t).subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Action1<Response<String>>() {

					@Override
					public void call(Response<String> arg0) {
						
					}
				},new Action1<Throwable>() {
					
					@Override
					public void call(Throwable arg0) {
						
					}
				});
	}
}
