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
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
/**
 * 土壤水分
 * @ClassName: TrsfFragment 
 * @Description: TODO
 * @author yuanxw
 * @date 2016-8-21 下午1:00:26 
 * @copyright XLSTUDIO
 */
public class TrsfFragment extends BaseFragment{
	private TabLayout tabLayout;
	private String[] land={"10","20","30","40","50","10_30","10_50"};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_trsf, container, false);
	}

	@Override
	public void setUpView(View view) {
		tabLayout = (TabLayout)view.findViewById(R.id.mTablayout);
	}

	@Override
	public void setUpData() {
		tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
		tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
		buildTabs();
		getData();
		
		
	}

	private void getData() {
		RequestEnvelope t = new RequestEnvelope();
		RequestBody b = new RequestBody();
		b.setDjz(new Request("soil_10_tiji_picture_map", "2016082108", "1"));
		t.setBody(b);
		WeatherRequest.buildXml().sayHi(t).subscribeOn(Schedulers.io())
		.observeOn(AndroidSchedulers.mainThread())
		.subscribe(new Action1<Response<ResponseEnvelope>>() {

			@Override
			public void call(Response<ResponseEnvelope> response) {
				ArrayList<Return> data = response.body().responseBody.model3;
				Toast.makeText(getActivity(),data.get(0).inserttime,2000).show();
			}
			}, new Action1<Throwable>() {

			@Override
			public void call(Throwable arg0) {
			}
		});
	}

	private void buildTabs() {
		for(int i=0;i<land.length;i++){
			tabLayout.addTab(tabLayout.newTab().setText(land[i].replace("_", "-")+"cm")); 
		}
	}

}
