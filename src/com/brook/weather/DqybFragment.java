package com.brook.weather;

import java.util.ArrayList;

import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import com.brook.weather.api.WeatherRequest;
import com.brook.weather.webservice.request.Request;
import com.brook.weather.webservice.request.RequestBody;
import com.brook.weather.webservice.request.RequestEnvelope;
import com.brook.weather.webservice.response.ResponseEnvelope;
import com.brook.weather.webservice.response.Return;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
/**
 * 短期预报
 * @ClassName: DqybFragment 
 * @Description: TODO
 * @author yuanxw
 * @date 2016-8-21 下午12:59:44 
 * @copyright XLSTUDIO
 */
public class DqybFragment extends BaseFragment{
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_dqyb, container, false);
	}

	@Override
	public void setUpView(View view) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUpData() {
		RequestEnvelope t = new RequestEnvelope();
		RequestBody b = new RequestBody();
		b.setTqybc(new Request("rain_tqyb_short_024", "20160821"));//24 48 72，第二个参数时间戳，返回是一张图片，直接展示即可
		t.setBody(b);
		WeatherRequest.buildXml().sayHi(t).subscribeOn(Schedulers.io())
		.observeOn(AndroidSchedulers.mainThread())
		.subscribe(new Action1<Response<ResponseEnvelope>>() {

			@Override
			public void call(Response<ResponseEnvelope> response) {
				ArrayList<Return> data = response.body().responseBody.model2;
			}
		}, new Action1<Throwable>() {

			@Override
			public void call(Throwable arg0) {
				
			}
		});
	}

}
