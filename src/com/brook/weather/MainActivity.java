package com.brook.weather;

import retrofit2.Response;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import com.brook.weather.api.WeatherRequest;
import com.brook.weather.utils.StringUtil;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		WeatherRequest
				.buildXml()
				.sayHi(StringUtil.getRequestBody("select_data_type_c_tqyj",
						"10", "0")).subscribeOn(Schedulers.io())
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
