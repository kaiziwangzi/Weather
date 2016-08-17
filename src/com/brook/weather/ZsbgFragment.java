package com.brook.weather;

import java.util.ArrayList;

import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import android.view.ViewGroup;
import android.widget.Toast;

import com.brook.weather.api.WeatherRequest;
import com.brook.weather.webservice.request.Request;
import com.brook.weather.webservice.request.RequestBody;
import com.brook.weather.webservice.request.RequestEnvelope;
import com.brook.weather.webservice.response.ResponseEnvelope;
import com.brook.weather.widgets.recyclerview.BaseViewHolder;

public class ZsbgFragment extends BaseListFragment<String> {

	@Override
	public void onRefresh(int mode) {
		
	}

	@Override
	public boolean needLoadMore() {
		return false;
	}

	@Override
	public void onRefresh() {
		
	}

	@Override
	protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
		return null;
	}

	@Override
	public void setUpData() {
		mDataList = new ArrayList<>();
		RequestEnvelope t = new RequestEnvelope();
		RequestBody b = new RequestBody();
		b.setJcfw(new Request("qxztfw"));//重要预报：qxztfw 重要信息：zytqyb
		t.setBody(b);
		WeatherRequest.buildXml().sayHi(t).subscribeOn(Schedulers.io())
		.observeOn(AndroidSchedulers.mainThread())
		.subscribe(new Action1<Response<ResponseEnvelope>>() {

			@Override
			public void call(Response<ResponseEnvelope> response) {
				if (response.isSuccessful() && null != response.body()) {
					Toast.makeText(getActivity(),"请求成功", 2000).show();
				}
			}
		}, new Action1<Throwable>() {

			@Override
			public void call(Throwable arg0) {
			}
		});
	}
	
	

}
