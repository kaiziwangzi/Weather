package com.brook.weather;

import java.util.ArrayList;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.brook.weather.DecisionServiceActivity.HomePageAdapter;
import com.brook.weather.entity.TabFragment;
import com.brook.weather.entity.TabModel;
import com.brook.weather.widgets.recyclerview.BaseViewHolder;

public class ObservationActivity extends BaseActivity {

	private ArrayList<TabFragment> tabs;
	private TabLayout tabLayout;
	private ViewPager viewPager;
	private HomePageAdapter adapter;

	@Override
	protected void setUpContentView() {
		setContentView(R.layout.activity_observation,
				R.string.tabmoel_zhgc, MODE_BACK);
	}

	@Override
	protected void setUpView() {
		tabLayout = (TabLayout) findViewById(R.id.mTablayout);
		viewPager = (ViewPager) findViewById(R.id.mViewpager);
	}

	@Override
	protected void setUpData() {
		createTab();
		viewPager.setOffscreenPageLimit(2);
		adapter = new HomePageAdapter(getSupportFragmentManager());
		viewPager.setAdapter(adapter);

		tabLayout.setupWithViewPager(viewPager);
		tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
		tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

		buildTabs();
	}

	private void createTab() {
		if (tabs == null) {
			tabs = new ArrayList<>();
			tabs.add(new TabFragment(R.string.observation_yl,
					YlFragment.class));
			tabs.add(new TabFragment(R.string.observation_ldpt,
					LdptFragment.class));
			tabs.add(new TabFragment(R.string.observation_wxyt,
					WxytFragment.class));
			tabs.add(new TabFragment(R.string.observation_trsf,
					TrsfFragment.class));
			tabs.add(new TabFragment(R.string.observation_zgqw,
					ZgqwFragment.class));
			tabs.add(new TabFragment(R.string.observation_zdqw,
					ZdqwFragment.class));
		}
	}

	private void buildTabs() {
		for (int i = 0; i < tabs.size(); i++) {
			TabLayout.Tab tab = tabLayout.getTabAt(i);
			View view = LayoutInflater.from(getApplicationContext()).inflate(
					R.layout.activity_home_tab_item, null);
			TextView mTabLabel = (TextView) view.findViewById(R.id.mTabLabel);
			mTabLabel.setText(tabs.get(i).labelResId);
			tab.setCustomView(view);
		}
	}

	public class HomePageAdapter extends FragmentPagerAdapter {
		public HomePageAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int i) {
			try {
				return tabs.get(i).targetFragmentClz.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		public int getCount() {
			return tabs.size();
		}

	}


}
