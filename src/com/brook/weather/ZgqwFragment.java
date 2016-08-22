package com.brook.weather;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * 最高气温
 * @ClassName: ZgqwFragment 
 * @Description: TODO
 * @author yuanxw
 * @date 2016-8-21 下午1:00:14 
 * @copyright XLSTUDIO
 */
public class ZgqwFragment extends BaseFragment{
	
	private TabLayout tabLayout;
	private String[] hour={"1","3","6","12","24"};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_zgqw, container, false);
	}

	@Override
	public void setUpView(View view) {
		tabLayout = (TabLayout)view.findViewById(R.id.mTablayout);
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
			tabLayout.addTab(tabLayout.newTab().setText(hour[i]+"h")); 
		}
	}

	private void getData() {
		// TODO Auto-generated method stub
		
	}

}
