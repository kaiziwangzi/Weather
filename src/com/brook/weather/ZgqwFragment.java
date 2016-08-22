package com.brook.weather;

import com.brook.weather.utils.DeviceInfo;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
/**
 * 最高气温
 * @ClassName: ZgqwFragment 
 * @Description: TODO
 * @author yuanxw
 * @date 2016-8-21 下午1:00:14 
 * @copyright XLSTUDIO
 */
public class ZgqwFragment extends BaseFragment implements OnClickListener{
	
	private TabLayout tabLayout;
	private String[] hour={"1","3","6","12","24"};
	private TabLayout typeTl;
	private ImageView addIv;
	private String[] showStr={"色斑图","表格","动画"};
	private TabLayout showTl;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_zgqw, container, false);
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
		tabLayout.setTabMode(TabLayout.MODE_FIXED);
		tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
		typeTl.setTabMode(TabLayout.MODE_FIXED);
		typeTl.setTabGravity(TabLayout.GRAVITY_FILL);
		showTl.setTabGravity(TabLayout.GRAVITY_CENTER);
		buildTabs();
		getData();
	}

	private void buildTabs() {
		for(int i=0;i<hour.length;i++){
			tabLayout.addTab(tabLayout.newTab().setText(hour[i]+"h")); 
		}
		for(int i=0;i<showStr.length;i++){
			showTl.addTab(showTl.newTab().setText(showStr[i])); 
		}
		typeTl.addTab(typeTl.newTab().setText("逐时快报"));
		
		typeTl.addTab(typeTl.newTab().setText("逐旬摘要"));
	}

	private void getData() {
		// TODO Auto-generated method stub
		
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
