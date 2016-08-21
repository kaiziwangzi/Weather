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
 * 短临预报
 * @ClassName: DlybFragment 
 * @Description: TODO
 * @author yuanxw
 * @date 2016-8-21 下午12:59:44 
 * @copyright XLSTUDIO
 */
public class DlybFragment extends BaseFragment{
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_dlyb, container, false);
	}

	@Override
	public void setUpView(View view) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUpData() {
		RequestEnvelope t = new RequestEnvelope();
		RequestBody b = new RequestBody();
		b.setTqybc(new Request("rain_tqyb_near_006", "20160821"));//006---6小时 012---12小时 第二个参数时间戳，返回的是文件形式的三个返回，用viewpager做展示
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
				Toast.makeText(getActivity(),arg0.getMessage().toString(), 2000).show();
			}
		});
	}

}
