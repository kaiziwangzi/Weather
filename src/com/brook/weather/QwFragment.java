package com.brook.weather;

import com.brook.weather.utils.DeviceInfo;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
/**
 * 最低气温
 * @ClassName: ZdqwFragment 
 * @Description: TODO
 * @author yuanxw
 * @date 2016-8-21 下午12:59:58 
 * @copyright XLSTUDIO
 */
public class QwFragment extends BaseFragment implements OnClickListener{
	private TabLayout tabLayout;
	private String[] hour={"1","3","6","12","24"};
	private TabLayout typeTl;
	private ImageView addIv;
	private String[] showStr={"色斑图","表格","曲线图"};
	private TabLayout showTl;
	private int typeIndex = 0;
	private int tabIndex = 0;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_zdqw, container, false);
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
		buildTimeTabs();
		
		for(int i=0;i<showStr.length;i++){
			showTl.addTab(showTl.newTab().setText(showStr[i])); 
		}
		typeTl.addTab(typeTl.newTab().setText("逐时快报"));
		
		typeTl.addTab(typeTl.newTab().setText("逐旬摘要"));
		
		typeTl.setOnTabSelectedListener(new OnTabSelectedListener() {
			
			@Override
			public void onTabUnselected(Tab arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onTabSelected(Tab arg0) {
				typeIndex = arg0.getPosition();
				buildTimeTabs();
			}
			
			@Override
			public void onTabReselected(Tab arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	private void buildTimeTabs() {
		tabIndex = 0;
		tabLayout.removeAllTabs();
		if(typeIndex==0){
			hour = new String[]{"1","3","6","12","24"};
		}else{
			hour = new String[]{"10","20","30","60","90"};
		}
		for(int i=0;i<hour.length;i++){
			tabLayout.addTab(tabLayout.newTab().setText(hour[i]+(typeIndex==0?"h":"天"))); 
		}
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
