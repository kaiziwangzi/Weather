package com.brook.weather;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import com.brook.weather.adapter.MainTabAdapter;
import com.brook.weather.api.WeatherRequest;
import com.brook.weather.entity.Tab;
import com.brook.weather.webservice.request.Request;
import com.brook.weather.webservice.request.RequestBody;
import com.brook.weather.webservice.request.RequestEnvelope;
import com.brook.weather.webservice.response.ResponseEnvelope;
import com.brook.weather.webservice.response.Return;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;

public class MainActivity extends Activity {

	private static final String TAG = MainActivity.class.getSimpleName();

	private GridView tabGv;
	private MainTabAdapter tabAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();

		findViewById(R.id.layout).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				RequestEnvelope t = new RequestEnvelope();
				RequestBody b = new RequestBody();
				b.setTqyj(new Request("10", "0"));
				t.setBody(b);

				WeatherRequest.buildXml().sayHi(t).subscribeOn(Schedulers.io())
						.observeOn(AndroidSchedulers.mainThread())
						.subscribe(new Action1<Response<ResponseEnvelope>>() {

							@Override
							public void call(Response<ResponseEnvelope> reponse) {
								ArrayList<Return> list = reponse.body().responseBody.model;
								Log.e(TAG, list.toString());
							}
						}, new Action1<Throwable>() {

							@Override
							public void call(Throwable throwable) {

								Log.e(TAG, throwable.getMessage().toString());
							}
						});
			}
		});

	}

	private void initView() {
		tabGv = (GridView) findViewById(R.id.gv_main_tab);
		List<Tab> tabs = new ArrayList<>();
		tabs.add(new Tab(R.drawable.tqyb, "天气预报"));
		tabs.add(new Tab(R.drawable.dlyb, "短临预报"));
		tabs.add(new Tab(R.drawable.dqyb, "短期预报"));
		tabs.add(new Tab(R.drawable.tqyj, "天气预警"));
		tabs.add(new Tab(R.drawable.zhgc, "综合观测"));
		tabs.add(new Tab(R.drawable.jcfw, "决策服务"));
		tabAdapter = new MainTabAdapter(this, tabs);
		tabGv.setAdapter(tabAdapter);
	}
}
