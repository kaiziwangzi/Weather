package com.brook.weather;

import java.util.ArrayList;

import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import com.brook.weather.api.WeatherRequest;
import com.brook.weather.utils.DeviceInfo;
import com.brook.weather.webservice.request.Request;
import com.brook.weather.webservice.request.RequestBody;
import com.brook.weather.webservice.request.RequestEnvelope;
import com.brook.weather.webservice.response.ResponseEnvelope;
import com.brook.weather.webservice.response.Return;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
/**
 * 土壤水分
 * @ClassName: TrsfFragment 
 * @Description: TODO
 * @author yuanxw
 * @date 2016-8-21 下午1:00:26 
 * @copyright XLSTUDIO
 */
public class TrsfFragment extends BaseFragment implements OnClickListener{
	private TabLayout tabLayout;
	private String[] land={"10","20","30","40","50","10_30","10_50"};
	private TabLayout typeTl;
	private ImageView addIv;
	private String[] showStr={"色斑图","表格","动画"};
	private TabLayout showTl;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_trsf, container, false);
	}

	@Override
	public void setUpView(View view) {
		tabLayout = (TabLayout)view.findViewById(R.id.mTablayout);
		typeTl = (TabLayout)view.findViewById(R.id.tl_type);
		showTl = (TabLayout) view.findViewById(R.id.show);
		addIv = (ImageView) view.findViewById(R.id.iv_plus);
		addIv.setOnClickListener(this);
	}

	@Override
	public void setUpData() {
		tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
		tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
		typeTl.setTabMode(TabLayout.MODE_FIXED);
		typeTl.setTabGravity(TabLayout.GRAVITY_FILL);
		showTl.setTabGravity(TabLayout.GRAVITY_CENTER);
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
		
		for(int i=0;i<showStr.length;i++){
			showTl.addTab(showTl.newTab().setText(showStr[i])); 
		}
		typeTl.addTab(typeTl.newTab().setText("土壤相对湿度"));
		
		typeTl.addTab(typeTl.newTab().setText("重量含水量"));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_plus:
			if(!typeTl.isShown()){
				 ObjectAnimator//  
		         .ofFloat(addIv, "rotation", 0.0F, 45.0F)//  
		         .setDuration(500)//  
		         .start();
				 typeTl.setVisibility(View.VISIBLE);
				 RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) addIv.getLayoutParams();
				 lp.setMargins(0,0, DeviceInfo.dip2px(getActivity(),10),DeviceInfo.dip2px(getActivity(),90));
			}else{
				 ObjectAnimator//  
		         .ofFloat(addIv, "rotation", 45.0F, 90.0F)//  
		         .setDuration(500)//  
		         .start();
				 typeTl.setVisibility(View.GONE);
				 RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) addIv.getLayoutParams();
				 lp.setMargins(0,0, DeviceInfo.dip2px(getActivity(),10),DeviceInfo.dip2px(getActivity(),50));
			}
			
			break;

		default:
			break;
		}
	}

}
