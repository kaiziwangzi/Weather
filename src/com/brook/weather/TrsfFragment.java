package com.brook.weather;

import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import com.brook.weather.api.WeatherRequest;
import com.brook.weather.webservice.request.Request;
import com.brook.weather.webservice.request.RequestBody;
import com.brook.weather.webservice.request.RequestEnvelope;
import com.brook.weather.webservice.response.ResponseEnvelope;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * 土壤水分
 * @ClassName: TrsfFragment 
 * @Description: TODO
 * @author yuanxw
 * @date 2016-8-21 下午1:00:26 
 * @copyright DPX
 */
public class TrsfFragment extends BaseFragment{
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_trsf, container, false);
	}

	@Override
	public void setUpView(View view) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUpData() {
		RequestEnvelope t = new RequestEnvelope();
		RequestBody b = new RequestBody();
		b.setDjz(new Request("soil_10_tiji_picture_map", "2016082108", "1"));
		t.setBody(b);
		WeatherRequest.buildXml().sayHi(t).subscribeOn(Schedulers.io())
		.observeOn(AndroidSchedulers.mainThread())
		.subscribe(new Action1<Response<ResponseEnvelope>>() {

			@Override
			public void call(Response<ResponseEnvelope> response) {
			}
		}, new Action1<Throwable>() {

			@Override
			public void call(Throwable arg0) {
			}
		});
	}

}
