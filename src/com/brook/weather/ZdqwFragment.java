package com.brook.weather;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * 最低气温
 * @ClassName: ZdqwFragment 
 * @Description: TODO
 * @author yuanxw
 * @date 2016-8-21 下午12:59:58 
 * @copyright XLSTUDIO
 */
public class ZdqwFragment extends BaseFragment{
	private TabLayout tabLayout;
	private String[] hour={"1","3","6","12","24"};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_zdqw, container, false);
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
