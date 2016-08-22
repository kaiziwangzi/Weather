package com.brook.weather;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * 雷达拼图
 * @ClassName: LdptFragment 
 * @Description: TODO
 * @author yuanxw
 * @date 2016-8-21 下午1:01:53 
 * @copyright DPX
 */
public class LdptFragment extends BaseFragment{
	private TabLayout tabLayout;
	private String[] typeStr={"组合反射率","小时降水","基本反射率","液态水含量"};
	private int tabIndex = 0;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_ldpt, container, false);
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
		for(int i=0;i<typeStr.length;i++){
			tabLayout.addTab(tabLayout.newTab().setText(typeStr[i])); 
		}
	}

	private void getData() {
		
	}

}
