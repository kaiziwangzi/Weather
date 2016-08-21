package com.brook.weather;

import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.brook.weather.api.WeatherRequest;
import com.brook.weather.webservice.request.Request;
import com.brook.weather.webservice.request.RequestBody;
import com.brook.weather.webservice.request.RequestEnvelope;
import com.brook.weather.webservice.response.ResponseEnvelope;
import com.brook.weather.webservice.response.Return;
import com.brook.weather.widgets.recyclerview.BaseViewHolder;
/**
 * 卫星云图
 * @ClassName: WxytFragment 
 * @Description: TODO
 * @author yuanxw
 * @date 2016-8-21 下午12:59:20 
 * @copyright DPX
 */
public class WxytFragment extends BaseListFragment<Return>{
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_wxyt, container, false);
	}

	@Override
	public void onRefresh(int mode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean needLoadMore() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onRefresh() {
		RequestEnvelope t = new RequestEnvelope();
		RequestBody b = new RequestBody();
		b.setJcfw(new Request("fy2e"));//fy2e卫星云图
		t.setBody(b);
		WeatherRequest.buildXml().sayHi(t).subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Action1<Response<ResponseEnvelope>>() {

					@Override
					public void call(Response<ResponseEnvelope> response) {
						if (response.isSuccessful() && null != response.body()) {
							mDataList = response.body().responseBody.model1;
							Toast.makeText(getActivity(),mDataList.size()+"",2000).show();
						}
					}
				}, new Action1<Throwable>() {

					@Override
					public void call(Throwable arg0) {
					}
				});
	}

	@Override
	protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
		// TODO Auto-generated method stub
		return null;
	}

}
