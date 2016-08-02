package com.brook.weather;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import com.brook.weather.adapter.MainTabAdapter;
import com.brook.weather.api.WeatherRequest;
import com.brook.weather.entity.Request;
import com.brook.weather.entity.RequestBody;
import com.brook.weather.entity.RequestEnvelope;
import com.brook.weather.entity.Tab;
import com.brook.weather.utils.StringUtil;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private GridView tabGv;
	
	private MainTabAdapter tabAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initView();

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

	private void initView() {
		tabGv = (GridView) findViewById(R.id.gv_main_tab);
		List<Tab> tabs = new ArrayList<>();
		tabs.add(new Tab(R.drawable.tqyb,"天气预报"));
		tabs.add(new Tab(R.drawable.dlyb,"短临预报"));
		tabs.add(new Tab(R.drawable.dqyb,"短期预报"));
		tabs.add(new Tab(R.drawable.tqyj,"天气预警"));
		tabs.add(new Tab(R.drawable.zhgc,"综合观测"));
		tabs.add(new Tab(R.drawable.jcfw,"决策服务"));
		tabAdapter = new MainTabAdapter(this, tabs);
		tabGv.setAdapter(tabAdapter);
	}
}
