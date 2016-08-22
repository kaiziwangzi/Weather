package com.brook.weather;

import java.util.ArrayList;

import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import com.brook.weather.api.WeatherRequest;
import com.brook.weather.constants.Constants;
import com.brook.weather.utils.L;
import com.brook.weather.utils.StringUtil;
import com.brook.weather.webservice.request.Request;
import com.brook.weather.webservice.request.RequestBody;
import com.brook.weather.webservice.request.RequestEnvelope;
import com.brook.weather.webservice.response.ResponseEnvelope;
import com.brook.weather.webservice.response.Return;
import com.brook.weather.widgets.ZoomImageView;
import com.bumptech.glide.Glide;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.TextView;
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
	
	private TabLayout tabLayout;
	private String[] hour={"024","048","072"};
	private int tabIndex = 0;
	private ZoomImageView iv;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_dqyb, container, false);
	}

	@Override
	public void setUpView(View view) {
		tabLayout = (TabLayout)view.findViewById(R.id.mTablayout);
		
		iv = (ZoomImageView) view.findViewById(R.id.iv);
		
		((TextView)view.findViewById(R.id.tv_date)).setText(StringUtil.getY_M_D()+"制作");
	}

	@Override
	public void setUpData() {
		tabLayout.setTabMode(TabLayout.MODE_FIXED);
		tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
		buildTabs();
		getData();
		
	}

	private void buildTabs() {
		for(int i=0;i<hour.length;i++){
			tabLayout.addTab(tabLayout.newTab().setText(hour[i].replace("0", "")+"小时")); 
		}
		
		tabLayout.setOnTabSelectedListener(new OnTabSelectedListener() {
			
			@Override
			public void onTabUnselected(Tab arg0) {
				
			}
			
			@Override
			public void onTabSelected(Tab arg0) {
				tabIndex = arg0.getPosition();
				getData();
			}
			
			@Override
			public void onTabReselected(Tab arg0) {
				
			}
		});
	}

	private void getData() {
		RequestEnvelope t = new RequestEnvelope();
		RequestBody b = new RequestBody();
		b.setTqybc(new Request(Constants.ACTION_DQYB+hour[tabIndex], StringUtil.getYMD()));//24 48 72，第二个参数时间戳，返回是一张图片，直接展示即可
		t.setBody(b);
		WeatherRequest.buildXml().sayHi(t).subscribeOn(Schedulers.io())
		.observeOn(AndroidSchedulers.mainThread())
		.subscribe(new Action1<Response<ResponseEnvelope>>() {

			@Override
			public void call(Response<ResponseEnvelope> response) {
				ArrayList<Return> data = response.body().responseBody.model2;
				Glide.with(getActivity()).load(data.get(0).path).asBitmap().into(iv);
			}
		}, new Action1<Throwable>() {

			@Override
			public void call(Throwable arg0) {
				
			}
		});
	}

}
